package com.booking.client;

import com.booking.models.Booking;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BookingClient {
	
	private static final String BASE_URL = "https://automationintesting.online/api";
	private static final String BOOKING_ENDPOINT = "/booking";
	
	public Response createBooking(Booking booking) {
		
		return given().log().all().contentType("application/json").body(booking)
				.when().post(BASE_URL + BOOKING_ENDPOINT);
		
	}

}
