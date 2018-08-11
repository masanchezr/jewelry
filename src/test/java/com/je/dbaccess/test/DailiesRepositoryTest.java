package com.je.dbaccess.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.DailyEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.DailyRepository;

import junit.framework.Assert;

/**
 * The Class DailiesRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
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
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		Calendar calendar = new GregorianCalendar(2018, 5, 14);
		DailyEntity daily = dailyRepository.findByPlaceAndDailydate(place, calendar.getTime());
		daily.setDailydate(calendar.getTime());
		daily.setPlace(place);
		daily.setFinalamount(BigDecimal.valueOf(8950));
		dailyRepository.save(daily);
	}

	/**
	 * Find by dailydate and place test.
	 */
	@Test
	public void findByDailydateAndPlaceTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		DailyEntity daily = dailyRepository.findByPlaceAndDailydate(place, new Date());
		Assert.assertNull(daily);
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
