package com.semaphorecorp.zp4

import org.springframework.beans.factory.InitializingBean

class AddressService implements InitializingBean {

	// You need the following setting in your Config.groovy
	// file in order for this to see your ZP4 HTTP server
	// grails.plugins.addressLookupZpfour.server = 'http://myzp4server.exmaple.com/'
	//

    boolean transactional = true
	def grailsApplication
	static String zp4WebServerUrl

	public static Zp4Address standardize(StreetAddress a) {

		def z = new Zp4Address(addressFinal: a.address,
			cityFinal: a.city,
			stateFinal: a.state,
			zipFinal: a.zipCode?.toString(),
			certified:"F" )

		if (zp4WebServerUrl) {
			def base = "${zp4WebServerUrl}?"
			def params = ["address":a.address, "city": a.city, "state": a.state , "zip": a.zipCode?.toString() ]

			def queryList = []
			params.each() { key, value ->
				if (value) {
					queryList.add(key + "=" + URLEncoder.encode(value))
				}
			}

			def queryString = queryList.join("&")

			def url = new URL(base)
			def connection = url.openConnection()
			connection.setRequestMethod("POST")
			connection.doOutput = true

			def writer = new OutputStreamWriter(connection.outputStream)
			writer.write(queryString)
			writer.flush()
			writer.close()
			connection.connect()

			if (connection.responseCode == 200) {

				def xml = connection.content.text
				def zp4 = new XmlSlurper().parseText(xml)

				z.certified = zp4.Certified as String
				z.addressFinal = zp4.Address.AddressFinal as String
				z.addressLine2Final = zp4.Address.AddressLine2Final as String
				z.addressLeftovers = zp4.Address.AddressLeftovers as String
				z.cityFinal = zp4.City.CityFinal as String
				z.cityPreferred = zp4.City.CityPreferred as String
				z.urbanizationFinal = zp4.UrbanizationFinal as String
				z.stateFinal = zp4.StateFinal as String
				z.zipFinal = zp4.ZIP.ZIPFinal as String
				z.errorsBinary = zp4.Error.ErrorsBinary as String
				z.errorNumbersDetailed = zp4.Error.ErrorNumbersDetailed as String
				z.errorMessage = zp4.Error.ErrorMessage as String

			} else {

				z.certified = "E"
				z.errorsBinary = "1"
				z.errorNumbersDetailed = connection.responseCode
				z.errorMessage = "com.semaphorecorp.zp4.AddressService.standardize FAILED: " + connection.responseMessage
			}
		}
		return z
	}

	void afterPropertiesSet() {
		if (grailsApplication.config.grails.plugins.addressLookupZpfour.server) {
			this.zp4WebServerUrl = grailsApplication.config.grails.plugins.addressLookupZpfour.server
		} else {
			this.zp4WebServerUrl = 'http://localhost/'
		}
	}
}
