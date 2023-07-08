package com.asp.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager class helps to achieve thread safety for the
 * {@link org.openqa.selenium.WebDriver} instance.
 * 
 * @author Anjan S P
 * @see Driver
 */
public final class DriverManager {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private DriverManager() {
	}

	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

	/**
	 * Returns the thread safe {@link org.openqa.selenium.WebDriver} instance
	 * fetched from ThreadLocal variable.
	 * 
	 * @author Anjan S P
	 * @return {@link org.openqa.selenium.WebDriver} instance.
	 */
	public static WebDriver getDriver() {
		return dr.get();
	}

	/**
	 * Set the WebDriver instance to thread local variable
	 * 
	 * @author Anjan S P
	 * @param driverref {@link org.openqa.selenium.WebDriver} instance that needs to
	 *                  saved from Thread safety issues.
	 */
	static void setDriver(WebDriver driverref) {
		if (Objects.nonNull(driverref)) {
			dr.set(driverref);
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
		dr.remove();
	}

}
