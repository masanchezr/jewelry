package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.ShoppingsRepository;
import com.je.jsboot.utils.date.DateUtil;

@SpringBootTest
class ShoppingRepositoryTest {

	@Autowired
	private ShoppingsRepository shoppingsRepository;

	@Test
	void findByNumshopAndPlaceAndYearTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		Assertions.assertNull(shoppingsRepository.findByNumshopAndPlaceAndYear(3L, place, 2015));
	}

	@Test
	void findGramsNullTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		Date datefrom = DateUtil.getDate("2015-12-01");
		Assertions.assertNotNull(shoppingsRepository.findGramsNull(place, datefrom, new Date()));
	}
}
