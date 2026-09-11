package com.booking.client;

import static io.restassured.RestAssured.given;

import com.booking.models.AuthRequest;
import com.booking.models.Booking;

import io.restassured.response.Response;

public class AuthClient {
	
	private static final String BASE_URL = "https://automationintesting.online/api";
	private static final String AUTH_ENDPOINT = "/auth/login";
	
	public Response login(AuthRequest authRequest) {
		
		return given().log().all().contentType("application/json").body(authRequest)
				.when().post(BASE_URL+AUTH_ENDPOINT);
				
		
	}
	
	public String getToken(AuthRequest authRequest) {

	    Response response = login(authRequest);

	    response.then().statusCode(200);

	    return response.jsonPath().getString("token");
	}

}
