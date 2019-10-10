package com.je.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.je.dbaccess.entities.PlaceEntity;

public class Recording {

	private Long idrecording;

	private BigDecimal amount;

	@CreatedDate
	private Date creationdate;

	private Long numsale;

	private PlaceEntity place;

	private Payment pay;

	private String description;

	public Long getIdrecording() {
		return idrecording;
	}

	public void setIdrecording(Long idrecording) {
		this.idrecording = idrecording;
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

	public Payment getPay() {
		return pay;
	}

	public void setPay(Payment pay) {
		this.pay = pay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
