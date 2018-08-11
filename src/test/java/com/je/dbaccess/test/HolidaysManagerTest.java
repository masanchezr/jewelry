package com.je.dbaccess.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.managers.HolidaysManager;

/**
 * The Class HolidaysManagerTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class HolidaysManagerTest {

	/** The holidays manager. */
	@Autowired
	private HolidaysManager holidaysManager;

	/**
	 * Gets the previous day test.
	 *
	 * @return the previous day test
	 */
	@Test
	public void getPreviousDayTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar calendar = new GregorianCalendar(2015, 3, 3);
		place.setIdplace(13700L);
		Date date = holidaysManager.getPreviousDay(calendar.getTime(), place);
		System.out.println("fecha:" + date.toString());
	}

	@Test
	public void isHolidayTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar calendar = new GregorianCalendar(2015, 3, 24);
		place.setIdplace(13700L);
		boolean isholiday = holidaysManager.isHoliday(calendar.getTime(), place);
		System.out.println("isholiday:" + isholiday);
	}
}
