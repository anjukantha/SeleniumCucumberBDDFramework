package com.asp.pages;

import static com.asp.enums.LogType.PASS;
import static com.asp.reports.Logger.log;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.asp.driver.DriverManager;
import com.asp.enums.WaitStrategy;
import com.asp.factories.ExplicitWaitFactory;

public class BasePage {

	/**
	 * Locates element by given wait strategy, performs the clicking operation on
	 * webelement and writes the pass event to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by           By Locator of the webelement
	 * @param waitstrategy Strategy to find webelement. Known strategies
	 *                     {@link com.asp.enums.WaitStrategy}
	 * @param elementname  Name of the element that needs to be logged in the
	 *                     report.
	 */
	protected WebElement click(By by, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy,
				DriverManager.getDriver().findElement(by), null);
		element.click();
		log(PASS, "<b>" + elementname + "</b> is clicked");
		return element;
	}

	/**
	 * Locates element, performs the clicking operation on webelement using
	 * JavaScript and writes the pass event to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by          By Locator of the webelement
	 * @param elementname Name of the element that needs to be logged in the report.
	 */
	protected WebElement clickUsingJS(By by, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
		log(PASS, "<b>" + elementname + "</b> is clicked");
		return element;
	}

	/**
	 * Locates element by given wait strategy, sends the value to located webelement
	 * and writes the pass event to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by           By Locator of the webelement
	 * @param value        value to be send the text box
	 * @param waitstrategy Strategy to find webelement. Known strategies
	 *                     {@link com.asp.enums.WaitStrategy}
	 * @param elementname  Name of the element that needs to be logged in the
	 *                     report.
	 */
	protected WebElement sendKeys(By by, String value, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy,
				DriverManager.getDriver().findElement(by), value);
		element.sendKeys(value);
		log(PASS, "<b>" + value + "</b> is entered successfully in " + elementname);
		return element;
	}

	/**
	 * Locates element, sends the value to located webelement and writes the pass
	 * event to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by          By Locator of the webelement
	 * @param value       value to be send the text box
	 * @param elementname Name of the element that needs to be logged in the report.
	 */
	protected WebElement sendKeysUsingJS(By by, String value, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].value='" + value + "'", element);
		log(PASS, "<b>" + value + "</b> is entered successfully in " + elementname);
		return element;
	}

	/**
	 * Locates the element and selects the choice based on wait strategy and writes
	 * the pass event to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by           By Locator of the webelement
	 * @param value        value to be selected
	 * @param waitstrategy Strategy to select the value/text in drop down. Known
	 *                     strategies {@link com.asp.enums.WaitStrategy}
	 * @param elementname  Name of the element that needs to be logged in the
	 *                     report.
	 */
	protected WebElement selectValue(By by, String value, WaitStrategy waitstrategy, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		Select s = new Select(element);
		if (waitstrategy == WaitStrategy.TEXTPRESENTINELEMENT) {
			s.selectByVisibleText(value);
			ExplicitWaitFactory.performExplicitWait(waitstrategy, element, value);
		} else if (waitstrategy == WaitStrategy.TEXTPRESENTINELEMENTVALUE) {
			s.selectByValue(value);
			ExplicitWaitFactory.performExplicitWait(waitstrategy, element, value);
		}
		log(PASS, "<b>" + value + "</b> is selected successfully in " + elementname);
		return element;
	}

	/**
	 * Used to get the selected choice text of a drop down and writes the pass event
	 * to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by          By Locator of the webelement
	 * @param elementname Name of the element that needs to be logged in the report.
	 * @return text of the selected choice
	 */
	protected String getSelectedValue(By by, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		Select s = new Select(element);
		String value = s.getFirstSelectedOption().toString();
		log(PASS, "Selected value of " + elementname + " is <b>" + value);
		return value;
	}

	/**
	 * Used to get the value of text box and writes the pass event to the extent
	 * report.
	 * 
	 * @author Anjan S P
	 * @param by          By Locator of the webelement
	 * @param elementname Name of the element that needs to be logged in the report.
	 * @return value of the text box
	 */
	protected String getValue(By by, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		String value = element.getAttribute("value");
		log(PASS, "Value of " + elementname + " is <b>" + value);
		return value;
	}

	/**
	 * Used to get the choices of a drop down and writes the pass event to the
	 * extent report.
	 * 
	 * @author Anjan S P
	 * @param by          By Locator of the webelement
	 * @param elementname Name of the element that needs to be logged in the report.
	 * @return String[] of choices
	 */
	protected String[] getChoices(By by, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		Select s = new Select(element);
		List<WebElement> list = s.getOptions();
		String[] options = new String[list.size()];
		StringBuilder sb = new StringBuilder();
		sb.append(elementname + " has below choices:");
		int i = 0;
		for (WebElement op : list) {
			options[i] = op.getText();
			i++;
			sb.append("<br>" + i + ". " + op.getText());
		}
		log(PASS, sb.toString());
		return options;
	}

	/**
	 * Return page title of webpage in String
	 * 
	 * @author Anjan S P
	 * @return Page title of the webpage where the selenium is currently
	 *         interacting.
	 */
	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	/**
	 * Used to scroll to view element
	 * 
	 * @author Anjan S P
	 * @param by          By Locator of the webelement
	 * @param elementname Name of the element that needs to be logged in the report.
	 */
	protected WebElement srollIntoView(By by, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		log(PASS, "Scrolled into visibility of " + elementname);
		return element;
	}

	/**
	 * Used to scroll by coordinates
	 * 
	 * @author Anjan S P
	 * @param x
	 * @param y
	 */
	protected void srollIntoView(int x, int y) {
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("window.scrollBy(" + x + "," + y + ")");
		log(PASS, "Scrolled by coordinates X: " + x + ", Y: " + y);
	}

	/**
	 * Used to highlight the element
	 * 
	 * @author Anjan S P
	 * @param by          By Locator of the webelement
	 * @param elementname Name of the element that needs to be logged in the report.
	 * @return
	 */
	protected WebElement highlightElement(By by, String elementname) {
		WebElement element = DriverManager.getDriver().findElement(by);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript(
				"arguments[0].setAttribute('style', 'background: rgb(255, 255, 153); border: 2px solid red;');",
				element);
		log(PASS, "Highlighted the <b>" + elementname);
		return element;
	}

}
