package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.utils.constants.Constants;

@Entity
@Table(name = "rentals")
public class RentalEntity {

	@Id
	@Column(name = "idrental")
	private Long idrental;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

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

	public Long getIdrental() {
		return idrental;
	}

	public void setIdrental(Long idrental) {
		this.idrental = idrental;
	}
}
