package com.je.services.holidays;

import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Class Holiday.
 */
public class Holiday {

	/** The holiday. */
	private String holiday;

	/** The place. */
	private PlaceEntity place;

	private String description;

	private boolean allplaces;

	private String untildate;

	/**
	 * Gets the holiday.
	 *
	 * @return the holiday
	 */
	public String getHoliday() {
		return holiday;
	}

	/**
	 * Sets the holiday.
	 *
	 * @param holiday
	 *            the new holiday
	 */
	public void setHoliday(String holiday) {
		this.holiday = holiday;
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

	public boolean isAllplaces() {
		return allplaces;
	}

	public void setAllplaces(boolean allplaces) {
		this.allplaces = allplaces;
	}

	public String getUntildate() {
		return untildate;
	}

	public void setUntildate(String untildate) {
		this.untildate = untildate;
	}

}
