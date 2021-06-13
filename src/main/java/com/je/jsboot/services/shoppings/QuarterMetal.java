package com.je.jsboot.services.shoppings;

import java.math.BigDecimal;

import com.je.jsboot.dbaccess.entities.MetalEntity;

/**
 * The Class QuarterMetal. Sólo para mostrar, no es formulario.
 */
public class QuarterMetal {

	/** The material. */
	private MetalEntity material;

	/** The grossgrams. */
	private BigDecimal grossgrams;

	/** The netgrams. */
	private BigDecimal netgrams;

	/** The realgrams. */
	private BigDecimal realgrams;

	/** The amount. */
	private BigDecimal amount;

	/**
	 * Gets the metal.
	 *
	 * @return the metal
	 */
	public MetalEntity getMetal() {
		return material;
	}

	/**
	 * Sets the metal.
	 *
	 * @param material the new metal
	 */
	public void setMetal(MetalEntity material) {
		this.material = material;
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
	 * Gets the netgrams.
	 *
	 * @return the netgrams
	 */
	public BigDecimal getNetgrams() {
		return netgrams;
	}

	/**
	 * Sets the netgrams.
	 *
	 * @param netgrams the new netgrams
	 */
	public void setNetgrams(BigDecimal netgrams) {
		this.netgrams = netgrams;
	}

	/**
	 * Gets the realgrams.
	 *
	 * @return the realgrams
	 */
	public BigDecimal getRealgrams() {
		return realgrams;
	}

	/**
	 * Sets the realgrams.
	 *
	 * @param realgrams the new realgrams
	 */
	public void setRealgrams(BigDecimal realgrams) {
		this.realgrams = realgrams;
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
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
