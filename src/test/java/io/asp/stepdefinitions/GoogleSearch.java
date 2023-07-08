package io.asp.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearch {

	@Given("user is on google search page")
	public void user_is_on_google_search_page() {
		System.out.println("user is on google search page");
	}

	@When("user inputs cucumber framework on google")
	public void user_inputs_cucumber_framework_on_google() {
		System.out.println("user inputs cucumber framework on google");
	}

	@When("user clicks on search button")
	public void user_clicks_on_search_button() {
		System.out.println("user clicks on search button");
	}

	@Then("cucumber framework search results page displayed")
	public void cucumber_framework_search_results_page_displayed() {
		System.out.println("cucumber framework search results page displayed");
	}

}
