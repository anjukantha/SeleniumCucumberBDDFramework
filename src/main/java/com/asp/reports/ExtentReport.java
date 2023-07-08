package com.asp.reports;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.asp.constants.FrameworkConstants;
import com.asp.enums.CategoryType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

/**
 * Perform initialization and termination of
 * {@link com.aventstack.extentreports.ExtentReports} After creating an instance
 * for {@link com.aventstack.extentreports.ExtentTest}, it is delegated to
 * ThreadLocal variable for providing thread safety.
 * 
 * @author Anjan S P
 * @see com.asp.listeners.ListenerClass
 * @see com.asp.annotations.FrameworkAnnotation
 */
public final class ExtentReport {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExtentReport() {
	}

	private static ExtentReports extent;

	/**
	 * Initialize the Extent report in the path
	 * {@link FrameworkConstants.getExtentReportFilePath()}
	 * 
	 * @author Anjan S P
	 */
	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("Automation Report");
			List<ViewName> order = Arrays.asList(ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.AUTHOR,
					ViewName.DEVICE, ViewName.TEST, ViewName.EXCEPTION, ViewName.LOG);
			spark.viewConfigurer().viewOrder().as(order);

			extent.attachReporter(spark);
			extent.setSystemInfo("Module name", "Work Module");
			extent.setSystemInfo("QA Team", "Team Name");
			extent.setSystemInfo("Application", "Applicatio name");
		}
	}

	/**
	 * Flushing the reports ensures extent logs are reflected properly. Sets the
	 * ThreadLocal variable to default value
	 * 
	 * @author Anjan S P
	 */
	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
	}

	/**
	 * Creates a test node in the extent report. Delegates to {@link ExtentManager}
	 * for providing thread safety
	 * 
	 * @author Anjan S P
	 * @param testcasename Test Name that needs to be reflected in the report
	 */
	public static void createTest(String testcasename) {
		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}

	/**
	 * Logs the authors details in the authors view in the extent report. Gives an
	 * clear idea of Authors Vs Percentage success metrics
	 * 
	 * @author Anjan S P
	 * @param authors Authors who created a particular test case
	 */
	public static void addAuthors(String[] authors) {
		for (String temp : authors) {
			ExtentManager.getExtentTest().assignAuthor(temp);
		}
	}

	/**
	 * Adds the category a particular test case belongs to. Gives an clear idea of
	 * Group Vs Percentage success metrics.
	 * 
	 * @author Anjan S P
	 * @param categories category a particular test case belongs to.
	 */
	public static void addCategories(CategoryType[] categories) {
		for (CategoryType temp : categories) {
			ExtentManager.getExtentTest().assignCategory(temp.toString());
		}
	}

}
