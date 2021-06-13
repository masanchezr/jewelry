package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.PlaceRepository;

/**
 * The Class PlaceRepositoryTest.
 */
@SpringBootTest
class PlaceRepositoryTest {

	/** The place repository. */
	@Autowired
	private PlaceRepository placeRepository;

	/**
	 * Save test.
	 */
	@Test
	void saveTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(2000L);
		place.setDescription("JORGE");
		place.setCreationdate(new Date());
		Assertions.assertNotNull(placeRepository.save(place));
	}

	/**
	 * Find all test.
	 */
	@Test
	void findAllTest() {
		Assertions.assertNotNull(placeRepository.findAll());
	}

	/**
	 * Find one.
	 */
	@Test
	void findById() {
		Assertions.assertNotNull(placeRepository.findById(24002L).get());
	}
}
