package com.je.jsboot.dbaccess.managers;

import java.util.Date;

import com.je.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Interface HolidaysManager.
 */
public interface HolidaysManager {

	/**
	 * Gets the previous day.
	 *
	 * @param date
	 *            the date
	 * @param place
	 *            the place
	 * @return the previous day
	 */
	public Date getPreviousDay(Date date, PlaceEntity place);

	public boolean isHoliday(Date date, PlaceEntity place);
}
