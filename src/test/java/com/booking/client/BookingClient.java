package com.booking.client;

import com.booking.models.Booking;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;


public class BookingClient {
	
	private static final String BASE_URL = "https://automationintesting.online/api";
	private static final String BOOKING_ENDPOINT = "/booking";
	
	//POST - Create a Booking
	public Response createBooking(Booking booking) {
		
		return given().log().all().contentType("application/json").body(booking)
				.when().post(BASE_URL + BOOKING_ENDPOINT);
		
	}
	
	//GET - Retrieve a Booking with Token
	public Response getBooking(int bookingId, String token) {
		
	    return given().log().all().cookie("token", token).when()
	            .get(BASE_URL + BOOKING_ENDPOINT + "/" + bookingId);
	}
	
	//PUT - Update a Booking
	public Response updateBooking(int bookingID, Booking booking, String token) {
		return given().log().all().contentType(ContentType.JSON)
	            .accept(ContentType.JSON).cookie("token", token).when().body(booking)
				.when().put(BASE_URL + BOOKING_ENDPOINT + "/" + bookingID);
		
	}
	
	//DELETE - Delete the Booking
	public Response deleteBooking(int bookingID, String token) {
		return given().log().all().cookie("token", token).when()
				.delete(BASE_URL + BOOKING_ENDPOINT + "/" + bookingID);	
	}
	
	//GET - Retrieve a Booking without Authentication Token
	public Response getBookingWithoutAuth(int bookingId) {
		
	    return given().log().all().when()
	            .get(BASE_URL + BOOKING_ENDPOINT + "/" + bookingId);
	}
	
	//PUT - Update a Booking without Authentication Token
	public Response updateBookingWithoutAuth(int bookingID, Booking booking) {
		return given().log().all().contentType(ContentType.JSON)
		            .accept(ContentType.JSON).when().body(booking)
					.when().put(BASE_URL + BOOKING_ENDPOINT + "/" + bookingID);
			
		}
		
	//DELETE - Delete the Booking without Authentication Token
	public Response deleteBookingWithoutAuth(int bookingID) {
		return given().log().all().when()
					.delete(BASE_URL + BOOKING_ENDPOINT + "/" + bookingID);	
		}
	

}
