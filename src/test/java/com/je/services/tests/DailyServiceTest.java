package com.je.services.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.dailies.DailyService;

/**
 * The Class DailyServiceTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
class DailyServiceTest {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/**
	 * Gets the daily test.
	 *
	 * @return the daily test
	 */
	@Test
	void getDailyTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar c = new GregorianCalendar(2020, 11, 28);
		place.setIdplace(28017L);
		assertNotNull(dailyService.getDaily(c.getTime(), place, "prueba"));
	}
}
