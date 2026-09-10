Feature: Booking API

Scenario: Create booking successfully
Given I have valid booking details for "<roomid>" "<firstName>" "<lastName>" "<depositPaid>" "<email>" "<phone>"
When I create the booking
Then the booking should be created successfully
And the response should contain the booking details

Examples:
|roomid | firstName | lastName | depositPaid | email                    | phone       |
| 2     | John      | Doe      | true        | john.doe@example.com     | 12345678901 |
| 3     | Alice     | Smith    | false       | alice.smith@example.com  | 12345678902 |
| 2     | David     | Brown    | true        | david.brown@example.com  | 12345678903 |