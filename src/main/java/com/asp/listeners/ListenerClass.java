package com.asp.listeners;

import static com.asp.enums.LogType.FAIL;
import static com.asp.enums.LogType.INFO;
import static com.asp.enums.LogType.SKIP;
import static com.asp.reports.Logger.log;

import java.io.File;
import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.asp.constants.FrameworkConstants;
import com.asp.constants.TestNameManager;
import com.asp.reports.ExtentReport;
import com.asp.utils.ScreenshotUtils;

/**
 * Implements {@link org.testng.ITestListener} and
 * {@link org.testng.ISuiteListener} to leverage the abstract methods Mostly
 * used to help in extent report generation
 * 
 * Please make sure to add the listener details in the testng.xml file
 * 
 * @author Anjan S P
 */
public class ListenerClass implements ITestListener, ISuiteListener {

	/**
	 * Initialize the reports with the file name. Creates the output folder based on
	 * the execution date and time.
	 * 
	 * @see com.asp.reports.ExtentReport
	 */
	@Override
	public void onStart(ISuite suite) {
		File file = new File(FrameworkConstants.getResultsPath());
		if (!file.isDirectory()) {
			try {
				file.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		file = new File(FrameworkConstants.getCurrentresultsPath());
		if (!file.isDirectory()) {
			try {
				file.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ExtentReport.initReports();
	}

	/**
	 * Terminate the reports and delete the temp folder
	 * 
	 * @see com.asp.reports.ExtentReport
	 */
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
		File file = new File(FrameworkConstants.getTempPath());
		if (file.isDirectory()) {
			try {
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Starts a test node for each testNG test
	 * 
	 * @see com.asp.reports.ExtentReport
	 * @see com.asp.annotations.FrameworkAnnotation
	 */
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getTestContext().getAttribute("testName").toString());
		//ExtentReport.createTest(result.getMethod().getMethodName());
		FrameworkConstants.setTestName(result.getTestContext().getAttribute("testName").toString());
		log(INFO, "Test: " + FrameworkConstants.getTestName() + " started");
	}

	/**
	 * Marks the test as pass and logs it in the report
	 * 
	 * @see com.asp.reports.Logger
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		RetryAnalyzeManager.unload();
		log(INFO, "Test: " + FrameworkConstants.getTestName() + " passed");
		TestNameManager.unload();
	}

	/**
	 * Marks the test as fail,append base64 screenshot and logs it in the report
	 * 
	 * @see com.asp.reports.Logger
	 * @see com.asp.utils.ScreenshotUtils
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		RetryAnalyzeManager.unload();
		log(FAIL, "Test: " + FrameworkConstants.getTestName() + " failed");
		log(FAIL, result.getThrowable().toString());
		try {
			ScreenshotUtils.captureSheetshotAsPNG("Failed screenshots");
		} catch (IOException e) {
			e.printStackTrace();
		}
		TestNameManager.unload();
	}

	/**
	 * Marks the test as skip and logs it in the report
	 * 
	 * @see com.asp.reports.Logger
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		log(SKIP, "Test: " + FrameworkConstants.getTestName() + " skipped");
		TestNameManager.unload();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/*
		 * For now, we are not using this.
		 */
	}

	@Override
	public void onStart(ITestContext context) {
		/*
		 * For now, we are not using this.
		 */
	}

	@Override
	public void onFinish(ITestContext context) {
		/*
		 * For now, we are not using this.
		 */

	}

}
