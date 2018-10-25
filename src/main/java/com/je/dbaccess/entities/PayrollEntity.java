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
@Table(name = "payroll")
public class PayrollEntity {

	@Id
	@Column(name = "idpayroll")
	private Long idpayroll;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	public Long getIdpayroll() {
		return idpayroll;
	}

	public void setIdpayroll(Long idpayroll) {
		this.idpayroll = idpayroll;
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
