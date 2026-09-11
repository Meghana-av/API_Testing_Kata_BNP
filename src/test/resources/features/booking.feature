Feature: Booking API

@booking
	Scenario Outline: Create, retrieve, update and delete a booking successfully

    	Given I have booking details for "<roomid>" "<firstName>" "<lastName>" "<depositPaid>" "<email>" "<phone>"

    	When I create the booking
    	Then the booking should be created successfully
    	And the response should contain the booking details

    	When I retrieve the booking
    	Then the booking details should be returned
    
    	When I update the booking "<updatedFirstName>" "<updatedLastName>"
		Then the booking should be updated successfully
		And the updated booking details should be returned
	
		When I delete the booking
		Then the booking details should be deleted
		And the booking should no longer exist

    Examples:
      | roomid | firstName | lastName | depositPaid | email                   | phone       | updatedFirstName | updatedLastName |
      | 2      | John      | Doe      | true        | john.doe@example.com    | 12345678901 | megjohn          | upDoemeg        |
     
      
@negativebooking
	Scenario Outline: Create a booking with invalid mandatory field

    	Given I have booking details for "<roomid>" "<firstName>" "<lastName>" "<depositPaid>" "<email>" "<phone>"
    	When I create the booking
    	Then the booking creation should fail with status code 400

    	
    Examples:
      | roomid | firstName | lastName | depositPaid | email                   | phone       |
      | 2      |           | Doe      | true        | john.doe@example.com    |             |
          

    Scenario Outline: Retrieve a booking without Authentication
    
        Given I have booking details for "<roomid>" "<firstName>" "<lastName>" "<depositPaid>" "<email>" "<phone>"
        When I create the booking
        Then the booking should be created successfully
        And the response should contain the booking details
        
        When I retrieve the booking without authentication
        Then the booking retrieval should be forbidden
        
        
    Examples:
      | roomid | firstName | lastName | depositPaid | email                   | phone       | 
      | 2      | John      | Doe      | true        | john.doe@example.com    | 12345678901 | 
  

    Scenario Outline: Update a booking without Authentication
    
        Given I have booking details for "<roomid>" "<firstName>" "<lastName>" "<depositPaid>" "<email>" "<phone>"
        When I create the booking
        Then the booking should be created successfully
        And the response should contain the booking details
        
        When I update the booking without authentication
        Then the booking update should be forbidden
        
        
    Examples:
      | roomid | firstName | lastName | depositPaid | email                   | phone       | 
      | 2      | John      | Doe      | true        | john.doe@example.com    | 12345678901 |
      
      

    Scenario Outline: delete a booking without Authentication
    
        Given I have booking details for "<roomid>" "<firstName>" "<lastName>" "<depositPaid>" "<email>" "<phone>"
        When I create the booking
        Then the booking should be created successfully
        And the response should contain the booking details
        
        When I delete the booking without authentication
        Then the booking delete should be forbidden
        
        
    Examples:
      | roomid | firstName | lastName | depositPaid | email                   | phone       | 
      | 2      | John      | Doe      | true        | john.doe@example.com    | 12345678901 |