package com.je.dbaccess.test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.JewelRepository;

/**
 * The Class JewelRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class JewelRepositoryTest {

	/** The jewel repository. */
	@Autowired
	private JewelRepository jewelRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		JewelEntity jewel = new JewelEntity();
		PlaceEntity place = new PlaceEntity();
		CategoryEntity category = new CategoryEntity();
		MetalEntity material = new MetalEntity();
		material.setIdmetal(2L);
		category.setIdcategory(38L);
		place.setIdplace(28017L);
		// jewel.setIdjewel(978L);
		jewel.setActive(true);
		jewel.setMetal(material);
		jewel.setPrice(new BigDecimal(195));
		jewel.setReference("PORTAFOTOSG120");
		jewel.setCategory(category);
		// jewel.setImg("dfasf");
		jewel.setPlace(place);
		// jewel.setName("pendientes");
		jewel.setDescription("portafotos de plata");
		jewel.setGrams(new BigDecimal(1.20));
		// jewelRepository.save(jewel);
	}

	/**
	 * Find all active test.
	 */
	@Test
	public void findAllActiveTest() {
		Iterable<JewelEntity> jewels = jewelRepository.searchAllActive();
		if (jewels != null) {
			Iterator<JewelEntity> ijewels = jewels.iterator();
			print(ijewels);
		}
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<JewelEntity> jewels = jewelRepository.findAll();
		if (jewels != null) {
			Iterator<JewelEntity> ijewels = jewels.iterator();
			print(ijewels);
		}
	}

	/**
	 * Count test.
	 */
	@Test
	public void countTest() {
		long num = jewelRepository.count();
		System.out.print("Numero de joyas:" + num);
	}

	/**
	 * Find by reference test.
	 */
	@Test
	public void findByReferenceTest() {
		CategoryEntity category = new CategoryEntity();
		category.setIdcategory(5L);
		Iterable<JewelEntity> jewels = jewelRepository.findByReferenceAndCategory("h183", category);
		Iterator<JewelEntity> ijewels = jewels.iterator();
		JewelEntity jewel;
		while (ijewels.hasNext()) {
			jewel = ijewels.next();
			System.out.println("idjewel: " + jewel.getIdjewel() + "description: " + jewel.getDescription() + " active:"
					+ jewel.getActive());
		}
	}

	/**
	 * Find by category test.
	 */
	@Test
	public void findByCategoryTest() {
		CategoryEntity category = new CategoryEntity();
		category.setIdcategory(17L);
		Iterable<JewelEntity> ijewels = jewelRepository.findByCategoryAndActiveTrueAndImgNotNull(category);
		Iterator<JewelEntity> itjewels = ijewels.iterator();
		JewelEntity jewel;
		while (itjewels.hasNext()) {
			jewel = itjewels.next();
			System.out.println("idjewel:" + jewel.getIdjewel() + "reference: " + jewel.getReference() + " price:"
					+ jewel.getPrice() + "description:" + jewel.getDescription());
		}
	}

	/**
	 * Find by name and category test.
	 */
	@Test
	public void findByNameAndCategoryTest() {
		Iterable<JewelEntity> jewels = jewelRepository.findByNameAndCategory("%juego%");
		print(jewels.iterator());
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
		List<JewelEntity> jewels = jewelRepository.findByReferenceAndCategoryAndMetalAndPlaceAndActive("b186", category,
				material, place, Boolean.TRUE);
		if (jewels != null) {

		}
	}

	@Test
	public void findByPlaceAndActiveTrueTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24003L);
		Iterable<JewelEntity> jewels = jewelRepository.findByPlaceAndActiveTrueOrderByReference(place);
		if (jewels != null) {
			print(jewels.iterator());
		}

	}

	/**
	 * Prints the.
	 *
	 * @param ijewels
	 *            the ijewels
	 */
	private void print(Iterator<JewelEntity> ijewels) {
		while (ijewels != null && ijewels.hasNext()) {
			JewelEntity jewel = ijewels.next();
			System.out.println("id:" + jewel.getIdjewel() + " reference:" + jewel.getReference() + " name: "
					+ jewel.getName() + " active:" + jewel.getActive() + " description:" + jewel.getDescription()
					+ " precio:" + jewel.getPrice() + " categoria: " + jewel.getCategory().getNamecategory());
		}
	}

}
