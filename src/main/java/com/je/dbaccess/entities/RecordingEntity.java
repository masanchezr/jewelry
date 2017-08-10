package com.je.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recordings")
public class RecordingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDRECORDING")
	private Long idrecording;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The idsale. */
	@Column(name = "NUMSALE")
	private Long numsale;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	@Column(name = "DESCRIPTION")
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

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
