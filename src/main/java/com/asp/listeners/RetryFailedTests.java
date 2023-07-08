package com.asp.listeners;

import java.util.Objects;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.asp.enums.ConfigProperties;
import com.asp.utils.PropertyUtils;

/**
 * Implements {@link IRetryAnalyzer}. Helps in rerunning the failed tests.
 * 
 * @author Anjan S P
 */
public class RetryFailedTests implements IRetryAnalyzer {

	private static final int MAXRETRY = 1;

	/**
	 * Return true when needs to be retried and false otherwise. Maximum will retry
	 * for one time. Retry will happen if user desires to and set the value in the
	 * property file
	 */
	@Override
	public boolean retry(ITestResult result) {
		if (Objects.isNull(RetryAnalyzeManager.getRetryCount())) {
			RetryAnalyzeManager.setRetryCount(0);
		}
		if (PropertyUtils.get(ConfigProperties.RETRYFAILEDTESTS).equalsIgnoreCase("yes")
				&& RetryAnalyzeManager.getRetryCount() < MAXRETRY) {
			RetryAnalyzeManager.setRetryCount(RetryAnalyzeManager.getRetryCount() + 1);
			return true;
		}
		return false;
	}

}
