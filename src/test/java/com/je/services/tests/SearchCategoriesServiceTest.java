package com.je.services.tests;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.services.categories.CategoriesService;

/**
 * The Class SearchCategoriesServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class SearchCategoriesServiceTest {

	/** The search categories service. */
	@Autowired
	CategoriesService searchCategoriesService;

	/**
	 * Gets the all categories test.
	 * 
	 * @return the all categories test
	 */
	@Test
	public void getAllCategoriesTest() {
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategories();
		if (categories != null) {
			Iterator<CategoryEntity> icategories = categories.iterator();
			while (icategories.hasNext()) {
				System.out.println("Nombre: " + icategories.next().getNamecategory());
			}
		}
	}
}
