package com.atmj.jsboot.services.rentals;

public class Rental {

	private String rentaldate;

	private String amount;

	private String user;

	public String getRentaldate() {
		return rentaldate;
	}

	public void setRentaldate(String rentaldate) {
		this.rentaldate = rentaldate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
