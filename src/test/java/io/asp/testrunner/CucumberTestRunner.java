package io.asp.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"classpath:featureFiles/googleSearch.feature"},
		glue = { "stepdefinitions" }, 
		plugin = { "pretty", "html:target/cucumber-reports/cucumberreport.html" },
		monochrome = true)

public class CucumberTestRunner extends AbstractTestNGCucumberTests{

}
