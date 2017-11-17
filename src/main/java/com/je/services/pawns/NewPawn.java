package com.je.services.pawns;

import java.util.List;

import com.je.dbaccess.entities.ObjectPawnEntity;
import com.je.forms.OperationForm;

/**
 * The Class NewPawn.
 */
public class NewPawn extends OperationForm {

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

	/** The datebirth. */
	private String datebirth;

	/** The retired. */
	private boolean retired;

	/** The percent. */
	private double percent;

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
