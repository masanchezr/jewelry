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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "straps")
public class StrapEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idstrap")
	private Long idstrap;

	@Column(name = "amount")
	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "numsale")
	private Long numsale;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/** The pay. */
	@JoinColumn(name = "IDPAYMENT")
	@ManyToOne
	private PaymentEntity payment;

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

	public PaymentEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}
}
