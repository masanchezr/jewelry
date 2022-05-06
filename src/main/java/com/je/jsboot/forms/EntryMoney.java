package com.je.jsboot.forms;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.utils.constants.ConstantsViews;

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
