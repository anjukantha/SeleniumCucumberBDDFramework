package com.asp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.asp.driver.DriverManager;
import com.asp.enums.WaitStrategy;

public class DemoQAPage extends BasePage {

	WebDriver driver;

	public DemoQAPage() {
		driver = DriverManager.getDriver();
	}

	// https://demoqa.com/
	By elements = By.xpath("//*[text()='Elements']");
	By textBox = By.xpath("//*[text()='Text Box']");
	By fullName = By.id("userName");
	By submitBtn = By.id("submit");
	By output = By.id("output");

	public boolean isOutputDisplayed() {
		return DriverManager.getDriver().findElement(output).isDisplayed();
	}

	/**
	 * Used to click Submit button
	 * 
	 * @return
	 * @author anjuk
	 */
	public DemoQAPage clickSubmitButton() {
		scrollIntoView(submitBtn, "Submit Button");
		click(submitBtn, WaitStrategy.CLICKABLE, "Submit Button");
		return this;
	}

	/**
	 * Used to click on Elements on Demo QA home page
	 * 
	 * @return
	 * @author anjuk
	 */
	public DemoQAPage clickElements() {
		scrollIntoView(elements, "Elements");
		click(elements, WaitStrategy.CLICKABLE, "Elements");
		return this;
	}

	/**
	 * Used to click Text Box on Elements page
	 * 
	 * @return
	 * @author anjuk
	 */
	public DemoQAPage clickTextBox() {
		scrollIntoView(textBox, "Text Box");
		click(textBox, WaitStrategy.CLICKABLE, "Text Box");
		return this;
	}

	/**
	 * Used to input Full Name on Text Box screen
	 * 
	 * @param fullName
	 * @return
	 * @author anjuk
	 */
	public DemoQAPage inputFullName(String fullName) {
		scrollIntoView(this.fullName, "Full Name");
		sendKeys(this.fullName, fullName, WaitStrategy.CLICKABLE, "Full Name");
		return this;
	}

}
