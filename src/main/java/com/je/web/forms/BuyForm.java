package com.je.web.forms;

import java.util.List;

import com.je.dbaccess.entities.JewelEntity;

/**
 * The Class BuyForm.
 */
public class BuyForm {

	/**
	 * dejo estos campos puede que se utilizen si se quieren visualizar en la
	 * siguiente vista.
	 */
	private String name;

	/** The nif. */
	private String nif;

	/** The telephone. */
	private Long telephone;

	/** The email. */
	private String email;

	/** The jewels. */
	private List<JewelEntity> jewels;

	/** The address. */
	private String address;

	/** The city. */
	private String city;

	/** The country. */
	private String country;

	/** The postalcode. */
	private Long postalcode;

	/** The billing_same_as_shipping. */
	private int billing_same_as_shipping;

	/** The cif. */
	private String cif;

	/** The namebilling. */
	private String namebilling;

	/** The addressbilling. */
	private String addressbilling;

	/** The citybilling. */
	private String citybilling;

	/** The countrybilling. */
	private String countrybilling;

	/** The postalcodebilling. */
	private Long postalcodebilling;

	/** The payment. */
	private Long payment;
	// id direccion envio ya existente
	/** The idaddressmailing. */
	private Long idaddressmailing;
	// id dirección facturación ya existente
	/** The idaddressbilling. */
	private Long idaddressbilling;

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
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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

	/**
	 * Gets the cif.
	 *
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * Sets the cif.
	 *
	 * @param cif
	 *            the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * Gets the addressbilling.
	 *
	 * @return the addressbilling
	 */
	public String getAddressbilling() {
		return addressbilling;
	}

	/**
	 * Sets the addressbilling.
	 *
	 * @param addressbilling
	 *            the addressbilling to set
	 */
	public void setAddressbilling(String addressbilling) {
		this.addressbilling = addressbilling;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the postalcode.
	 *
	 * @return the postalcode
	 */
	public Long getPostalcode() {
		return postalcode;
	}

	/**
	 * Sets the postalcode.
	 *
	 * @param postalcode
	 *            the postalcode to set
	 */
	public void setPostalcode(Long postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the citybilling.
	 *
	 * @return the citybilling
	 */
	public String getCitybilling() {
		return citybilling;
	}

	/**
	 * Sets the citybilling.
	 *
	 * @param citybilling
	 *            the citybilling to set
	 */
	public void setCitybilling(String citybilling) {
		this.citybilling = citybilling;
	}

	/**
	 * Gets the postalcodebilling.
	 *
	 * @return the postalcodebilling
	 */
	public Long getPostalcodebilling() {
		return postalcodebilling;
	}

	/**
	 * Sets the postalcodebilling.
	 *
	 * @param postalcodebilling
	 *            the postalcodebilling to set
	 */
	public void setPostalcodebilling(Long postalcodebilling) {
		this.postalcodebilling = postalcodebilling;
	}

	/**
	 * Gets the countrybilling.
	 *
	 * @return the countrybilling
	 */
	public String getCountrybilling() {
		return countrybilling;
	}

	/**
	 * Sets the countrybilling.
	 *
	 * @param countrybilling
	 *            the countrybilling to set
	 */
	public void setCountrybilling(String countrybilling) {
		this.countrybilling = countrybilling;
	}

	/**
	 * Gets the billing_same_as_shipping.
	 *
	 * @return the billing_same_as_shipping
	 */
	public int getBilling_same_as_shipping() {
		return billing_same_as_shipping;
	}

	/**
	 * Sets the billing_same_as_shipping.
	 *
	 * @param billing_same_as_shipping
	 *            the billing_same_as_shipping to set
	 */
	public void setBilling_same_as_shipping(int billing_same_as_shipping) {
		this.billing_same_as_shipping = billing_same_as_shipping;
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
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the namebilling.
	 *
	 * @return the namebilling
	 */
	public String getNamebilling() {
		return namebilling;
	}

	/**
	 * Sets the namebilling.
	 *
	 * @param namebilling
	 *            the namebilling to set
	 */
	public void setNamebilling(String namebilling) {
		this.namebilling = namebilling;
	}

	/**
	 * Gets the payment.
	 *
	 * @return the payment
	 */
	public Long getPayment() {
		return payment;
	}

	/**
	 * Sets the payment.
	 *
	 * @param payment
	 *            the payment to set
	 */
	public void setPayment(Long payment) {
		this.payment = payment;
	}

	/**
	 * Gets the idaddressmailing.
	 *
	 * @return the idaddressmailing
	 */
	public Long getIdaddressmailing() {
		return idaddressmailing;
	}

	/**
	 * Sets the idaddressmailing.
	 *
	 * @param idaddressmailing
	 *            the idaddressmailing to set
	 */
	public void setIdaddressmailing(Long idaddressmailing) {
		this.idaddressmailing = idaddressmailing;
	}

	/**
	 * Gets the idaddressbilling.
	 *
	 * @return the idaddressbilling
	 */
	public Long getIdaddressbilling() {
		return idaddressbilling;
	}

	/**
	 * Sets the idaddressbilling.
	 *
	 * @param idaddressbilling
	 *            the idaddressbilling to set
	 */
	public void setIdaddressbilling(Long idaddressbilling) {
		this.idaddressbilling = idaddressbilling;
	}

	public List<JewelEntity> getJewels() {
		return jewels;
	}

	public void setJewels(List<JewelEntity> jewels) {
		this.jewels = jewels;
	}

}