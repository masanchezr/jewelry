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
@Table(name = "installments")
public class InstallmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDINSTALLMENT")
	private Long idinstallment;

	@ManyToOne
	@JoinColumn(name = "idsalepostponed")
	private SalePostponedEntity salepostponed;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "creationdate")
	private Date creationdate;

	public Long getIdinstallment() {
		return idinstallment;
	}

	public void setIdinstallment(Long idinstallment) {
		this.idinstallment = idinstallment;
	}

	public SalePostponedEntity getSalepostponed() {
		return salepostponed;
	}

	public void setSalepostponed(SalePostponedEntity salepostponed) {
		this.salepostponed = salepostponed;
	}

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
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
}
