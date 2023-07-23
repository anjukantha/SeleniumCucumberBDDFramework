package io.asp.testrunner;

import java.lang.reflect.Method;

import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberPropertiesProvider;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = { "classpath:featureFiles" }, // Path of the feature files
		glue = { "io/asp/stepdefinitions" }, // Path of the step definitions
		tags = "not @RegressionTest or not @SmokeTest", plugin = { "pretty",
				"html:target/cucumber-reports/cucumberReport.html",
				"json:target/cucumber-reports/cucumberReport.json" }, // To generate different format of test reports
		monochrome = true, // Display the console output in a proper readable format
		dryRun = false) // to check if the mapping b/w feature file and step definitions file

public class CucumberTestRunnerTest extends AbstractTestNGCucumberTests implements ITest {

	private ThreadLocal<String> testName = new ThreadLocal<>();

	private TestNGCucumberRunner testNGCucumberRunner;

	@Override
	@BeforeClass(alwaysRun = true)
	public void setUpClass(ITestContext context) {
		XmlTest currentXmlTest = context.getCurrentXmlTest();
		CucumberPropertiesProvider properties = currentXmlTest::getParameter;
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass(), properties);
	}

	@BeforeMethod
	public void BeforeMethod(Method method, Object[] testData, ITestContext ctx) {
		// Below code is update the test case name in TestNG.
		// If condition is used to set test name when using dataProvider.
		if (testData.length > 0) {
			testName.set(method.getName() + "_" + testData[0]);
			ctx.setAttribute("testName", testName.get());
		} else
			ctx.setAttribute("testName", method.getName());
	}

	@Override
	public String getTestName() {
		return testName.get();
	}

	@Override
	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	/**
	 * Returns two dimensional array of {@link PickleWrapper}s with their associated
	 * {@link FeatureWrapper}s.
	 *
	 * @return a two dimensional array of scenarios features.
	 */
	@Override
	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@Override
	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();
	}
}
