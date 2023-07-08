package io.asp.stepdefinitions;

import static com.asp.enums.LogType.PASS;
import static com.asp.reports.Logger.log;

import com.asp.driver.Driver;
import com.asp.driver.DriverManager;
import com.asp.enums.ConfigProperties;
import com.asp.pages.GoogleSearchPage;
import com.asp.utils.PropertyUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearch {

	GoogleSearchPage gp = new GoogleSearchPage();

	@Given("user is on google search page")
	public void user_is_on_google_search_page() {
		Driver.initDriver();
		DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
		log(PASS, "User navigated to Google Page");
	}

	@When("user inputs {string} on google")
	public void user_inputs_cucumber_framework_on_google(String query) {
		gp.inputGoogleSearchQuery(query);
	}

	@When("user clicks on search button")
	public void user_clicks_on_search_button() {
		System.out.println("user clicks on search button");
		gp.clickGoogleSearchBtn();
	}

	@Then("cucumber framework search results page displayed")
	public void cucumber_framework_search_results_page_displayed() {
		System.out.println("cucumber framework search results page displayed");
	}

}
