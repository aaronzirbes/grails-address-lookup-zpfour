<g:setProvider library="jquery"/>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<title>Address Lookup ZP4 Test Page</title>
	<g:javascript library="jquery"/>
	<g:javascript src="address-lookup-zp4.js"/>
  </head>
  <body>
  <g:form action="find" method="get">
	<div class="nav">
	  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
	</div>
  </g:form>
  <div class="body">
	<g:if test="${flash.message}">
	  <div class="message">${flash.message}</div>
	</g:if>

	<form name="lookupAddress">
	  <div class="dialog">
		<fieldset>
		  <legend>Street</legend>
		  <div class="prop">
			<label class="name" for="address"><g:message code="streetAddress.address.label" default="Address" /></label>
			<br/>
			<span class="value">
			  <g:textField name="address" id="bAddress" size="40" maxlength="64" value="${streetAddressInstance?.address}" />
			</span>
		  </div>
		  <br/>
		  <div class="prop">
			<label class="name" for="city"><g:message code="streetAddress.city.label" default="City" /></label>
			<br/>
			<span class="value">
			  <g:textField name="city" id="bCity" maxlength="30" value="${streetAddressInstance?.city}" />

			</span>
		  </div>

		  <div class="prop">
			<label class="name" for="state"><g:message code="streetAddress.state.label" default="State" /></label>
			<br/>
			<span class="value">
			  <g:textField name="state" id="bState" size="3" maxlength="2" value="${streetAddressInstance?.state}" />
			</span>
		  </div>

		  <div class="prop">
			<label class="name" for="zipCode"><g:message code="streetAddress.zipCode.label" default="Zip Code" /></label>
			<br/>
			<span class="value">
			  <g:textField name="zipCode" maxlength="5" size="7" value="${streetAddressInstance?.zipcode}" />
			</span>
			-
			<span class="value">
			  <g:textField name="zip4text" maxlength="4" size="5" value="${streetAddressInstance?.zip4}" />
			</span>
		  </div>
		</fieldset>

		<fieldset>
		  <legend>Country</legend>

		  <div class="prop">
			<label class="name" for="country"><g:message code="streetAddress.country.label" default="Country" /></label>
			<br/>
			<span class="value">
			  <g:textField name="zip4text" maxlength="2" size="3" value="${streetAddressInstance?.country}" />
			</span>
		  </div>
		</fieldset>
        <g:hiddenField name="zp4Standardized" value="${streetAddressInstance?.zp4Standardized}" />
	  </div>

	  <div id="addressLookupResult">
		<span id="z1Address">
		  ${zp4Address.addressFinal}
		</span>
		<br/>
		<span id="z1City">
		  ${zp4Address.cityFinal}
		</span>
		,
		<span id="z1State">
		  ${zp4Address.stateFinal}
		</span>

		<span id="z1ZipCode">
		  ${zp4Address.zipFinal}
		</span>
		&nbsp;
		<span id="z1Zip4">
		</span>
		<br/>
		<span id="z1Errors">
		  ${zp4Address.errorMessage}
		</span>
		<br/>
		<span id="z1Certified">
		  ${zp4Address.certified}
		</span>
	  </div>

	</form>

  </div>
</body>
</html>
