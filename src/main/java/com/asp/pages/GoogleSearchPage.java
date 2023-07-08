package com.asp.pages;

import org.openqa.selenium.By;

import com.asp.driver.DriverManager;
import com.asp.enums.WaitStrategy;

public class GoogleSearchPage extends BasePage {

	// https://www.google.com/
	By gSearch = By.xpath("//*[@title='Search']");
	By gSearchBtn = By.xpath("//input[@aria-label='Google Search']");

	/**
	 * Used to input the search query on Google
	 * 
	 * @param query
	 * @return
	 */
	public GoogleSearchPage inputGoogleSearchQuery(String query) {
		sendKeys(gSearch, query, WaitStrategy.CLICKABLE, "Google Search Query input");
		return this;
	}

	/**
	 * Used to click Google Search button
	 * 
	 * @return
	 */
	public GoogleSearchPage clickGoogleSearchBtn() {
		click(gSearchBtn, WaitStrategy.CLICKABLE, "Google Search Button");
		return this;
	}

	/**
	 * Used to get the number of search results found on google
	 * 
	 * @param query
	 * @return
	 */
	public int getNumberOfSearchResults(String query) {
		By h3 = By.xpath("//h3[contains(text(),'" + query + "')]");
		return DriverManager.getDriver().findElements(h3).size();
	}

}
