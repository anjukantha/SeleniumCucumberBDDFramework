@googleSearch
Feature: Google Search

Scenario: Search on Google with query = cucumber framework
  Given user is on google search page
  When user inputs "cucumber framework" on google
  And user clicks on search button
  Then cucumber framework search results page displayed