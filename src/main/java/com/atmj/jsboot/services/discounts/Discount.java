package com.atmj.jsboot.services.discounts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.utils.constants.ConstantsViews;

public class Discount {

	@NotNull(message = ConstantsViews.ERRORNUMDISCOUNT)
	private Long numsale;

	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
	@NotEmpty(message = ConstantsViews.ERRORSELECTAMOUNT)
	@NotBlank(message = ConstantsViews.ERRORSELECTAMOUNT)
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
