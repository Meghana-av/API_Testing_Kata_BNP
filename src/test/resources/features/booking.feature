Feature: Booking API

Scenario: Create booking successfully
Given I have valid booking details
When I create the booking
Then the booking should be created successfully
And the response should contain the booking details