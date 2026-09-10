package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.Booking;
import com.booking.models.BookingDates;
import com.booking.utils.TestDataFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


import static org.hamcrest.Matchers.*;


public class BookingSteps {
	private BookingClient bookingClient = new BookingClient();
	private Booking bookingRequest;
	private Response apiResponse;
	
	
	@Given("I have valid booking details for {string} {string} {string} {string} {string} {string}")
	public void iHaveValidBookingDetails(String roomid, String firstName, 
			String lastName, String depositPaid,String email, String phone) {		
		
		BookingDates bookingDates = TestDataFactory.generateFutureBookingDates();
		
		bookingRequest = TestDataFactory.createBooking(Integer.parseInt(roomid), firstName, lastName,
				Boolean.parseBoolean(depositPaid), email, phone);				
		
	}	
	
	@When("I create the booking")
	public void iCreateBooking() {
		
		apiResponse = bookingClient.createBooking(bookingRequest);
	}
	
	@Then("the booking should be created successfully")
	public void theBookingShouldBeCreatedSuccessfully() {
		
		apiResponse.then().statusCode(201);
	}
	
	@Then("the response should contain the booking details")
	public void theResponseShouldContainBookingDetails() {
		
		apiResponse.then()
			.body("bookingid", notNullValue()).body("bookingid", greaterThan(0))
			.body("firstname", equalTo(bookingRequest.getFirstname()))
			.body("lastname", equalTo(bookingRequest.getLastname()))
			.body("roomid",equalTo(bookingRequest.getRoomid()))
			.body("depositpaid", equalTo(bookingRequest.isDepositpaid()))
			.body("bookingdates.checkin", equalTo(bookingRequest.getBookingdates().getCheckin()))
			.body("bookingdates.checkout", equalTo(bookingRequest.getBookingdates().getCheckout()));
	}

}
