# friday-sales-funnel-test
UI tests with https://hello.friday.de/ application, with searching cars brands and types.
Test automation framework build with Page object pattern, Selenium, Maven, TestNG, Allure Reporter and Jacoco Report.
Framework ensure cross-browser testing (Firefox, Chrome), tests are executed at the same time for the mentioned browsers.

Run tests by # mvn clean test - this will generate proper Allure Report, you can use also # mvn clean verify to execute tests with usage jacoco report, however you should comment out this line in maven-surefire-plugin
<argLine>
                       javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                    </argLine>

To run tests on Jenkins, install plugin to Allure Report, run command clean test and point location where the allure reports reside.
