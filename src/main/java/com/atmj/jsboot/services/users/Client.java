package com.atmj.jsboot.services.users;

/**
 * The Class Client.
 */
public class Client {

	/** The nifclient. */
	private String nifclient;

	/** The email. */
	private String email;

	/** The nameuser. */
	private String name;

	/** The address. */
	private String address;

	/** The telephone. */
	private Long telephone;

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
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the nameuser.
	 * 
	 * @return the nameuser
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the nameuser.
	 * 
	 * @param nameuser
	 *            the new nameuser
	 */
	public void setName(String nameuser) {
		this.name = nameuser;
	}

	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the nifclient.
	 * 
	 * @return the nifclient
	 */
	public String getNifclient() {
		return nifclient;
	}

	/**
	 * Sets the nifclient.
	 * 
	 * @param nifclient
	 *            the new nifclient
	 */
	public void setNifclient(String nifclient) {
		this.nifclient = nifclient;
	}

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
}
