package com.atmj.jsboot.forms;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.utils.constants.ConstantsViews;

public class EntryMoney {

	private Long identrymoney;

	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
	@DecimalMin(value = "0.1", message = ConstantsViews.ERRORSELECTAMOUNT)
	private BigDecimal amount;

	@CreatedDate
	private Date creationdate;

	private PlaceEntity place;

	public Long getIdentrymoney() {
		return identrymoney;
	}

	public void setIdentrymoney(Long identrymoney) {
		this.identrymoney = identrymoney;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

}
