package com.je.services.holidays;

import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Class Holiday.
 */
public class Holiday {

	/** The holiday. */
	private String dateholiday;

	/** The place. */
	private PlaceEntity place;

	private String description;

	private String untildate;

	/**
	 * Gets the holiday.
	 *
	 * @return the holiday
	 */
	public String getDateholiday() {
		return dateholiday;
	}

	/**
	 * Sets the holiday.
	 *
	 * @param holiday
	 *            the new holiday
	 */
	public void setDateholiday(String holiday) {
		this.dateholiday = holiday;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUntildate() {
		return untildate;
	}

	public void setUntildate(String untildate) {
		this.untildate = untildate;
	}

}
