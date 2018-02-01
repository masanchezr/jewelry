package com.je.services.workshop;

import java.math.BigDecimal;

import com.je.dbaccess.entities.MetalEntity;

/**
 * The Class Workshop.
 */
public class Workshop {

	/** The amount. */
	private BigDecimal amount;

	/** The description. */
	private String description;

	/** The grams. */
	private BigDecimal grams;

	private MetalEntity material;

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

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public MetalEntity getMetal() {
		return material;
	}

	public void setMetal(MetalEntity material) {
		this.material = material;
	}
}
