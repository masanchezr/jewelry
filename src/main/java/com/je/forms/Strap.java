package com.je.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.je.dbaccess.entities.PlaceEntity;

public class Strap {

	private Long idstrap;

	private BigDecimal amount;

	@CreatedDate
	private Date creationdate;

	private Long numsale;

	private PlaceEntity place;

	private Payment payment;

	public Long getIdstrap() {
		return idstrap;
	}

	public void setIdstrap(Long idstrap) {
		this.idstrap = idstrap;
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

	public Long getNumsale() {
		return numsale;
	}

	public void setNumsale(Long numsale) {
		this.numsale = numsale;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
