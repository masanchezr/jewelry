package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paymentshop")
public class PaymentShopEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPAYMENTSHOP")
	private Long idpaymentshop;

	/** The pay. */
	@JoinColumn(name = "IDPAYMENT", referencedColumnName = "IDPAYMENT")
	@ManyToOne(fetch = FetchType.EAGER)
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
