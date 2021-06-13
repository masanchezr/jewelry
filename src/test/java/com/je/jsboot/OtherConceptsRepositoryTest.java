package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.OtherConceptsRepository;

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
