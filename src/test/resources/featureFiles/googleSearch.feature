@googleSearch
Feature: Google Search

  Scenario: Search on Google with query = cucumber framework
    Given user is on google search page
    When user inputs "cucumber framework" on google
    And user clicks on search button
    Then "cucumber framework" search results page displayed

  Scenario Outline: Search on Google with multiple data set
    Given user is on google search page
    When user inputs "<query>" on google
    And user clicks on search button
    Then "<query>" search results page displayed

    Examples: 
      | query               |
      | Automation          |
      | Selenium Automation |
      | Cucumber Automation |

  Scenario: Search on Google with specified data
    Given user is on google search page
    When user inputs data on google
      | Facebook |
    And user clicks on search button
    Then data search results page displayed
      | Facebook |
