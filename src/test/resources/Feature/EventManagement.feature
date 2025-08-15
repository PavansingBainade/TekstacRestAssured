Feature: To Verify all the API Functionality in Booking of EventManagement

Background:
    Given User should login to get the Access Token
    And add the access token in all the endpoints
    
Scenario: Create a new booking successfully
    Given Create a valid Payload for booking
    When send a post request to add booking
    Then the booking should be added successfully
    And The passenger name should be displayed along with the details

Scenario: To view all the Bookings List
    When send a get request to view all bookings
    Then List of bookings should be displayed
    And the status code expected is 200

Scenario: Get booking by ID
    Given I have an existing booking ID
    When send a GET request to view booking by ID
    Then booking details for that ID should be displayed
    And the response status should be 200

Scenario Outline: Get bookings by train class
    Given I have a train class "<classname>"
    When send a GET request to view booking by class
    Then bookings for that class name should be displayed
    And the response status should be 200
    
    Examples:
    |classname|
    |SLEEPER|

Scenario: Delete booking by ID
    Given I have a booking ID to delete
    When I send a DELETE request to delete booking by ID
    Then the booking should be deleted successfully
    And the response status should be 200

Scenario: Create multiple bookings from Excel data
    Given read booking data from Excel "src/test/resources/Exceldata.xlsx"
    
    

