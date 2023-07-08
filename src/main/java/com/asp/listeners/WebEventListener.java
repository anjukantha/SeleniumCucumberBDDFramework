package com.asp.listeners;

import static com.asp.enums.LogType.CONSOLE;
import static com.asp.reports.Logger.log;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

/**
 * 
 * This class implements the WebDriverEventListener, which is included under
 * events. The purpose of implementing this interface is to override all the
 * methods and define certain useful Log statements which would be
 * displayed/logged as the application under test is being run.
 * 
 * Do not call any of these methods, instead these methods will be invoked
 * automatically as an when the action done (click, findBy etc).
 * 
 * @author Anjan S P
 *
 */
public class WebEventListener implements WebDriverListener {

	@Override
	public void beforeFindElement(WebDriver driver, By locator) {
		log(CONSOLE, "Trying to find Element By : " + locator.toString());
	}

	@Override
	public void afterFindElement(WebDriver driver, By locator, WebElement result) {
		log(CONSOLE, "Found Element By : " + locator.toString());
	}

	@Override
	public void beforeGet(WebDriver driver, String url) {
		log(CONSOLE, "Trying to get the URL: " + url);
	}

	@Override
	public void afterGet(WebDriver driver, String url) {
		log(CONSOLE, "Navigated to URL: " + url);
	}

	@Override
	public void beforeGetTitle(WebDriver driver) {
		log(CONSOLE, "Trying to get the Current Window Title");
	}

	@Override
	public void afterGetTitle(WebDriver driver, String result) {
		log(CONSOLE, "Got the title: " + result);
	}

	@Override
	public void beforeFindElements(WebDriver driver, By locator) {
		log(CONSOLE, "Trying to find Elements By : " + locator.toString());
	}

	@Override
	public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
		log(CONSOLE, "Found " + result.size() + " Elements By : " + locator.toString());
	}

	@Override
	public void beforeGetWindowHandles(WebDriver driver) {
		log(CONSOLE, "Trying to get the number of windows currently opened");
	}

	@Override
	public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
		log(CONSOLE, "Found " + result.size() + " windows opened");
	}

	@Override
	public void beforeClick(WebElement element) {
		log(CONSOLE, "Trying to click element: " + element.toString().split("]")[1]);
	}

	@Override
	public void afterClick(WebElement element) {
		log(CONSOLE, "Clicked element: " + element.toString().split("]")[1]);
	}

	@Override
	public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
		log(CONSOLE, "Trying to send the keys " + Arrays.toString(keysToSend) + " to element: "
				+ element.toString().split("]")[1]);
	}

	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		log(CONSOLE, "Successfully sent the keys " + Arrays.toString(keysToSend) + " to element: "
				+ element.toString().split("]")[1]);
	}
}