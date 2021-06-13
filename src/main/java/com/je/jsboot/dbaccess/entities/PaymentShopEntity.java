package com.je.jsboot.dbaccess.entities;

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
@Table(name = "paymentshop")
public class PaymentShopEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPAYMENTSHOP")
	private Long idpaymentshop;

	/** The pay. */
	@JoinColumn(name = "IDPAYMENT")
	@ManyToOne
	private PaymentEntity payment;

	/** The amount. */
	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The shop. */
	@ManyToOne
	@JoinColumn(name = "IDSHOP")
	private ShoppingEntity shop;

	/**
	 * Gets the payment.
	 *
	 * @return the payment
	 */
	public PaymentEntity getPayment() {
		return payment;
	}

	/**
	 * Sets the payment.
	 *
	 * @param payment the new payment
	 */
	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}

	public Long getIdpaymentshop() {
		return idpaymentshop;
	}

	public void setIdpaymentshop(Long idpaymentshop) {
		this.idpaymentshop = idpaymentshop;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public ShoppingEntity getShop() {
		return shop;
	}

	public void setShop(ShoppingEntity shop) {
		this.shop = shop;
	}
}
