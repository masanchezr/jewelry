package com.je.jsboot.services.categories;

import com.je.jsboot.dbaccess.entities.CategoryEntity;

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

	public CategoryEntity findById(Long idcategory);

}
