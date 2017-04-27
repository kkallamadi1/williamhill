Author: Kalyan Kallamadi
Contact Details: KALYAN.KALLAMADI@dwp.gsi.gov.uk
Project: Bereavement Support Payment


This is a Initial Acceptance Test Automation Framework for BSP.
This project defines the Acceptance Test Criteria for the Bereavement Support Payment Application. 
As per the requirements the features are written and implemented with Cucumber-JVM and Java as a framework.

Pre-requisites:-
-- To run the BPS acceptance tests, you must have the following software installed;
-- Java JDK 8 (open jdk version 1.8.0_91) - https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

--Maven (version 3.X) -
For Windows - http://www.mirrorservice.org/sites/ftp.apache.org/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.zip
For Mac - http://www.mirrorservice.org/sites/ftp.apache.org/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
Set the JAVA_HOME, MAVEN_HOME, PATH as 'system variables' within "environment variables" under System Properties

-- To check whether you have the mentioned software, run these commands ;
java -version,
mvn -v

-- Running acceptance tests:-
Pull the latest code for the acceptance test from GIT repo "https://gitlab.itsshared.net/bereavement-support-payment/Acceptance-tests.git", 
open a command prompt in the project root folder and run the command:-  mvn clean install.
This will install all the maven dependencies.
In the same command window, after the maven dependecies are downloaded, type the below command to generate the reports. (browser & environment property values can be changed according to the needs, TestRunner is the test runner file which has the cucumber tags)
mvn test -Dbrowser=chrome -Denvironment=qa -Dtest=TestRunner

-- Running acceptance tests against browserstack for cross browser testing (Firefox 38/IE9.0):-
Navigate to this URL: https://www.browserstack.com/local-testing and download the appropriate binary.
Follow the steps mentioned in above URL to start the browserstack instance 
                          OR 
Copy the command as mentioned below in a notepad and save as .bat file. Then double click on batch file to start the browser stack instance.
All this has to be done only after downloading the appropriate binary.

cd C:\Users\DWP USER\Downloads\BrowserStackLocal-win32
BrowserStackLocal.exe  h5QPWAsqT6qfxrJjrtqZ

-- To run tests against the browserstack,run the below command in the command prompt of project folder as:
mvn test -Dbrowser=firefox/internetexplorer -Denvironment=qa -Dtest=TestRunner
                    OR
Open git bash and navigate to the project folder and run the appropriate .sh command which is within the project structure as :
./run_local.sh

-- View cucumber reports:
After running the tests either from Command Prompt OR from git bash as .sh file, navigate to the Target folder within the project folder(where the project has been cloned), Site folder - cucumber-reports - and open "feature-overview.html file" - View the cucumber reports - click on appropraite feature file to view the steps and screenshots.

-- Running acceptance tests and tasks:
tasks have been configured to run through mvn commands. These commands can be run from a command window opened anywhere inside the acceptance-tests project.
 
-- Command	Description	   
Start browserstack instance - copy the command in a notepad and save as batch file and run the file	cd C:\Users\DWP USER\Downloads\BrowserStackLocal-win32
BrowserStackLocal.exe  h5QPWAsqT6qfxrJjrtqZ	   
mvn test -Dbrowser=firefox -Denvironment=qa -Dtest=TestRunner	 Runs the acceptance tests against FIREFOX 38 on Browserstack (cross browser tool)	   
mvn test -Dbrowser=internetexplorer  -Denvironment=qa -Dtest=TestRunner	Runs the acceptance tests against IE 9.0  on Browserstack (cross browser tool)	   
		   
		 
Project structure
 
Folder/file name	                            Description	   
Browsers -	                                            Folder containing appropriate chrome driver files for Windows, Mac and Linux	   
features/*.feature	-                                    Given When Then cucumber feature files	   
src/test/java/uk.gov.dwp.bsp/Cucumber/Steps	  -          Step definition class (java class implementing the .feature files). This folder must be named "Steps" for cucumber to work.	   
src/test/java/uk.gov.dwp.bsp/Cucumber/hooks.java -	    To take screenshots , browser set up, driver initialization, environment set up.	   
run_local.sh	-                                        Contains a bash script to run the tests on DEV environment.	   
.gittignore	    -                                        Config file defining folders/files that should not be committed to git.	   
C:\\temp	    -                                        the folder where we are writing the dynamic generated NINO's to a CSV file. the step definition that uses dynamic nino generation with all valid 
                                                        combinations of a NINO is "And agent submits claimant personal details",& "And agent submits claimant nino details" which is part of BPS-20.feature file and BPS-455.feature file.	   
pom.xml	        -                                        Contains project dependencies.
run_qa.sh       -                                        Contains a bash script to run the tests on QA environment.		   
src/test/java/uk.gov.dwp.bsp/Cucumber/TestRunner.java -	Runner class with cucumber tags to run the scenarios.	   
Properties	                                -            Folder containing env.properties file with browser stack credentials, environment URLS's
                                                        , chrome driver files.	 


