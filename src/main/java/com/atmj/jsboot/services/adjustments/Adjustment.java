package com.atmj.jsboot.services.adjustments;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.utils.constants.ConstantsViews;

/**
 * The Class Adjustment.
 */
public class Adjustment {

	/** The idadjustment. */
	@NotNull
	private Long idadjustment;

	/** The description. */
	@NotNull(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	@NotEmpty(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	private String description;

	private String user;

	/** The amount. */
	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
	@DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal amount;

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
}
