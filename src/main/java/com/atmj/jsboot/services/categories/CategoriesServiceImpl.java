package com.atmj.jsboot.services.categories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.repositories.CategoriesRepository;
import com.atmj.jsboot.utils.string.Util;

/**
 * The Class SearchCategoriesServiceImpl.
 */
@Service
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
		return categoriesRepository.findByActiveTrue(Sort.by(Direction.ASC, "namecategory"));
	}

	@Override
	public void save(CategoryEntity category) {
		// creamos la keyword
		category.setKeyword(Util.getKeyword(category.getNamecategory()));
		categoriesRepository.save(category);

	}

	@Override
	public CategoryEntity getCategoryByKeyword(String keyword) {
		return categoriesRepository.findByKeyword(keyword);
	}

	@Override
	public CategoryEntity findById(Long idcategory) {
		Optional<CategoryEntity> oc = categoriesRepository.findById(idcategory);
		if (oc.isPresent()) {
			return oc.get();
		} else
			return null;
	}

}
