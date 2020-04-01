package com.je.services.categories;

import com.je.dbaccess.entities.CategoryEntity;

/**
 * The Interface SearchCategoriesService.
 */
public interface CategoriesService {

	/**
	 * Gets the all categories.
	 * 
	 * @return the all categories
	 */
	public Iterable<CategoryEntity> getAllCategories();

	/**
	 * Save.
	 *
	 * @param category the category
	 */
	public void save(CategoryEntity category);

	/**
	 * Gets the all categories order by name.
	 *
	 * @return the all categories order by name
	 */
	public Iterable<CategoryEntity> getAllCategoriesOrderByName();

	public CategoryEntity getCategoryByKeyword(String keyword);

}
