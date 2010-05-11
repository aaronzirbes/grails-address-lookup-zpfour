package com.semaphorecorp.zp4

class Zp4Address {

	String certified
	String addressFinal
	String addressLine2Final
	String addressLeftovers
	String cityFinal
	String cityPreferred
	String urbanizationFinal
	String stateFinal
	String zipFinal
	String errorsBinary
	String errorNumbersDetailed
	String errorMessage

	String toString() {
		if (addressFinal != null && cityFinal != null && stateFinal != null) {
			return addressFinal + ", " + cityFinal + ", " + stateFinal
		} else if (cityFinal != null && state != null) {
			return cityFinal + ", " + stateFinal
		} else if (stateFinal != null) {
			return stateFinal
		} else {
			return certified
		}
	}

	StreetAddress toStreetAddress() {

		def country = 'US'
		int zipcode = 0
		int zip4 = 0

		if (zipFinal.length() == 10) {
			zipcode = zipFinal[0..4].toInteger()
			zip4 = zipFinal[6..9].toInteger()
		}

		return new StreetAddress(address: addressFinal,
			city: cityFinal,
			state: stateFinal,
			zipCode: zipcode,
			zip4: zip4,
			country: country,
			zp4Certified: true
		)
	}
}
