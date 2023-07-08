package com.asp.reports;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

import com.asp.enums.ConfigProperties;
import com.asp.enums.LogType;
import com.asp.utils.PropertyUtils;
import com.asp.utils.ScreenshotUtils;
import com.aventstack.extentreports.MediaEntityBuilder;

public final class Logger {

	private Logger() {
	}

	private static final Consumer<String> PASS = message -> ExtentManager.getExtentTest().pass(message);
	private static final Consumer<String> FAIL = message -> ExtentManager.getExtentTest().fail(message);
	private static final Consumer<String> SKIP = message -> ExtentManager.getExtentTest().skip(message);
	private static final Consumer<String> INFO = message -> ExtentManager.getExtentTest().info(message);
	private static final Consumer<String> CONSOLE = message -> System.out.println("INFO---->" + message);
	private static final Consumer<String> EXTENTANDCONSOLE = PASS.andThen(CONSOLE);
	private static final Consumer<String> TAKESCREENSHOTPASS = message -> ExtentManager.getExtentTest().pass(message,
			MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
	private static final Consumer<String> TAKESCREENSHOTFAIL = message -> ExtentManager.getExtentTest().fail(message,
			MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
	private static final Consumer<String> TAKESCREENSHOTSKIP = message -> ExtentManager.getExtentTest().skip(message,
			MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());

	private static final Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);
	private static final Map<LogType, Consumer<String>> SCREENSHOTMAP = new EnumMap<>(LogType.class);

	static {
		MAP.put(LogType.PASS, PASS);
		MAP.put(LogType.FAIL, FAIL);
		MAP.put(LogType.SKIP, SKIP);
		MAP.put(LogType.INFO, INFO);
		MAP.put(LogType.CONSOLE, CONSOLE);
		MAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE);
		SCREENSHOTMAP.put(LogType.PASS, TAKESCREENSHOTPASS);
		SCREENSHOTMAP.put(LogType.FAIL, TAKESCREENSHOTFAIL);
		SCREENSHOTMAP.put(LogType.SKIP, TAKESCREENSHOTSKIP);
		SCREENSHOTMAP.put(LogType.INFO, INFO);
		SCREENSHOTMAP.put(LogType.CONSOLE, CONSOLE);
		SCREENSHOTMAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE.andThen(TAKESCREENSHOTPASS));
	}

	public static void log(LogType status, String message) {
		if (status == LogType.PASS
				&& !PropertyUtils.get(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")) {
			MAP.getOrDefault(status, EXTENTANDCONSOLE).accept(message);
		} else if (status == LogType.FAIL
				&& !PropertyUtils.get(ConfigProperties.FAILEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")) {
			MAP.getOrDefault(status, EXTENTANDCONSOLE).accept(message);
		} else {
			SCREENSHOTMAP.getOrDefault(status, EXTENTANDCONSOLE).accept(message);
		}
	}
}
