package com.booking.models;


public class Booking {
	

	private int roomid;
	private String firstname;
	private String lastname;
	private BookingDates bookingDates;
	private boolean depositpaid;
	private String email;
	private String phone;
	
	public Booking() {
		
	}

	public Booking(int roomid, String firstname, String lastname, boolean depositpaid, BookingDates bookingDates, String email,
			String phone) {
		// TODO Auto-generated constructor stub
		
		this.roomid = roomid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.depositpaid = depositpaid;
		this.bookingDates = bookingDates;
		this.email = email;
		this.phone = phone;
	}
	
	
	public int getRoomid() {
		return roomid;
	}
	
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingDates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingDates = bookingdates;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
