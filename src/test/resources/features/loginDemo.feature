Feature: test login functionality
  @loginDemo
  Scenario: check login is successful with valid credentials
    Given user is on sauce demo login
    When user provides a valid user name
    And user provides valid password
    And user clicks on login button
    Then verify user successfully logged in

    @loginInvalid
    Scenario: check user sees an error message with invalid credentials
      Given user is on sauce demo login
      When user provides a invalid user name
      And user provides invalid password
      And user clicks on login button
      Then verify user sees an error message
