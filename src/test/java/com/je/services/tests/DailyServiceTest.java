package com.je.services.tests;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;

/**
 * The Class DailyServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class DailyServiceTest {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/**
	 * Gets the daily test.
	 *
	 * @return the daily test
	 */
	@Test
	public void getDailyTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar c = Calendar.getInstance();
		c.set(2016, 12, 6);
		place.setIdplace(28017L);
		Daily daily = dailyService.getDaily(c.getTime(), place, "prueba");
		if (daily != null) {
			System.out.println(
					"Numero de operaciones:" + daily.getNumoperations() + " importe final:" + daily.getFinalamount());
		}
	}

	@Test
	public void calculateDailiesTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar c = Calendar.getInstance();
		c.set(2017, 7, 28);
		place.setIdplace(28017L);
		dailyService.calculateDailies(c.getTime(), place);
	}
}
