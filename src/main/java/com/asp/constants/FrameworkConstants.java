package com.asp.constants;

import java.util.Date;

/**
 * Framework Constants holds all the constant values used within the framework.
 * If some value needs to be changed or modified often, then it should be stored
 * in the property files
 * 
 * @author Anjan S P
 * @see com.asp.utils.PropertyUtils
 */
public final class FrameworkConstants {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private FrameworkConstants() {
	}

	private static final String CURRENTPATH = System.getProperty("user.dir");
	private static final String DATE = new Date().toString().replace(":", "-").replace(" ", "_");
	private static final int EXPLICITWAIT = 30;
	private static final String RESOURCESPATH = CURRENTPATH + "/src/test/resources";
	private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
	private static final String JSONCONFIGFILEPATH = RESOURCESPATH + "/config/config.json";
	private static final String EXCELPATH = RESOURCESPATH + "/excel/testdata.xlsx";
	private static final String RESULTSPATH = CURRENTPATH + "/test-results/";
	private static final String CURRENTRESULTSPATH = RESULTSPATH + DATE;
	private static final String PDFREPORTSPATH = CURRENTRESULTSPATH + "/PDFReports/";
	private static final String TEMPPATH = CURRENTPATH + "/temp/";
	private static final String EXTENTREPORTPATH = CURRENTRESULTSPATH + "/execution-report/";

	/**
	 * 
	 * @author Anjan S P
	 * @return Extent Report path where the index.html file will be generated.
	 */
	public static String getExtentReportFilePath() {
		return EXTENTREPORTPATH;
	}

	/**
	 * @return Test data excel path
	 */
	public static String getExcelpath() {
		return EXCELPATH;
	}

	/**
	 * @return JSON file path
	 */
	public static String getJsonconfigfilepath() {
		return JSONCONFIGFILEPATH;
	}

	public static int getExplicitwait() {
		return EXPLICITWAIT;
	}

	/**
	 * 
	 * @return Config file path
	 */
	public static String getConfigFilePath() {
		return CONFIGFILEPATH;
	}

	/**
	 * @return the current working path
	 */
	public static String getCurrentPath() {
		return CURRENTPATH;
	}

	/**
	 * @return the date
	 */
	public static String getDate() {
		return DATE;
	}

	/**
	 * @return the resources path
	 */
	public static String getResourcesPath() {
		return RESOURCESPATH;
	}

	/**
	 * @return the results path
	 */
	public static String getResultsPath() {
		return RESULTSPATH;
	}

	/**
	 * @return the current results path
	 */
	public static String getCurrentresultsPath() {
		return CURRENTRESULTSPATH;
	}

	/**
	 * @return the pdf reports path
	 */
	public static String getPdfreportsPath() {
		return PDFREPORTSPATH;
	}

	/**
	 * @return the temp path
	 */
	public static String getTempPath() {
		return TEMPPATH;
	}

	/**
	 * Used to set the test name to thread
	 * 
	 * @param testName
	 */
	public static void setTestName(String testName) {
		TestNameManager.setTestName(testName);
	}

	/**
	 * Used to get the current test name from thread
	 * 
	 * @return
	 */
	public static String getTestName() {
		return TestNameManager.getTestName();
	}

}
