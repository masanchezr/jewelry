package com.atmj.jsboot.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.DailyEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.DailyRepository;

@SpringBootTest
class DailiesRepositoryTest {

	/** The daily repository. */
	@Autowired
	private DailyRepository dailyRepository;

	/**
	 * Save test.
	 */
	@Test
	void saveTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		Calendar calendar = new GregorianCalendar(2020, 11, 30);
		DailyEntity daily = dailyRepository.findByPlaceAndDailydate(place, calendar.getTime());
		daily.setDailydate(calendar.getTime());
		daily.setPlace(place);
		daily.setFinalamount(BigDecimal.valueOf(8950));
		assertNotNull(dailyRepository.save(daily));
	}

	/**
	 * Find by dailydate and place test.
	 */
	@Test
	void findByDailydateAndPlaceTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		DailyEntity daily = dailyRepository.findByPlaceAndDailydate(place, new Date());
		assertThat(daily);
	}
}
