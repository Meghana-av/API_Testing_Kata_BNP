package com.booking.utils;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.booking.models.BookingDates;

public class TestDataFactory {
	
	private TestDataFactory() {
		//utility class
	}
	
	public static BookingDates generateFutureBookingDates() {
		
		int randomDays = ThreadLocalRandom.current().nextInt(30, 365);
		
		LocalDate checkin = LocalDate.now().plusDays(randomDays);
		LocalDate checkout = checkin.plusDays(3);
		
		return new BookingDates (checkin.toString(), checkout.toString());
				

	}
}
