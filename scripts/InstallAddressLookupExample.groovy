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
import groovy.text.SimpleTemplateEngine
import org.codehaus.groovy.grails.commons.GrailsResourceUtils

includeTargets << grailsScript("Init")
includeTargets << grailsScript('_GrailsBootstrap')

target(main: "Installs demo GSP page with address lookup tags from the address-lookup-zpfour plugin") {
	depends( configureProxy, packageApp, classpath)

	installViewExample()
}

private void installViewExample() {

	def viewsPath = GrailsResourceUtils.VIEWS_DIR_PATH
	def addressLookupPath = "${viewsPath}/addressLookup"
	def templatesPath = "${addressLookupZpfourPluginDir}/src/templates"
	def sourceFilePath = "${templatesPath}/addressLookupTest.gsp.template"
	def destFilePath = "${addressLookupPath}/test.gsp"

	ant.echo "Copying: ${sourceFilePath}"
	ant.echo "     to: ${destFilePath}"

	ant.mkdir(dir:addressLookupPath)
	ant.copy(file:sourceFilePath, toFile:destFilePath)

}

setDefaultTarget(main)
