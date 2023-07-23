package io.asp.stepdefinitions;

import static com.asp.enums.LogType.PASS;
import static com.asp.reports.Logger.log;

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
		DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.GOOGLE_URL));
		log(PASS, "User navigated to Google Page");
	}

	@When("user inputs {string} on google")
	public void user_inputs_query_on_google(String query) {
		gp.inputGoogleSearchQuery(query);
	}

	@When("user inputs data on google")
	public void user_inputs_data_on_google(String query) {
		gp.inputGoogleSearchQuery(query);
	}

	@When("user clicks on search button")
	public void user_clicks_on_search_button() {
		gp.clickGoogleSearchBtn();
	}

	@Then("{string} search results page displayed")
	public void cucumber_framework_search_results_page_displayed(String query) {
		System.out.println(query + " search results page displayed");
	}

	@Then("data search results page displayed")
	public void data_search_results_page_displayed(String query) {
		System.out.println(query + " search results page displayed");
	}

}
