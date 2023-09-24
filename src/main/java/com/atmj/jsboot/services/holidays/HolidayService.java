package com.atmj.jsboot.services.holidays;

import java.util.List;

/**
 * The Interface HolidayService.
 */
public interface HolidayService {

	/**
	 * Adds the holiday.
	 *
	 * @param holiday
	 *            the holiday
	 */
	public void addHoliday(Holiday holiday);

	public void addHolidayAllPlaces(Holiday holiday);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Holiday> findAll();

	public boolean existsHoliday(Holiday holiday);

	public List<Holiday> findByBetweenDates(Holiday holiday);
}
