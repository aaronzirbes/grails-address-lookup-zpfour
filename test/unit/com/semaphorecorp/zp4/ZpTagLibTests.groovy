package com.semaphorecorp.zp4

import grails.test.*

class ZpTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testButton() {

		ZpTagLib.metaClass.createLink = { attrs ->
			"/myapp/${attrs?.controller}/${attrs?.action}"
		}

		String output = new ZpTagLib().addressLookupButton()
		String expected = "<input type=\"button\" onclick=\"addressLookupFromMap({address:'address', city:'city', state:'state', zipCode:'zipCode', addressOut:'address', cityOut:'city', stateOut:'state', zipCodeOut:'zipCode', zipFourOut:'zip4', certifiedOut:'certifiedOut', errorsOut:'errorsOut', allowAddressLookup:'allowAddressLookup'}, 'button', '/myapp/addressLookup/json');\" />"

		assertEquals expected, output
		
    }
}
