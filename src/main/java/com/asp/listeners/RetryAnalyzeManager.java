package com.asp.listeners;

/**
 * Class used to manage the retry count for failed test cases.
 * 
 * @author Anjan S P
 *
 */
public class RetryAnalyzeManager {

	private RetryAnalyzeManager() {
	}

	private static ThreadLocal<Integer> retryCount = new ThreadLocal<Integer>();

	/**
	 * Used to get the retry count from the current thread
	 * 
	 * @return
	 */
	static Integer getRetryCount() {
		return retryCount.get();
	}

	/**
	 * Used to set the retry count to the thread
	 * 
	 * @param count
	 */
	static void setRetryCount(Integer count) {
		retryCount.set(count);
	}

	/**
	 * Used to remove the retryCount thread
	 */
	public static void unload() {
		retryCount.remove();
	}
}
