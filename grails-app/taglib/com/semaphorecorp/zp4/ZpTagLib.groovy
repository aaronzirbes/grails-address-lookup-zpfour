package com.semaphorecorp.zp4

class ZpTagLib {
	private def defaults = [
		address				: 'address',
		city				: 'city',
		state				: 'state',
		zipCode				: 'zipCode',
		addressOut			: 'address',
		cityOut				: 'city',
		stateOut			: 'state',
		zipCodeOut			: 'zipCode',
		zipFourOut			: 'zip4',
		certifiedOut		: 'certifiedOut',
		errorsOut			: 'errorsOut',
		allowAddressLookup	: 'allowAddressLookup'
	]

	def addressLookupLink = { attrs, body ->
		if (!attrs) { attrs = [:] }

		out << '<a href="#"'
		// print out any passed keys that aren't parameters
		attrs.each{ key, value ->
			if (! defaults[key] ) {
				out << " ${key}=\"${value}\""
			}
		}

		// print out the JS function call
		out << ' onclick="addressLookupFromMap({'
		out << defaults.collect{ key, value ->
			"${key}:'${attrs[key] ?: value}'"
		}.join(", ")
		// print out the call type
		out << "}, 'link', '"
		// pass the link to the controller
		out << createLink(controller:'addressLookup', action:'json')
		// Print out the contents of the link
		out << "');\">" << body() << "</a>"
	}

	def addressLookupButton = { attrs ->

		if (!attrs) { attrs = [:] }

		out << '<input type="button"'
		// print out any passed keys that aren't parameters
		attrs.each{ key, value ->
			if (! defaults[key] ) {
				out << " ${key}=\"${value}\""
			}
		}


		out << ' onclick="addressLookupFromMap({'
		out << defaults.collect{ key, value ->
			"${key}:'${attrs[key] ?: value}'"
		}.join(", ")
		// print out the call type
		out << "}, 'button', '"
		// pass the link to the controller
		out << createLink(controller:'addressLookup', action:'json')
		out << "');\" />"

	}

	def addressLookupOnChange = { attrs ->
		if (!attrs) { attrs = [:] }

		out << '<script type="text/javascript">\n'
		out << '$(document).ready(function() {\n'
		out << '  addressLookupFromMap({'
		out << defaults.collect{ key, value ->
			"${key}:'${attrs[key] ?: value}'"
		}.join(", ")
		// print out the call type
		out << "}, 'onChange', '"
		// pass the link to the controller
		out << createLink(controller:'addressLookup', action:'json')
		out << "');\n"
		out << "});\n"
		out << '</script>'
	}

}
