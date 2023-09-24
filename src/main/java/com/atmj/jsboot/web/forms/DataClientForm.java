package com.atmj.jsboot.web.forms;

import java.util.List;

import com.atmj.jsboot.dbaccess.entities.JewelEntity;

/**
 * The Class DataClientForm.
 */
public class DataClientForm {

	/** The jewels. */
	private List<JewelEntity> jewels;

	/** The name. */
	private String name;

	/** The nif. */
	private String nif;

	/** The email. */
	private String email;

	/** The telephone. */
	private Long telephone;

	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public Long getTelephone() {
		return telephone;
	}

	/**
	 * Sets the telephone.
	 *
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the nif.
	 *
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Sets the nif.
	 *
	 * @param nif
	 *            the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public List<JewelEntity> getJewels() {
		return jewels;
	}

	public void setJewels(List<JewelEntity> jewels) {
		this.jewels = jewels;
	}
}
