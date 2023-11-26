package com.atmj.jsboot.employee.forms;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.TypeOfSaleEntity;
import com.atmj.jsboot.forms.Payment;
import com.atmj.jsboot.utils.constants.ConstantsViews;

public class OtherSale {

	private Long idothersale;

	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
	@DecimalMin(value = "0.1", message = ConstantsViews.ERRORSELECTAMOUNT)
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
