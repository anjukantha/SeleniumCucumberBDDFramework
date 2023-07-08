package com.asp.constants;

/**
 * This file is used to manage the test name
 * 
 * @author Anjan S P
 *
 */
public class TestNameManager {

	private TestNameManager() {
	}

	private static ThreadLocal<String> testName = new ThreadLocal<>();

	/**
	 * Used to set the test name to thread
	 * 
	 * @return
	 */
	static String getTestName() {
		return testName.get();
	}

	/**
	 * Used to get the test name from current thread
	 * 
	 * @param name
	 */
	static void setTestName(String name) {
		testName.set(name);
	}

	/**
	 * Used to remove the test name thread
	 */
	public static void unload() {
		testName.remove();
	}
}
