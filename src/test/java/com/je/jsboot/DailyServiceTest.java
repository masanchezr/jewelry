package com.je.jsboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.services.dailies.DailyService;

@SpringBootTest
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
		Calendar c = new GregorianCalendar(2021, 01, 05);
		place.setIdplace(28017L);
		assertNotNull(dailyService.getDaily(c.getTime(), place, "prueba"));
	}
}
