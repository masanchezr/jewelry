package com.je.services.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

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

	@Override
	public Iterable<CategoryEntity> getAllCategories() {
		return categoriesRepository.findAll();
	}

	@Override
	public Iterable<CategoryEntity> getAllCategoriesOrderByName() {
		return categoriesRepository.findByActiveTrue(new Sort(Direction.ASC, "namecategory"));
	}

	@Override
	public void save(CategoryEntity category) {
		// creamos la keyword
		category.setKeyword(Util.getKeyword(category.getNamecategory()));
		categoriesRepository.save(category);

	}

}
