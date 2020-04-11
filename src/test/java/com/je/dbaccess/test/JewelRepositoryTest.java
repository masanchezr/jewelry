package com.je.dbaccess.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.JewelRepository;

/**
 * The Class JewelRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class JewelRepositoryTest {

	/** The jewel repository. */
	@Autowired
	private JewelRepository jewelRepository;

	/**
	 * Count test.
	 */
	@Test
	public void countTest() {
		Assertions.assertNotNull(jewelRepository.count());
	}

	/**
	 * Find by reference test.
	 */
	@Test
	public void findByReferenceTest() {
		CategoryEntity category = new CategoryEntity();
		category.setIdcategory(5L);
		Assertions.assertNotNull(jewelRepository.findByReferenceAndCategory("h183", category));
	}

	/**
	 * Find by reference and category and material and place and active test.
	 */
	@Test
	public void findByReferenceAndCategoryAndMetalAndPlaceAndActiveTest() {
		PlaceEntity place = new PlaceEntity();
		CategoryEntity category = new CategoryEntity();
		MetalEntity material = new MetalEntity();
		material.setIdmetal(1L);
		category.setIdcategory(1L);
		place.setIdplace(24003L);
		Assertions.assertNotNull(jewelRepository.findByReferenceAndCategoryAndMetalAndPlaceAndActive("b186", category,
				material, place, Boolean.TRUE));
	}

	@Test
	public void findByPlaceAndActiveTrueTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24003L);
		Assertions.assertNotNull(jewelRepository.findByPlaceAndActiveTrueOrderByReference(place));
	}
}
