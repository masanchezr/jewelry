package com.je.services.sales;

import com.je.dbaccess.entities.PaymentEntity;

/**
 * The Class CancelSale.
 */
public class CancelSale {

	/** The amount. */
	private String amount;

	/** The numsale. */
	private long numsale;

	/** The s parcial. */
	private String parcial;

	private PaymentEntity payment;

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * Gets the s parcial.
	 *
	 * @return the s parcial
	 */
	public String getParcial() {
		return parcial;
	}

	/**
	 * Sets the s parcial.
	 *
	 * @param sParcial the new s parcial
	 */
	public void setParcial(String parcial) {
		this.parcial = parcial;
	}

	public long getNumsale() {
		return numsale;
	}

	public void setNumsale(long numsale) {
		this.numsale = numsale;
	}

	public PaymentEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}
}
