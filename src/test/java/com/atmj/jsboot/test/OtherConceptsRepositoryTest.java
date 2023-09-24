package com.atmj.jsboot.test;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.OtherConceptsRepository;

/**
 * The Class OtherConceptsRepositoryTest.
 */
@SpringBootTest
class OtherConceptsRepositoryTest {

	/** The other concepts repository. */
	@Autowired
	private OtherConceptsRepository otherConceptsRepository;

	/**
	 * Find by creationdate and place test.
	 */
	@Test
	void findByCreationdateAndPlaceTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		Assertions.assertNotNull(otherConceptsRepository.findByCreationdateAndPlace(new Date(), place));
	}

}
