package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "salespayment")
public class SalesPayments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDSALEPAYMENT")
	private Long idsalepayment;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The sale. */
	@ManyToOne
	@JoinColumn(name = "IDSALE")
	private SaleEntity sale;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	public Long getIdsalepayment() {
		return idsalepayment;
	}

	public void setIdsalepayment(Long idsalepayment) {
		this.idsalepayment = idsalepayment;
	}

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
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
}
