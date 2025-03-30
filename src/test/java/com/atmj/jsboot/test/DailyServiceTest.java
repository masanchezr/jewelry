package com.atmj.jsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.services.dailies.DailyService;

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
		Calendar c = new GregorianCalendar(2023, 9, 6);
		place.setIdplace(13700L);
		assertNotNull(dailyService.getDaily(c.getTime(), place, "prueba"));
	}

	@Test
	void calculateDailiesTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar c = new GregorianCalendar(2023, 11, 6);
		place.setIdplace(24003L);
		dailyService.calculateDailies(c.getTime(), place);
	}
}
