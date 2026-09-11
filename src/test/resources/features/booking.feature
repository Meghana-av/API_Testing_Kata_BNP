@booking
Feature: Booking API

  Scenario Outline: Create and retrieve a booking successfully

    Given I have valid booking details for "<roomid>" "<firstName>" "<lastName>" "<depositPaid>" "<email>" "<phone>"

    When I create the booking
    Then the booking should be created successfully
    And the response should contain the booking details

    When I retrieve the booking
    Then the booking details should be returned

    Examples:
      | roomid | firstName | lastName | depositPaid | email                   | phone       |
      | 2      | John      | Doe      | true        | john.doe@example.com    | 12345678901 |
  