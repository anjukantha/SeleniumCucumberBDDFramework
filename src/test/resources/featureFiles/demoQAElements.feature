@DemoQA
Feature: demoqa Elements page

Scenario: Input Only Full Name on Text Box

	Given user is on demoqa application
	And user clicks on Elements
	And user clicks on Text Box
	When input Full Name as "Rahul Kumar"
	And click on Submit button
	Then data should be submitted successfully