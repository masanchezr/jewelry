package com.je.services.workshop;

import java.math.BigDecimal;
import java.util.Date;

import com.je.dbaccess.entities.MetalEntity;

/**
 * The Class Billing.
 */
public class Billing {

	/** The amount. */
	private BigDecimal amount;

	/** The description. */
	private String description;

	/** The idadjustment. */
	private long idadjustment;

	private Date creationdate;

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

	/**
	 * Gets the idadjustment.
	 *
	 * @return the idadjustment
	 */
	public long getIdadjustment() {
		return idadjustment;
	}

	/**
	 * Sets the idadjustment.
	 *
	 * @param idadjustment the new idadjustment
	 */
	public void setIdadjustment(long idadjustment) {
		this.idadjustment = idadjustment;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
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
