package com.semaphorecorp.zp4

import grails.test.*

class AddressServiceTests extends GrailsUnitTestCase {

    protected void setUp() {
        super.setUp()

    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGoodAddressZipcode() {

		def a  = new StreetAddress(
			address: "200 oak street suite 350",
			zipCode: 55455, country: 'us')

		def za = a.lookup()

		assert za != null

		assert za.certified == 'C'
		assert za.errorMessage == 'State determined from ZIP'

		def fa = za.toStreetAddress()

		assert fa.address == '200 Oak St SE Ste 350'
		assert fa.city == 'Minneapolis'
		assert fa.state == 'MN'
		assert fa.zipCode == 55455
		assert fa.zip4 == 2008

    }

    void testGoodAddressCity() {

	    def a = new StreetAddress(
	        address: '176 N. Mississippi River Boulevard',
		    city: 'St. Paul', state: 'MN')

		def za = a.lookup()

		assert za != null

		assert za.certified == 'C'
		assert za.errorMessage == 'Acceptable city name used'

		def fa = za.toStreetAddress()

		assert fa.address == '176 Mississippi River Blvd N'
		assert fa.city == 'Saint Paul'
		assert fa.state == 'MN'
		assert fa.zipCode == 55104
		assert fa.zip4 == 5613
    }

    void testBadAddress() {

		def a = new StreetAddress(
			address: "666 Zirbes street",
			city: 'Anytown', state: 'MN')

		def za = a.lookup()

		assert za != null

		assert za.certified == ''

		assert za.errorNumbersDetailed == '2.1'

    }


}
