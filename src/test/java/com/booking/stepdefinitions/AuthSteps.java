package com.booking.stepdefinitions;

import com.booking.client.AuthClient;
import com.booking.models.AuthRequest;
import com.booking.models.Booking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class AuthSteps {
	private AuthClient authClient = new AuthClient();
	private AuthRequest authRequest;
	private Response apiResponse;
	private String authToken;
	
	@Given("I have valid authentication credentials {string} {string}")
	public void iHaveValidAuthenticationCredentials(String username, String password) {
		
		authRequest = new AuthRequest(username, password);
	}
	
	@When("I Send the login request")
	public void iSendTheLoginRequest() {
		apiResponse = authClient.login(authRequest);
	}
	
	@Then("login should be successful")
	public void loginShouldBeSuccessful() {
		apiResponse.then().statusCode(200).body("token", not(isEmptyOrNullString()));
		authToken = apiResponse.jsonPath().getString("token");
	}
	
	
	  
    @Given("I have invalid authentication credentials {string} {string}")
    public void iHaveInvalidAuthenticationCredentials(String username, String password) {
    	authRequest = new AuthRequest(username, password);
    }
    		
   
    @Then("login should fail")
    public void loginShouldFail() {
    	apiResponse.then().statusCode(401).body("error", equalTo("Invalid credentials"));
    }

}
