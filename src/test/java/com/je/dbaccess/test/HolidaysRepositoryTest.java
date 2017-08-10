package com.je.dbaccess.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.HolidayEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.HolidayRepository;

/**
 * The Class HolidaysRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class HolidaysRepositoryTest {

	/** The holiday repository. */
	@Autowired
	private HolidayRepository holidayRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		HolidayEntity holiday = new HolidayEntity();
		PlaceEntity place = new PlaceEntity();
		Calendar calendar = new GregorianCalendar(2015, 3, 2);
		place.setIdplace(13700L);
		holiday.setPlace(place);
		holiday.setHoliday(calendar.getTime());
		// holidayRepository.save(holiday);
	}
}
