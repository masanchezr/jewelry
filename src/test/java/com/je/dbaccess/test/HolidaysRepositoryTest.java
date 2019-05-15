package com.je.dbaccess.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.HolidayEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.HolidayRepository;

/**
 * The Class HolidaysRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
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

	@Test
	public void allHolidaysTest() {
		holidayRepository.findAll();
	}

	@Test
	public void findByHolidayAndPlaceTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(28017L);
		HolidayEntity holiday = holidayRepository.findByHolidayAndPlace(new Date(), place);
		System.out.println(holiday);
	}

	@Test
	public void findByHolidayBetweenTest() {
		Iterable<HolidayEntity> holidays = holidayRepository.findByHolidayBetween(new Date(), new Date());
		if (holidays != null) {
			Iterator<HolidayEntity> iholidays = holidays.iterator();
			while (iholidays.hasNext()) {
				System.out.println(iholidays.next());
			}
		}
	}
}
