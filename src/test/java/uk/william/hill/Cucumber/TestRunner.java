package uk.william.hill.Cucumber;

import org.apache.log4j.BasicConfigurator;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features"
		,glue = "uk.william.hill.Cucumber"
		,format = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json" }
		,monochrome = true
		,tags={"@test"}


		)

public class TestRunner {	
//	public static void main(String[] args) throws Exception{
//		BasicConfigurator.configure();
//		JUnitCore.main("TestRunner");
//	}
}

