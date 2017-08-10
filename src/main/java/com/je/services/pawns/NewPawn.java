package com.je.services.pawns;

import java.util.List;

import com.je.dbaccess.entities.ObjectPawnEntity;

/**
 * The Class NewPawn.
 */
public class NewPawn {

	private List<ObjectPawnEntity> objects;

	private List<RenovationDates> renovations;

	/** The numpawn. */
	private String numpawn;

	/** The nif. */
	private String nif;

	/** The address. */
	private String address;

	/** The name. */
	private String name;

	/** The surname. */
	private String surname;

	private String user;

	/** The creationdate. */
	private String creationdate;

	/** The datebirth. */
	private String datebirth;

	/** The nationality. */
	private String nationality;

	/** The amount. */
	private double amount;

	/** The retired. */
	private boolean retired;

	/** The percent. */
	private double percent;

	/** The idpawn. */
	private long idpawn;

	private int months;

	private String town;

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
	 *            the new nif
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
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public String getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate
	 *            the new creationdate
	 */
	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the datebirth.
	 *
	 * @return the datebirth
	 */
	public String getDatebirth() {
		return datebirth;
	}

	/**
	 * Sets the datebirth.
	 *
	 * @param datebirth
	 *            the new datebirth
	 */
	public void setDatebirth(String datebirth) {
		this.datebirth = datebirth;
	}

	/**
	 * Gets the nationality.
	 *
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Sets the nationality.
	 *
	 * @param nationality
	 *            the new nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Checks if is retired.
	 *
	 * @return true, if is retired
	 */
	public boolean isRetired() {
		return retired;
	}

	/**
	 * Sets the retired.
	 *
	 * @param retired
	 *            the new retired
	 */
	public void setRetired(boolean retired) {
		this.retired = retired;
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

	/**
	 * Gets the percent.
	 *
	 * @return the percent
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * Sets the percent.
	 *
	 * @param percent
	 *            the new percent
	 */
	public void setPercent(double percent) {
		this.percent = percent;
	}

	/**
	 * Gets the idpawn.
	 *
	 * @return the idpawn
	 */
	public long getIdpawn() {
		return idpawn;
	}

	/**
	 * Sets the idpawn.
	 *
	 * @param idpawn
	 *            the new idpawn
	 */
	public void setIdpawn(long idpawn) {
		this.idpawn = idpawn;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<ObjectPawnEntity> getObjects() {
		return objects;
	}

	public void setObjects(List<ObjectPawnEntity> objects) {
		this.objects = objects;
	}

	public List<RenovationDates> getRenovations() {
		return renovations;
	}

	public void setRenovations(List<RenovationDates> renovations) {
		this.renovations = renovations;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

}
