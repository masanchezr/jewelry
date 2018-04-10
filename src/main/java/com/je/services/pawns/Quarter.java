package com.je.services.pawns;

import java.math.BigDecimal;

import com.je.dbaccess.entities.PlaceEntity;

public class Quarter {

	private BigDecimal gramsreal;

	private BigDecimal grossgrams;

	private BigDecimal amount;

	private PlaceEntity place;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getGramsreal() {
		return gramsreal;
	}

	public void setGramsreal(BigDecimal gramsreal) {
		this.gramsreal = gramsreal;
	}

	public BigDecimal getGrossgrams() {
		return grossgrams;
	}

	public void setGrossgrams(BigDecimal grossgrams) {
		this.grossgrams = grossgrams;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}
}
