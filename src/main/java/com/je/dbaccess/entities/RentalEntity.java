package com.je.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.je.utils.constants.Constants;

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
