package com.je.jsboot.services.adjustments;

import java.math.BigDecimal;

import com.je.jsboot.dbaccess.entities.PaymentEntity;

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
	private BigDecimal amountwork;

	/** The recommendedprice. */
	private BigDecimal recommendedprice;

	/** The grams. */
	private BigDecimal grams;

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
	 * @param idadjustment the new idadjustment
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
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountwork() {
		return amountwork;
	}

	public void setAmountwork(BigDecimal amountwork) {
		this.amountwork = amountwork;
	}

	public BigDecimal getRecommendedprice() {
		return recommendedprice;
	}

	public void setRecommendedprice(BigDecimal recommendedprice) {
		this.recommendedprice = recommendedprice;
	}

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}
}
