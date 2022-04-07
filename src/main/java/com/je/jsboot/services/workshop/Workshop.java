package com.je.jsboot.services.workshop;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.utils.constants.ConstantsViews;

/**
 * The Class Workshop.
 */
public class Workshop {

	/** The amount. */
	@DecimalMin(value = "0.1", message = ConstantsViews.ERRORSELECTAMOUNT)
	private BigDecimal amount;

	/** The description. */
	@NotNull(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	@NotEmpty(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	private String description;

	/** The grams. */
	private BigDecimal grams;

	private MetalEntity metal;

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
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public MetalEntity getMetal() {
		return metal;
	}

	public void setMetal(MetalEntity metal) {
		this.metal = metal;
	}
}
