//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//

/* Copyright 2006-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import grails.util.GrailsNameUtils
import org.codehaus.groovy.grails.commons.GrailsResourceUtils


def appDir = GrailsResourceUtils.GRAILS_APP_DIR
def configFile = new File(appDir, 'conf/Config.groovy')

if (configFile.exists()) {
	def settingFound = false
	configFile.eachLine{ line ->
		if (line =~ '.*grails.plugins.addressLookupZpfour.server.*') { settingFound = true }
	}
	if ( ! settingFound ) {
		configFile.withWriterAppend {
			it.writeLine '\n// Added by the Address Lookup ZP4 Plugin:'
			it.writeLine "//grails.plugins.addressLookupZpfour.server = 'http://zp4.intranet.example.com/'"
		}
		ant.echo """
	***********************************************************
	*                       IMPORTANT                         *
	*                                                         *
	* Don't forget to edit your grails-app/conf/Config.groovy *
	* file to change the                                      *
	*        grails.plugins.addressLookupZpfour.server        *
	* parameter to point to your internal ZP4 HTTPSERV server *
    ***********************************************************
"""
	}
}


