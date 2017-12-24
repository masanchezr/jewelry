package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.OtherConceptEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.OtherConceptsRepository;

/**
 * The Class OtherConceptsRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class OtherConceptsRepositoryTest {

	/** The other concepts repository. */
	@Autowired
	private OtherConceptsRepository otherConceptsRepository;

	private static final Logger logger = Logger.getLogger(OtherConceptsRepositoryTest.class);

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
				logger.debug(iconcepts.next().getDescription());
			}
		}
	}

}
