Author: Kalyan Kallamadi
Contact Details: kkallamadi1@gmail.com
Project: Willaim hill test


This is a Initial Acceptance Test Automation Framework.

As per the requirements the features are written and implemented with Cucumber-JVM and Java as a framework.


-- Running acceptance tests:-
Pull the latest code for the acceptance test from GIT repo
In the same command window, after the maven dependecies are downloaded, type the below command to generate the reports.
(browser & environment property values can be changed according to the needs, TestRunner
is the test runner file which has the cucumber tags)

mvn test -Dbrowser=chrome -Denvironment=dev -Dtest=TestRunner

-- View cucumber reports:
After running the tests either from Command Prompt OR from git bash as .sh file, navigate to the Target folder within the project
folder(where the project has been cloned)-> Site -> cucumber-reports - and open "feature-overview.html file" - View the cucumber reports - click on appropraite feature file to view the steps and screenshots.

--
Project structure

Folder/file name	                            Description
Browsers -	                                            Folder containing appropriate chrome driver files for Windows, Mac and Linux
features/*.feature	-                                    Given When Then cucumber feature files
src/test/java/uk.william.hill/Cucumber/Steps	  -          Step definition class (java class implementing the .feature files). This folder must be named "Steps" for cucumber to work.
src/test/java/uk.william.hill/Cucumber/hooks.java -	    To take screenshots , browser set up, driver initialization, environment set up.
run_local.sh	-                                        Contains a bash script to run the tests on DEV environment.
.gittignore	    -                                        Config file defining folders/files that should not be committed to git.
pom.xml	        -                                        Contains project dependencies.
src/test/java/uk.william.hill/Cucumber/TestRunner.java -	Runner class with cucumber tags to run the scenarios.
Properties	                                -            Folder containing env.properties file with environment URLS's
                                                        , chrome driver files.


