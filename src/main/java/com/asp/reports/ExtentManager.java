package com.asp.reports;

import java.util.Objects;

import com.asp.driver.Driver;
import com.aventstack.extentreports.ExtentTest;

/**
 * ExtentManager class helps to achieve thread safety for the
 * {@link com.aventstack.extentreports.ExtentTest} instance.
 * 
 * @author Anjan S P
 * @see Driver
 */
public class ExtentManager {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExtentManager() {
	}

	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

	/**
	 * Returns the thread safe {@link com.aventstack.extentreports.ExtentTest}
	 * instance fetched from ThreadLocal variable.
	 * 
	 * @author Anjan S P
	 * @return Thread safe {@link com.aventstack.extentreports.ExtentTest} instance.
	 */
	static ExtentTest getExtentTest() {
		return extTest.get();
	}

	/**
	 * Set the {@link com.aventstack.extentreports.ExtentTest} instance to thread
	 * local variable
	 * 
	 * @author Anjan S P
	 * @param test {@link com.aventstack.extentreports.ExtentTest} instance that
	 *             needs to saved from Thread safety issues.
	 *             <p>
	 */
	static void setExtentTest(ExtentTest test) {
		if (Objects.nonNull(test)) {
			extTest.set(test);
		}
	}

	/**
	 * Calling remove method on Threadlocal variable ensures to set the default
	 * value to Threadlocal variable. It is much safer than assigning null value to
	 * ThreadLocal variable.
	 * 
	 * @author Anjan S P
	 */
	static void unload() {
		extTest.remove();
	}
}
