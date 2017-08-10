package com.je.services.rentals;

import java.math.BigDecimal;

public class Rental {

	private String rentaldate;

	private BigDecimal amount;

	private String user;

	public String getRentaldate() {
		return rentaldate;
	}

	public void setRentaldate(String rentaldate) {
		this.rentaldate = rentaldate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
