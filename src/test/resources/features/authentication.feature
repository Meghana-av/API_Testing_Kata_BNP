@auth
Feature: Authentication API

  Scenario: Login successfully with valid credentials
    Given I have valid authentication credentials "<username>" "<password>"
    When I Send the Login request
    Then Login should be successful
    
    Examples:
      | username | password |
      | admin    | password |
      
