package com.je.services.pawns;

import java.math.BigDecimal;

import com.je.dbaccess.entities.MetalEntity;

/**
 * The Class Quarter. Sólo para mostrar, no es formulario.
 */
public class Quarter {

	/** The gramsreal. */
	private BigDecimal gramsreal;

	/** The grossgrams. */
	private BigDecimal grossgrams;

	/** The amount. */
	private BigDecimal amount;

	/** The metal. */
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
	 * Gets the gramsreal.
	 *
	 * @return the gramsreal
	 */
	public BigDecimal getGramsreal() {
		return gramsreal;
	}

	/**
	 * Sets the gramsreal.
	 *
	 * @param gramsreal the new gramsreal
	 */
	public void setGramsreal(BigDecimal gramsreal) {
		this.gramsreal = gramsreal;
	}

	/**
	 * Gets the grossgrams.
	 *
	 * @return the grossgrams
	 */
	public BigDecimal getGrossgrams() {
		return grossgrams;
	}

	/**
	 * Sets the grossgrams.
	 *
	 * @param grossgrams the new grossgrams
	 */
	public void setGrossgrams(BigDecimal grossgrams) {
		this.grossgrams = grossgrams;
	}

	/**
	 * Gets the metal.
	 *
	 * @return the metal
	 */
	public MetalEntity getMetal() {
		return metal;
	}

	/**
	 * Sets the metal.
	 *
	 * @param metal the new metal
	 */
	public void setMetal(MetalEntity metal) {
		this.metal = metal;
	}
}
