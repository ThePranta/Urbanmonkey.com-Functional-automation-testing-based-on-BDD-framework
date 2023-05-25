@tag
Feature: This is the Login test Feature file for UrbanMonkey.com

  Scenario: Positive Login sceanario
    Given User navigates to the UrbanMonkey.com landing page 
    When User close the subscription popup
    When User click on the profile icon
    Then User logins with the valida credentials
    Then User validates the ProfileHolder name from the Dashboard
    Then User click on the Logout
    And User Validate the landing page again By checking Login text from slider

