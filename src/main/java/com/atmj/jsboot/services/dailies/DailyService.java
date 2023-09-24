package com.atmj.jsboot.services.dailies;

import java.util.Date;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Interface DailyService.
 */
public interface DailyService {

	/**
	 * Gets the daily.
	 *
	 * @param date
	 *            the date
	 * @param place
	 *            the place
	 * @param ipAddress
	 * @return the daily
	 */
	public Daily getDaily(Date date, PlaceEntity place, String ipAddress);

	public void calculateDailies(Date date, PlaceEntity place);
}
