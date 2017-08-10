package com.je.services.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.repositories.CategoriesRepository;
import com.je.utils.string.Util;

/**
 * The Class SearchCategoriesServiceImpl.
 */
public class CategoriesServiceImpl implements CategoriesService {

	/** The categories repository. */
	@Autowired
	private CategoriesRepository categoriesRepository;

	public Iterable<CategoryEntity> getAllCategories() {
		return categoriesRepository.findAll();
	}

	public Iterable<CategoryEntity> getAllCategoriesOrderByName() {
		return categoriesRepository.findByActiveTrue(new Sort(new Order("namecategory")));
	}

	public void save(CategoryEntity category) {
		// creamos la keyword
		category.setKeyword(Util.getKeyword(category.getNamecategory()));
		categoriesRepository.save(category);

	}

}
