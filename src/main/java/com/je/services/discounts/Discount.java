package com.je.services.discounts;

import com.je.dbaccess.entities.PlaceEntity;

public class Discount {

	private Long iddiscount;

	private String sdiscount;

	private PlaceEntity place;

	public Long getIddiscount() {
		return iddiscount;
	}

	public void setIddiscount(Long iddiscount) {
		this.iddiscount = iddiscount;
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
