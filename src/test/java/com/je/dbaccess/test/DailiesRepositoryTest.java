package com.je.dbaccess.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.DailyEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.DailyRepository;

/**
 * The Class DailiesRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class DailiesRepositoryTest {

	/** The daily repository. */
	@Autowired
	private DailyRepository dailyRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		DailyEntity daily = new DailyEntity();
		Calendar calendar = new GregorianCalendar(2015, 2, 14);
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		daily.setDailydate(calendar.getTime());
		daily.setPlace(place);
		daily.setFinalamount(new BigDecimal(8950));
		// dailyRepository.save(daily);
	}

	/**
	 * Find by dailydate and place test.
	 */
	@Test
	public void findByDailydateAndPlaceTest() {
		Calendar calendar = new GregorianCalendar(2016, 6, 21);
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(28017L);
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		DailyEntity daily = dailyRepository.findByPlaceAndDailydate(place, calendar.getTime());
		System.out.println(daily.getFinalamount());
		System.out.println(daily.getDailydate());
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<DailyEntity> idailies = dailyRepository.findAll();
		Iterator<DailyEntity> itdailies = idailies.iterator();
		while (itdailies.hasNext()) {
			System.out.println(itdailies.next().getDailydate());
		}
	}

	/**
	 * Find first by place order by iddaily desc test.
	 */
	@Test
	public void findFirstByPlaceOrderByIddailyDescTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		DailyEntity daily = dailyRepository.findFirstByPlaceOrderByIddailyDesc(place);
		if (daily != null) {
			System.out.println("importe:" + daily.getFinalamount() + " fecha:" + daily.getDailydate());
		}
	}
}
