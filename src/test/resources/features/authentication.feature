Feature: Authentication API

@auth
    Scenario Outline: Login successfully with valid credentials
        Given I have valid authentication credentials "<username>" "<password>"
        When I Send the Login request
        Then Login should be successful
        
   Examples:
      | username  | password  |
      | admin     | password  |
    
    
 @negativeauth
 
  	Scenario Outline: Login successfully with invalid credentials
  	    Given I have invalid authentication credentials "<username>" "<password>"
  	    When I Send the Login request
  	    Then Login should fail
    

    Examples:
      | username  | password  |
      | admin1     | password1  |
     
      
