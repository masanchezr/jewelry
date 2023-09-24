package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.JewelRepository;

/**
 * The Class JewelRepositoryTest.
 */
@SpringBootTest
class JewelRepositoryTest {

	/** The jewel repository. */
	@Autowired
	private JewelRepository jewelRepository;

	/**
	 * Count test.
	 */
	@Test
	void countTest() {
		Assertions.assertNotEquals(0, jewelRepository.count());
	}

	/**
	 * Find by reference test.
	 */
	@Test
	void findByReferenceTest() {
		CategoryEntity category = new CategoryEntity();
		category.setIdcategory(5L);
		Assertions.assertNotNull(jewelRepository.findByReferenceAndCategory("h183", category));
	}

	/**
	 * Find by reference and category and material and place and active test.
	 */
	@Test
	void findByReferenceAndCategoryAndMetalAndPlaceAndActiveTest() {
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
	void findByPlaceAndActiveTrueTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24003L);
		Assertions.assertNotNull(jewelRepository.findByPlaceAndActiveTrueOrderByReference(place));
	}
}
