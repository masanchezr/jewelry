package com.atmj.jsboot.test;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.repositories.CategoriesRepository;
import com.atmj.jsboot.utils.string.Util;

@SpringBootTest
class CategoriesRepositoryTest {

	/** The categories repository. */
	@Autowired
	private CategoriesRepository categoriesRepository;

	/**
	 * Find all test.
	 */
	@Test
	void findAllTest() {
		Assertions.assertNotNull(categoriesRepository.findAll());
	}

	/**
	 * Find by active test.
	 */
	@Test
	void findByActiveTest() {
		Assertions.assertNotNull(categoriesRepository);
	}

	/**
	 * Find all order by namecategory test.
	 */
	@Test
	void findAllOrderByNamecategoryTest() {
		Assertions.assertNotNull(categoriesRepository.findAll(Sort.by(Direction.ASC, "namecategory")));
	}

	/**
	 * Update test.
	 */
	@Test
	void updateTest() {
		Iterable<CategoryEntity> categories = categoriesRepository.findAll();
		if (categories != null) {
			Iterator<CategoryEntity> icategories = categories.iterator();
			while (icategories.hasNext()) {
				CategoryEntity category = icategories.next();
				category.setKeyword(Util.getKeyword(category.getNamecategory()));
				Assertions.assertNotNull(categoriesRepository.save(category));
			}
		}
	}
}
