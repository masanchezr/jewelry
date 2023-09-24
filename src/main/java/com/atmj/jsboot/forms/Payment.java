package com.atmj.jsboot.forms;

public class Payment {

	private Long idpayment;

	private String name;

	private boolean active;

	/**
	 * Gets the idpayment.
	 *
	 * @return the idpayment
	 */
	public Long getIdpayment() {
		return idpayment;
	}

	/**
	 * Sets the idpayment.
	 *
	 * @param idpayment the idpayment to set
	 */
	public void setIdpayment(Long idpayment) {
		this.idpayment = idpayment;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks if is active.
	 *
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
