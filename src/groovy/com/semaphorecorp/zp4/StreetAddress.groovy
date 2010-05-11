package com.semaphorecorp.zp4

import org.springframework.context.ApplicationContext
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

class StreetAddress {

	private ApplicationContext ctx =
		ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT) 
	private AddressService addressService = ctx.getBean('addressService')

	private String		address = ''
	private String		city = null
	private String		state = null
	private Integer		zipCode = null
	private Integer		zip4 = null
	private String		country = 'US'
	private boolean		zp4Certified = false

	// enforce maximum length of 64
	void setAddress(String _address) {
		// silently trim the string
		if (_address == null) {
			address = ''
		} else if (_address.size() > 64) {
			address = _address.substring(0..63)
		} else {
			address = _address
		}
	}

	// enforce maximum length of 30
	void setCity(String _city) {
		if (_city) {
			if (_city.size() > 30) {
				city = _city.substring(0..29)
			} else {
				city = _city
			}
		} else {
			city = null
		}
	}

	// enforce maximum length of 2
	void setState(String _state) {
		if (_state?.size() != 2) {
			state = null
		} else {
			state = _state
		}
	}

	// enforce min and max
	void setZipCode(Integer _zipCode) {
		if (_zipCode != null) {
			if (_zipCode > 0 && _zipCode < 100000) {
				zipCode = _zipCode
			} else {
				zipCode = null
			}
		} else {
			zipCode = null
		}
	}

	// enforce min and max
	void setZip4(Integer _zip4) {
		if (_zip4 != null) {
			if (_zip4 > 0 && _zip4 < 10000) {
				zip4 = _zip4
			} else {
				zip4 = null
			}
		} else {
			zip4 = null
		}
	}

	// enforce maximum length of 2
	void setCountry(String _country) {
		if (_country?.size() != 2) {
			country = null
		} else {
			country = _country
		}
	}

	void setZp4Certified(Boolean _zp4Certified) {
		zp4Certified = _zp4Certified
	}

	String getAddress() { address }
	String getCity() { city }
	String getState() { state }
	Integer getZipCode() { zipCode }
	Integer getZip4() { zip4 }
	String getCountry() { country }
	Boolean getZp4Certified() { zp4Certified }

	String toString() {
		if (address != null && city != null && state != null) {
			return address + ", " + city + ", " + state
		} else if (city != null && state != null) {
			return city + ", " + state
		} else if (state != null) {
			return state
		} else {
			return zp4Certified
		}
	}

	Zp4Address lookup() {
		if (addressService) {
			addressService.standardize(this)
		} else {
			println "address service is null!"
		}
	}

}
