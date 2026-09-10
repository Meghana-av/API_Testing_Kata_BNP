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
	
	
	@Given("I have valid booking details")
	public void iHaveValidBookingDetails() {		
		
		BookingDates bookingDates = TestDataFactory.generateFutureBookingDates();
		
		bookingRequest = new Booking(2, "John", "Doe", true, bookingDates, "John.doe@example.com", "12345678901");
		
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
			.body("firstname", equalTo("John"))
			.body("lastname", equalTo("Doe"))
			.body("roomid",equalTo(2))
			.body("depositpaid", equalTo(true))
			.body("bookingdates.checkin", equalTo(bookingRequest.getBookingdates().getCheckin()))
			.body("bookingdates.checkout", equalTo(bookingRequest.getBookingdates().getCheckout()));
	}

}
