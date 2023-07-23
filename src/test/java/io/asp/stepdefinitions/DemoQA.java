package io.asp.stepdefinitions;

import static com.asp.enums.LogType.PASS;
import static com.asp.reports.Logger.log;
import static org.assertj.core.api.Assertions.assertThat;

import com.asp.driver.Driver;
import com.asp.driver.DriverManager;
import com.asp.enums.ConfigProperties;
import com.asp.pages.DemoQAPage;
import com.asp.utils.PropertyUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DemoQA {

	DemoQAPage dp = new DemoQAPage();

	@Before
	public void setup() {
		Driver.initDriver();
	}

	@After
	public void quit() {
		Driver.quitDriver();
	}

	@Given("user is on demoqa application")
	public void user_is_on_demoqa_application() {
		DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.DEMOQA_URL));
		log(PASS, "User navigated to Demo QA page");
	}

	@Given("user clicks on Elements")
	public void user_clicks_on_elements() {
		dp.clickElements();
	}

	@Given("user clicks on Text Box")
	public void user_clicks_on_text_box() {
		dp.clickTextBox();
	}

	@When("input Full Name as {string}")
	public void input_full_name(String name) {
		dp.inputFullName(name);
	}

	@When("click on Submit button")
	public void click_on_submit_button() {
		dp.clickSubmitButton();
	}

	@Then("data should be submitted successfully")
	public void data_should_be_submitted_successfully() {
		assertThat(dp.isOutputDisplayed()).isTrue();
		log(PASS, "Data submitted successfully.");
	}

}
