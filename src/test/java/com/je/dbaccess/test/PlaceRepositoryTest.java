package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.PlaceRepository;

/**
 * The Class PlaceRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class PlaceRepositoryTest {

	/** The place repository. */
	@Autowired
	private PlaceRepository placeRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(2000L);
		place.setDescription("JORGE");
		place.setCreationdate(new Date());
		// placeRepository.save(place);
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<PlaceEntity> places = placeRepository.findAll();
		if (places != null) {
			Iterator<PlaceEntity> iplaces = places.iterator();
			PlaceEntity place;
			while (iplaces.hasNext()) {
				place = iplaces.next();
				System.out.println("Codigo postal:" + place.getIdplace() + " name:" + place.getDescription());
			}
		}
	}

	/**
	 * Find one.
	 */
	@Test
	public void findById() {
		PlaceEntity place = placeRepository.findById(24002L).get();
		if (place != null) {
			System.out.println("Lugar:" + place.getDescription());
		}
	}

	/**
	 * Delete one test.
	 */
	@Test
	public void deleteOneTest() {
		// placeRepository.delete(28003L);
	}
}
