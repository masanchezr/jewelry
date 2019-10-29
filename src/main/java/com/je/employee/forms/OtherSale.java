package com.je.employee.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.TypeOfSaleEntity;
import com.je.forms.Payment;

public class OtherSale {

	private Long idothersale;

	private BigDecimal amount;

	@CreatedDate
	private Date creationdate;

	private Long numsale;

	private PlaceEntity place;

	private Payment pay;

	private String description;

	private TypeOfSaleEntity type;

	public Long getIdothersale() {
		return idothersale;
	}

	public void setIdothersale(Long idrecording) {
		this.idothersale = idrecording;
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

	public TypeOfSaleEntity getType() {
		return type;
	}

	public void setType(TypeOfSaleEntity type) {
		this.type = type;
	}

}
