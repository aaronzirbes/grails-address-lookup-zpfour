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
    def description = '''Provides address lookup service and tags so you can easily 
	add address lookups to your web app if you have a ZP4 HTTPSERV service from Semaphore corp running on your network.'''

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
