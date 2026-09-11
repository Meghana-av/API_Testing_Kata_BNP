package com.booking.client;

import com.booking.models.Booking;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class BookingClient {
	
	private static final String BASE_URL = "https://automationintesting.online/api";
	private static final String BOOKING_ENDPOINT = "/booking";
	
	//Post - Create a Booking
	public Response createBooking(Booking booking) {
		
		return given().log().all().contentType("application/json").body(booking)
				.when().post(BASE_URL + BOOKING_ENDPOINT);
		
	}
	
	//Get - Retrieve a Booking
	public Response getBooking(int bookingId, String token) {
		
	    return given().log().all().cookie("token", token).when()
	            .get(BASE_URL + BOOKING_ENDPOINT + "/" + bookingId);
	}

}
