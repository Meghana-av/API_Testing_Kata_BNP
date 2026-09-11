package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.models.Booking;
import com.booking.models.BookingDates;
import com.booking.service.AuthService;
import com.booking.utils.ConfigReader;
import com.booking.utils.TestDataFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import com.booking.client.AuthClient;
import com.booking.models.AuthRequest;


import static org.hamcrest.Matchers.*;


public class BookingSteps {
	private BookingClient bookingClient = new BookingClient();
	private Booking bookingRequest;
	private Response apiResponse;
	private int bookingId;
	private AuthService authService = new AuthService();
	private String authToken;
	private String updatedFirstName;
	private String updatedLastName;
	
	@Given("I have valid booking details for {string} {string} {string} {string} {string} {string}")
	public void iHaveValidBookingDetails(String roomid, String firstName, 
			String lastName, String depositPaid,String email, String phone)  	{		
		
		bookingRequest = TestDataFactory.createBooking(Integer.parseInt(roomid), firstName, lastName,
				Boolean.parseBoolean(depositPaid), email, phone);				
		
	}	
	
	//POST	- Create a Booking
	@When("I create the booking")
	public void iCreateBooking() {
		apiResponse = bookingClient.createBooking(bookingRequest);
	}
	
	//Validate POST
	@Then("the booking should be created successfully")
	public void theBookingShouldBeCreatedSuccessfully() {
		
		apiResponse.then().statusCode(201);
		bookingId = apiResponse.jsonPath().getInt("bookingid");
	}
	
	//Validate POST Response
	@Then("the response should contain the booking details")
	public void theResponseShouldContainBookingDetails() {
		
		apiResponse.then().statusCode(201)
			.body("bookingid", equalTo(bookingId))
			.body("firstname", equalTo(bookingRequest.getFirstname()))
			.body("lastname", equalTo(bookingRequest.getLastname()))
			.body("roomid",equalTo(bookingRequest.getRoomid()))
			.body("depositpaid", equalTo(bookingRequest.isDepositpaid()))
			.body("bookingdates.checkin", equalTo(bookingRequest.getBookingdates().getCheckin()))
			.body("bookingdates.checkout", equalTo(bookingRequest.getBookingdates().getCheckout()));
	}
	
	
	//GET
	@When("I retrieve the booking")
    public void iRetrieveTheBooking() {
		
		String username = ConfigReader.get("auth.username");
		String password = ConfigReader.get("auth.password");
		
		AuthRequest authRequest =
	            new AuthRequest(username, password);

	    authToken = authService.getAuthToken();

	    apiResponse = bookingClient.getBooking(bookingId, authToken);
    }
	
	 // Validate GET
    @Then("the booking details should be returned")
    public void theBookingDetailsShouldBeReturned() {

        apiResponse.then()
                .statusCode(200)
                .body("bookingid", equalTo(bookingId))
                .body("firstname",
                        equalTo(bookingRequest.getFirstname()))
                .body("lastname",
                        equalTo(bookingRequest.getLastname()))
                .body("roomid",
                        equalTo(bookingRequest.getRoomid()))
                .body("depositpaid",
                        equalTo(bookingRequest.isDepositpaid()))
                .body("bookingdates.checkin",
                        equalTo(bookingRequest.getBookingdates().getCheckin()))
                .body("bookingdates.checkout",
                        equalTo(bookingRequest.getBookingdates().getCheckout()));
    }
    
    //UPDATE
    @When("I update the booking {string} {string}")
    public void iUpdateTheBooking(String updatedFirstname, String updatedLastname) {
    	
    	this.updatedFirstName = updatedFirstname;
    	this.updatedLastName = updatedLastname;
    	
    	bookingRequest.setFirstname(updatedFirstname);
    	bookingRequest.setLastname(updatedLastname);
    	
    	BookingDates updatedDates = TestDataFactory.generateFutureBookingDates();
    	
    	bookingRequest.setBookingdates(updatedDates);
    	
    	apiResponse = bookingClient.updateBooking(bookingId, bookingRequest, authToken);
    	
    }
    
    //Validate Update
    @Then("the booking should be updated successfully")
    public void thenTheBookingShouldBeUpdatedSuccessfully() {
    	
    	
		apiResponse.then().statusCode(200).body("bookingid", equalTo(bookingId)).body("booking.firstname", equalTo(updatedFirstName))
    		.body("booking.lastname", equalTo(updatedLastName));
    }
    
    @Then("the updated booking details should be returned")
    public void theUpdatedBookingDetailsShouldBeReturned() {

        apiResponse.then()
                .statusCode(200)
                .body("bookingid", equalTo(bookingId))
                .body("booking.firstname", equalTo(updatedFirstName))
                .body("booking.lastname", equalTo(updatedLastName))
                .body("booking.roomid", equalTo(bookingRequest.getRoomid()))
                .body("booking.depositpaid", equalTo(bookingRequest.isDepositpaid()))
                .body("booking.bookingdates.checkin",
                        equalTo(bookingRequest.getBookingdates().getCheckin()))
                .body("booking.bookingdates.checkout",
                        equalTo(bookingRequest.getBookingdates().getCheckout()));
    }
    	
}
