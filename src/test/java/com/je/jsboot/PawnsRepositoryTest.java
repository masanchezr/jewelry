package com.je.jsboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.PawnsRepository;

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
}
