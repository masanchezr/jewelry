package com.je.jsboot.admin.forms;

import com.je.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Class SearchDailyForm.
 */
public class SearchDailyForm {

	/** The date. */
	private String date;

	/** The place. */
	private PlaceEntity place;

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(String date) {
		this.date = date;
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
