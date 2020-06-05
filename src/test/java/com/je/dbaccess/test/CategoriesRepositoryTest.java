package com.je.dbaccess.test;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.repositories.CategoriesRepository;
import com.je.utils.string.Util;

/**
 * The Class CategoriesRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class CategoriesRepositoryTest {

	/** The categories repository. */
	@Autowired
	private CategoriesRepository categoriesRepository;

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<CategoryEntity> categories = categoriesRepository.findAll();
		if (categories != null) {
			Iterator<CategoryEntity> icategories = categories.iterator();
			while (icategories.hasNext()) {
				CategoryEntity category = icategories.next();
				System.out.println("id:" + category.getIdcategory() + "name:" + category.getNamecategory() + " keyword:"
						+ category.getKeyword());
			}
		}
	}

	/**
	 * Find by active test.
	 */
	@Test
	public void findByActiveTest() {
		Iterable<CategoryEntity> categories = categoriesRepository
				.findByActiveTrue(Sort.by(Direction.ASC, "namecategory"));
		if (categories != null) {
			Iterator<CategoryEntity> icategories = categories.iterator();
			while (icategories.hasNext()) {
				CategoryEntity category = icategories.next();
				System.out.println("id:" + category.getIdcategory() + "name:" + category.getNamecategory() + " keyword:"
						+ category.getKeyword());
			}
		}
	}

	/**
	 * Find all order by namecategory test.
	 */
	@Test
	public void findAllOrderByNamecategoryTest() {
		Iterable<CategoryEntity> categories = categoriesRepository.findAll(Sort.by(Direction.ASC, "namecategory"));
		if (categories != null) {
			Iterator<CategoryEntity> icategories = categories.iterator();
			while (icategories.hasNext()) {
				CategoryEntity category = icategories.next();
				System.out.println("id:" + category.getIdcategory() + "name:" + category.getNamecategory() + " keyword:"
						+ category.getKeyword());
			}
		}
	}

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		CategoryEntity category = new CategoryEntity();
		category.setNamecategory("Billetes");
		category.setActive(true);
		category.setKeyword(Util.getKeyword(category.getNamecategory()));
		// categoriesRepository.save(category);
	}

	/**
	 * Update test.
	 */
	@Test
	public void updateTest() {
		Iterable<CategoryEntity> categories = categoriesRepository.findAll();
		if (categories != null) {
			Iterator<CategoryEntity> icategories = categories.iterator();
			while (icategories.hasNext()) {
				CategoryEntity category = icategories.next();
				category.setKeyword(Util.getKeyword(category.getNamecategory()));
				// categoriesRepository.save(category);
				System.out.println("id:" + category.getIdcategory() + "name:" + category.getNamecategory() + " keyword:"
						+ category.getKeyword() + " active:" + category.getActive());
			}
		}
	}
}
