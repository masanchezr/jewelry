package com.je.jsboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.CategoryEntity;
import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.services.jewels.JewelService;

/**
 * The Class JewelServiceTest.
 */
@SpringBootTest
class JewelServiceTest {

	/** The object service. */
	@Autowired
	private JewelService objectService;

	/**
	 * Search by reference category material place active test.
	 */
	@Test
	void searchByReferenceCategoryMetalPlaceActiveTest() {
		JewelEntity jewel = new JewelEntity();
		PlaceEntity place = new PlaceEntity();
		CategoryEntity category = new CategoryEntity();
		MetalEntity material = new MetalEntity();
		material.setIdmetal(1L);
		category.setIdcategory(1L);
		place.setIdplace(24003L);
		jewel.setPlace(place);
		jewel.setCategory(category);
		jewel.setReference("b186");
		jewel.setMetal(material);
		Assertions.assertNull(objectService.searchByReferenceCategoryMetalPlaceActive(jewel));
	}

	@Test
	void searchTest() {
		Assertions.assertNotNull(objectService.searchActive(1));
	}
}
