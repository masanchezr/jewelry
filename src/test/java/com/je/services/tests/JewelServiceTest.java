package com.je.services.tests;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.jewels.JewelService;

/**
 * The Class JewelServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class JewelServiceTest {

	/** The object service. */
	@Autowired
	private JewelService objectService;

	/**
	 * Adds the object test.
	 */
	@Test
	public void addObjectTest() {
		JewelEntity thing = new JewelEntity();
		PlaceEntity place = new PlaceEntity();
		CategoryEntity category = new CategoryEntity();
		MetalEntity metal = new MetalEntity();
		metal.setIdmetal(1L);
		category.setIdcategory(4L);
		place.setIdplace(13700L);
		thing.setPlace(place);
		thing.setName("bicolor");
		thing.setDescription("bicolor");
		thing.setPrice(new BigDecimal(224));
		thing.setActive(true);
		thing.setReference("9332p");
		thing.setCategory(category);
		thing.setMetal(metal);
		thing.setRevised(Boolean.FALSE);
		// objectService.addObject(thing);
	}

	/**
	 * Search by reference category metal place active test.
	 */
	@Test
	public void searchByReferenceCategoryMetalPlaceActiveTest() {
		JewelEntity jewel = new JewelEntity();
		PlaceEntity place = new PlaceEntity();
		CategoryEntity category = new CategoryEntity();
		MetalEntity metal = new MetalEntity();
		metal.setIdmetal(1L);
		category.setIdcategory(1L);
		place.setIdplace(24003L);
		jewel.setPlace(place);
		jewel.setCategory(category);
		jewel.setReference("b186");
		jewel.setMetal(metal);
		objectService.searchByReferenceCategoryMetalPlaceActive(jewel);
	}

	/**
	 * Search all test.
	 */
	@Test
	public void searchAllTest() {
		Iterable<JewelEntity> objects = objectService.searchAll();
		print(objects);
	}

	@Test
	public void searchWithImgTest() {
		Page<JewelEntity> page = objectService.searchWithImg(1);
		page.getTotalElements();
	}

	/**
	 * Prints the.
	 *
	 * @param objects
	 *            the objects
	 */
	private void print(Iterable<JewelEntity> objects) {
		if (objects != null) {
			for (JewelEntity thing : objects) {
				System.out.println("nombre: " + thing.getName() + " id:" + thing.getIdjewel() + " active:"
						+ thing.getActive() + "price: " + thing.getPrice() + " categoria:"
						+ thing.getCategory().getNamecategory() + " lugar:" + thing.getPlace().getDescription());
			}
		}
	}
}
