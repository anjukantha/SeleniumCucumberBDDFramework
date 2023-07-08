package com.asp.factories;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.asp.constants.FrameworkConstants;
import com.asp.driver.DriverManager;
import com.asp.enums.WaitStrategy;

/**
 * Explicit wait factory produces different waits before operating on webelement
 * 
 * @author Anjan S P
 */
public final class ExplicitWaitFactory {
	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExplicitWaitFactory() {
	}

	/**
	 * 
	 * @author Anjan S P
	 * @param waitstrategy Strategy to be applied to find a webelement
	 *                     {@link com.asp.enums.WaitStrategy}
	 * @param by           By locator of the webelement
	 * @return webelement Locates and return the webelement
	 */
	public static WebElement performExplicitWait(WaitStrategy waitstrategy, WebElement element, String value) {
		if (waitstrategy == WaitStrategy.CLICKABLE) {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.until(ExpectedConditions.elementToBeClickable(element));
		} else if (waitstrategy == WaitStrategy.VISIBILITY) {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.until(ExpectedConditions.visibilityOf(element));
		} else if (waitstrategy == WaitStrategy.TEXTPRESENTINELEMENT) {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.until(ExpectedConditions.textToBePresentInElement(element, value));
		} else if (waitstrategy == WaitStrategy.TEXTPRESENTINELEMENTVALUE) {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		} else if (waitstrategy == WaitStrategy.INVISIBILITY) {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.until(ExpectedConditions.invisibilityOf(element));
		} else if (waitstrategy == WaitStrategy.PAGELOAD) {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.until(x -> ((JavascriptExecutor) DriverManager.getDriver())
							.executeScript("return document.readyState").equals("complete"));
		} else if (waitstrategy == WaitStrategy.STALENESS) {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
					.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
		}
		return element;
	}

}
