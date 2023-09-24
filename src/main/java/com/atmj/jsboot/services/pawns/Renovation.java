package com.atmj.jsboot.services.pawns;

import java.math.BigDecimal;

/**
 * The Class Renovation.
 */
public class Renovation {

	/** The numpawn. */
	private String numpawn;

	/** The renvationamount. */
	private BigDecimal renovationamount;

	/**
	 * Gets the renvationamount.
	 *
	 * @return the renvationamount
	 */
	public BigDecimal getRenovationamount() {
		return renovationamount;
	}

	/**
	 * Sets the renvationamount.
	 *
	 * @param renvationamount
	 *            the new renvationamount
	 */
	public void setRenovationamount(BigDecimal renvationamount) {
		this.renovationamount = renvationamount;
	}

	/**
	 * Gets the numpawn.
	 *
	 * @return the numpawn
	 */
	public String getNumpawn() {
		return numpawn;
	}

	/**
	 * Sets the numpawn.
	 *
	 * @param numpawn
	 *            the new numpawn
	 */
	public void setNumpawn(String numpawn) {
		this.numpawn = numpawn;
	}
}
