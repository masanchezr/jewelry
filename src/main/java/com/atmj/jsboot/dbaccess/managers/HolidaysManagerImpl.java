package com.atmj.jsboot.dbaccess.managers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.HolidayEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.HolidayRepository;

/**
 * The Class HolidaysManagerImpl.
 */
@Service
public class HolidaysManagerImpl implements HolidaysManager {

	/** The holiday repository. */
	@Autowired
	private HolidayRepository holidayRepository;

	public Date getPreviousDay(Date date, PlaceEntity place) {
		Calendar calendar = Calendar.getInstance();
		boolean isHoliday = true;
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		while (isHoliday) {
			/**
			 * if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			 * calendar.add(Calendar.DAY_OF_MONTH, -1); }
			 **/
			HolidayEntity holiday = holidayRepository.findByHolidayAndPlace(calendar.getTime(), place);
			if (holiday != null) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			} else {
				isHoliday = false;
			}
		}
		return calendar.getTime();
	}

	public boolean isHoliday(Date date, PlaceEntity place) {
		Calendar calendar = Calendar.getInstance();
		boolean isHoliday = true;
		calendar.setTime(date);
		/* if (Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK)) { */
		HolidayEntity holiday = holidayRepository.findByHolidayAndPlace(calendar.getTime(), place);
		if (holiday == null) {
			isHoliday = false;
		}
		// }
		return isHoliday;
	}
}
