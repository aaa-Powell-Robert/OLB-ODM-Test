Feature: ODMRestAPITest

  Testing ODM with Rest API calls.
  
  Scenario Outline: validate email, zipcode, and response
  
    Given User email "<email>" and zipcode "<zipcode>"
    When I send JSON as POST to endpoint
    Then I should get response code "<code>"
    And response should have email "<email>"
    And response should have zipcode "<zipcode>"
    And response should have response "<message>"
    
    Examples:
    | email                       | zipcode | code | message |
    | test@test.com               | 90210   | 200  | hello   |
    | Powell.Robert@aaa-calif.com | 91506   | 200  | hello   |
    