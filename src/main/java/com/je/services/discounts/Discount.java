package com.je.services.discounts;

import java.math.BigDecimal;

import com.je.dbaccess.entities.PlaceEntity;

public class Discount {
	private Long iddiscount;
	private BigDecimal discount;

	private PlaceEntity place;

	public Long getIddiscount() {
		return iddiscount;
	}

	public void setIddiscount(Long iddiscount) {
		this.iddiscount = iddiscount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

}
