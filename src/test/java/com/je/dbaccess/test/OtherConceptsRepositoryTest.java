package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.OtherConceptEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.OtherConceptsRepository;

/**
 * The Class OtherConceptsRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class OtherConceptsRepositoryTest {

	/** The other concepts repository. */
	@Autowired
	private OtherConceptsRepository otherConceptsRepository;

	/**
	 * Find by creationdate and place test.
	 */
	@Test
	public void findByCreationdateAndPlaceTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		List<OtherConceptEntity> concepts = otherConceptsRepository.findByCreationdateAndPlace(new Date(), place);
		if (concepts != null) {
			Iterator<OtherConceptEntity> iconcepts = concepts.iterator();
			while (iconcepts.hasNext()) {
				System.out.println(iconcepts.next().getDescription());
			}
		}
	}

}
