package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PawnEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.PawnsRepository;

/**
 * The Class PawnsRepositoryTest.
 */
@SpringBootTest
class PawnsRepositoryTest {

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;

	@Test
	void sumPawnsActiveTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		Assertions.assertNotNull(pawnsRepository.sumPawnsActive(place));
	}

	@Test
	void searchMissingMonthsTest() {
		PawnEntity pawns = pawnsRepository.searchMissingMonths(1L);
		Assertions.assertNull(pawns);
	}
}
