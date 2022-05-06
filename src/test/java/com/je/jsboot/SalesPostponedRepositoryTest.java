package com.je.jsboot;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.SalesPostponedRepository;

@SpringBootTest
class SalesPostponedRepositoryTest {

	@Autowired
	private SalesPostponedRepository salespostponedrepository;

	@Test
	void findFirstByOrderByIdsalepostponedDesc() {
		Assertions.assertNotNull(salespostponedrepository.findFirstByOrderByIdsalepostponedDesc());
	}

	@Test
	void findFistByOrderByIdsalepostponed() {
		Assertions.assertNotNull(salespostponedrepository.findFirstByOrderByIdsalepostponed());
	}

	@Test
	void sumDateretiredBetweenAndPlaceTest() {
		Calendar c = Calendar.getInstance();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(9004L);
		c.set(2020, 4, 1);
		Assertions
				.assertNotNull(salespostponedrepository.sumDateretiredBetweenAndPlace(c.getTime(), new Date(), place));
	}

}
