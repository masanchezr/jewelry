package com.je.services.discounts;

import com.je.dbaccess.entities.PlaceEntity;

public class Discount {

	private Long numsale;

	private String sdiscount;

	private PlaceEntity place;

	public Long getNumsale() {
		return numsale;
	}

	public void setNumsale(Long numsale) {
		this.numsale = numsale;
	}

	public String getSdiscount() {
		return sdiscount;
	}

	public void setSdiscount(String discount) {
		this.sdiscount = discount;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

}
