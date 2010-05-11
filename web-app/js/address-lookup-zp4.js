/*
 * Inputs from Tag Library
 *
 * <g:lookupAddressOnChange />
 * <g:lookupAddressLink />
 * <g:lookupAddressButton />
 *
 * paramaters is an optional map of input and output
 * fields.  Input fields are always form fields.
 * Output fields can either be form fields or element IDs
 *
 */


// default map to locate form fields / div ids on page
// to do lookup and return the data
const _addressLookupFields = {
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
}

function _addressLookupCompleteMap(overRideMap) {
	var lookupMap = _addressLookupFields;

	if (overRideMap) {
		for (var field in lookupMap) {
			if (overRideMap[field]) {
				lookupMap[field] = overRideMap[field];
			}
		}
	}

	return lookupMap;
}

function addressLookupFromMap(overRideMap, type, serviceUrl) {

	var lookupMap = _addressLookupCompleteMap(overRideMap);
	var elementMap = _addressLookupCompleteMap;

	// Set the elementMap to the same fields as the lookupMap, but empty
	for (var idx in elementMap) {
		elementMap[idx] = null;
	}

	// find the actual fields we are searching for, be they fields or element
	for (var field in lookupMap) {
		var varName = String(field);
		var fieldName = lookupMap[varName];
		// get the last two characters of the field name
		var varTail = varName.substring(varName.length - 3 , varName.length);

		// Find Element IDs only for output columns
		if ( $('#'+ fieldName).length > 0 && varTail == 'Out') {
			// field is an element id on the page
			elementMap[varName] = $('#'+ fieldName);
		} else if ( $('input[name="' + fieldName + '"]').length ) {
			// field is an input field name
			elementMap[varName] = $('input[name="' + fieldName + '"]');
		} else {
			elementMap[varName] = '';
		}
	}

	// we now have all the elements for input/output stored in the elementMap
	if (type == 'onChange') {
		// List the inputs that trigger an on-change event
		var changeFields = Array('address', 'city', 'state', 'zipCode');
		// for each input, find the element from the elementMap
		for (var i in changeFields) {
			var a = changeFields[i];
			// if the element exists...
			if ( $(elementMap[a]).length ) {
				// Set it so that we call the function when one of the fields change
				$(elementMap[a]).change(function() {
					_addressLookupZp4(elementMap, serviceUrl);
				});
			}
		}
		return true;
	} else {
		// This must be a button or link, change it _now_
		_addressLookupZp4(elementMap, serviceUrl);
		if (type == 'link') {
			return false;
		} else {
			return true;
		}
	}
}

function _addressLookupZp4(elementMap, serviceUrl) {

	// Set it to false on any change
	$(elementMap.certifiedOut).val(false);
	$(elementMap.zipFourOut).val('');

	// Need to wrap an "If US Then Else" around this

	var okToRunLookup = true;

	// If the checkbox exists
	if ($(elementMap.allowAddressLookup).length > 0) {
		// if the checkbox is not checked
		var aalChecked = $(elementMap.allowAddressLookup).attr('checked');
		if ( ! aalChecked ) {
			// disable the lookup feature
			okToRunLookup = false;
		}
	}

	if (okToRunLookup) {
		var streetAddress = $(elementMap.address).val();
		var city = $(elementMap.city).val();
		var state = $(elementMap.state).val();
		var zipCode = new Number($(elementMap.zipCode).val());

		var postData = {
			address: streetAddress,
			city: city,
			state: state,
			zipCode: zipCode.toString()
			};


		// default URL
		var url = serviceUrl;

		$.post(url, postData, function(data, textStatus) {
			if (textStatus == "success") {
				
				var errorNumbersDetailed = new String(data.zp4AddressInstance.errorNumbersDetailed)
				var zpFatal = false;
				if (errorNumbersDetailed.length > 0) {
					var zpErrors = errorNumbersDetailed.split(',');

					for (var i = 0; i < zpErrors.length; i++) {
						var zpErrorValue = new Number(zpErrors[i]);
						if (zpErrorValue < 8.0) {
							zpFatal = true;
						}
					}
				}
				
				if (!zpFatal) {
					setFieldValOrHtml( $(elementMap['addressOut']), data.streetAddressInstance.address);
					setFieldValOrHtml( $(elementMap['cityOut']), data.streetAddressInstance.city);
					setFieldValOrHtml( $(elementMap['stateOut']), data.streetAddressInstance.state);
					setFieldValOrHtml( $(elementMap['zipCodeOut']), data.streetAddressInstance.zipCode);
					setFieldValOrHtml( $(elementMap['zipFourOut']), data.streetAddressInstance.zip4);
					setFieldValOrHtml( $(elementMap['certifiedOut']), true);
					setFieldValOrHtml( $(elementMap['errorsOut']), data.zp4AddressInstance.errorMessage);
				} else {
					setFieldValOrHtml( $(elementMap['errorsOut']), data.zp4AddressInstance.errorMessage);
					setFieldValOrHtml( $(elementMap['certifiedOut']), false);
				}
			}
		}, "json");
	}
}

function setFieldValOrHtml(element, value) {
	if ($(element).get(0).tagName == 'INPUT') {
		$(element).val(value);
	} else {
		$(element).html(value);
	}
}