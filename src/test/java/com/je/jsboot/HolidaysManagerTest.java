package com.je.jsboot;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.managers.HolidaysManager;

@SpringBootTest
class HolidaysManagerTest {

	/** The holidays manager. */
	@Autowired
	private HolidaysManager holidaysManager;

	/**
	 * Gets the previous day test.
	 *
	 * @return the previous day test
	 */
	@Test
	void getPreviousDayTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar calendar = new GregorianCalendar(2015, 3, 3);
		place.setIdplace(13700L);
		Assertions.assertNotNull(holidaysManager.getPreviousDay(calendar.getTime(), place));
	}

	@Test
	void isHolidayTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar calendar = new GregorianCalendar(2015, 3, 24);
		place.setIdplace(13700L);
		Assertions.assertFalse(holidaysManager.isHoliday(calendar.getTime(), place));
	}
}
