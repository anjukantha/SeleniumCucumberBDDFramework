package io.asp.hooks;

import com.asp.driver.Driver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	@Before
	public void setup(Scenario scenario) {
		Driver.initDriver();
	}

	@After
	public void quit() {
		Driver.quitDriver();
	}
	
}
