package com.asp.enums;

/**
 * Enums to restrict the values used on Property files. Without using enums
 * there can be null pointer exceptions happening because of typos. Whenever a
 * new value is added to property file, corresponding enum should be created
 * here.
 * 
 * @author Anjan S P
 * @see com.asp.utils.PropertyUtils
 */
public enum ConfigProperties {

	DEMOQA_URL, GOOGLE_URL, PASSEDSTEPSSCREENSHOTS, FAILEDSTEPSSCREENSHOTS, RETRYFAILEDTESTS, SELENIUMGRIDURL, GRID,
	BROWSER;
}
