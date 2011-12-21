class AddressLookupZpfourGrailsPlugin {
    // the plugin version
    def version = "0.1.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2.2 > *"
    // the other plugins this plugin depends on
    def dependsOn = [jquery:"1.4.2.1 > *"]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
			"grails-app/conf/BuildConfig.groovy"
    ]

    // TODO Fill in these fields
    def author = "Aaron J. Zirbes"
    def authorEmail = "ajz@umn.edu"
    def title = "Provides plugin-interface to Address Lookups via a Semaphore ZP4 HTTPSERV"
    def description = '''\\
Provides address lookup service and tags so you can easily add address lookups to your web app if you
have a ZP4 HTTPSERV service from Semaphore corp running on your network.

From their website:

http://www.semaphorecorp.com/cgi/zp4.html

ZP4 is a DVD-ROM containing United States addresses, ZIP + 4® codes, 
mail carrier route numbers, and other supplemental postal databases 
(all maintained by the Postal Service™), plus complete automatic 
CASS Certified™ address correction software for Windows computers.

This plugin assumes you already have a valid ZP4 DVD, and your Grails app 
abides by the license agreements: Only accessed by your company, only installed
on one computer, etc...

For details, see: http://www.semaphorecorp.com/cgi/faq.html#21

I, Aaron Zirbes, the author have no affiliation with Semaphore corp, other than that I am a 
satisfied paying customer.  Their DVD is much more affortable than many similar products
available on the market.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/address-lookup-zpfour"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

}
