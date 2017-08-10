package com.je.services.adjustments;

import java.math.BigDecimal;

import com.je.dbaccess.entities.PaymentEntity;

/**
 * The Class Adjustment.
 */
public class Adjustment {

	/** The idadjustment. */
	private Long idadjustment;

	/** The description. */
	private String description;

	private String user;

	/** The amount. */
	private BigDecimal amount;

	/** The amountwork. */
	private double amountwork;

	/** The recommendedprice. */
	private double recommendedprice;

	/** The grams. */
	private double grams;

	private PaymentEntity payment;

	/**
	 * Gets the idadjustment.
	 *
	 * @return the idadjustment
	 */
	public Long getIdadjustment() {
		return idadjustment;
	}

	/**
	 * Sets the idadjustment.
	 *
	 * @param idadjustment
	 *            the new idadjustment
	 */
	public void setIdadjustment(Long idadjustment) {
		this.idadjustment = idadjustment;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the recommendedprice.
	 *
	 * @return the recommendedprice
	 */
	public double getRecommendedprice() {
		return recommendedprice;
	}

	/**
	 * Sets the recommendedprice.
	 *
	 * @param recommendedprice
	 *            the new recommendedprice
	 */
	public void setRecommendedprice(double recommendedprice) {
		this.recommendedprice = recommendedprice;
	}

	/**
	 * Gets the amountwork.
	 *
	 * @return the amountwork
	 */
	public double getAmountwork() {
		return amountwork;
	}

	/**
	 * Sets the amountwork.
	 *
	 * @param amountwork
	 *            the new amountwork
	 */
	public void setAmountwork(double amountwork) {
		this.amountwork = amountwork;
	}

	/**
	 * Gets the grams.
	 *
	 * @return the grams
	 */
	public double getGrams() {
		return grams;
	}

	/**
	 * Sets the grams.
	 *
	 * @param grams
	 *            the new grams
	 */
	public void setGrams(double grams) {
		this.grams = grams;
	}

	public PaymentEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
