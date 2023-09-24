package com.atmj.jsboot.forms;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Class SearchForm.
 */
public class SearchForm {

	/** The datefrom. */
	private String datefrom;

	/** The dateuntil. */
	private String dateuntil;

	/** The place. */
	private PlaceEntity place;

	/**
	 * Gets the datefrom.
	 *
	 * @return the datefrom
	 */
	public String getDatefrom() {
		return datefrom;
	}

	/**
	 * Sets the datefrom.
	 *
	 * @param datefrom
	 *            the new datefrom
	 */
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	/**
	 * Gets the dateuntil.
	 *
	 * @return the dateuntil
	 */
	public String getDateuntil() {
		return dateuntil;
	}

	/**
	 * Sets the dateuntil.
	 *
	 * @param dateuntil
	 *            the new dateuntil
	 */
	public void setDateuntil(String dateuntil) {
		this.dateuntil = dateuntil;
	}

	/**
	 * Gets the place.
	 *
	 * @return the place
	 */
	public PlaceEntity getPlace() {
		return place;
	}

	/**
	 * Sets the place.
	 *
	 * @param place
	 *            the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}
}
