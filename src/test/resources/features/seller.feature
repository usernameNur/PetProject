Feature: seller api testing
  @allSellers @regression
  Scenario: get all sellers and verify email is not empty
    Given user hits get all seller api with "api/myaccount/sellers"
    Then verify email is not empty
@sellerEmail @regression
    Scenario: get a single seller, change email, verify email was changed
      Given user hits get single seller with "api/myaccount/sellers/"
      Then verify single seller email is not empty
      And hit put api with "api/myaccount/sellers/" and change email to "whatever@gmail.com"
      Then verify email was changed






