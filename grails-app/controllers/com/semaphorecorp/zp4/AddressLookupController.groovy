package com.semaphorecorp.zp4

import grails.converters.*

class AddressLookupController {

	static allowedMethods = [ json:"POST", xml:"POST" ]

    def index = {
		def testLink = createLink(conroller:"addressLookup", action:"test")

		render """\
<html>
	<head>
		<title>Address Lookup for ZP4</title>
	</head>
	<body>
		<p>If you have installed the demo page by running <br/>
		<pre>grails install-address-lookup-example</pre>
		you can go to <a href="${testLink}">here</a> for a demo.</p>
	</body>
</html>
"""
	}

	def json = {
		// println "Params: ${params}"

		def streetAddressInstance = new StreetAddress(address: params.address,
			city: params.city,
			state: params.state,
			zipCode: Integer.parseInt(params.zipCode))
		def zp4AddressInstance = streetAddressInstance.lookup()
		def streetAddressCleaned = zp4AddressInstance.toStreetAddress()

		// println "zp4AddressInstance: ${zp4AddressInstance}"
		// println "streetAddressCleaned: ${streetAddressCleaned}"


		def data = [streetAddressInstance: streetAddressCleaned,
			zp4AddressInstance: zp4AddressInstance]

		//println data.encodeAsJSON()
		render data as JSON
	}

	def xml = {
		def streetAddressInstance = new StreetAddress(address: params.address,
			city: params.city,
			state: params.state,
			zipCode: Integer.parseInt(params.zipCode))
		def zp4AddressInstance = streetAddressInstance.lookup()
		def streetAddressCleaned = zp4AddressInstance.toStreetAddress()

		def data = [streetAddressInstance: streetAddressCleaned,
			zp4AddressInstance: zp4AddressInstance]

		//println data.encodeAsXML()
		render data as XML
	}

	def test = {
		def streetAddressInstance = new StreetAddress(
	        address: '176 N. Mississippi River Boulevard',
		    city: 'St. Paul', state: 'MN')

		def zp4Address = null

		[streetAddressInstance:streetAddressInstance, zp4Address: zp4Address]
	}

	def testLookup = {
		def streetAddressInstance = new StreetAddress(params)

		def zp4Address = streetAddressInstance.lookup()

		[streetAddressInstance:streetAddressInstance, zp4Address:zp4Address]
	}

}
