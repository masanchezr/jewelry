package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.HolidayRepository;

@SpringBootTest
class HolidaysRepositoryTest {

	/** The holiday repository. */
	@Autowired
	private HolidayRepository holidayRepository;

	@Test
	void allHolidaysTest() {
		Assertions.assertNotNull(holidayRepository.findAll());
	}

	@Test
	void findByHolidayAndPlaceTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(28017L);
		Assertions.assertNull(holidayRepository.findByHolidayAndPlace(new Date(), place));
	}

	@Test
	void findByHolidayBetweenTest() {
		Assertions.assertNotNull(holidayRepository.findByHolidayBetween(new Date(), new Date()));
	}
}
