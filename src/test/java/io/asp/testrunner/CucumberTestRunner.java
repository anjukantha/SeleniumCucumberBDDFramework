package io.asp.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"classpath:featureFiles"}, // Path of the feature files
		glue = { "io/asp/stepdefinitions" }, // Path of the step definitions
		plugin = { "pretty", "html:target/cucumber-reports/cucumberreport.html" }, // To generate different format of test reports
		monochrome = true, // Display the console output in a proper readable format 
		dryRun =false) // to check if the mapping b/w feature file and step definitions file

public class CucumberTestRunner extends AbstractTestNGCucumberTests{

}
