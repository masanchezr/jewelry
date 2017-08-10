package com.je.web.forms;

import com.je.dbaccess.entities.CategoryEntity;

/**
 * The Class SearchForm.
 */
public class SearchJewelForm {

	/** The searchname. */
	private String searchname;

	/** The category. */
	private CategoryEntity category;

	/**
	 * Gets the searchname.
	 *
	 * @return the searchname
	 */
	public String getSearchname() {
		return searchname;
	}

	/**
	 * Sets the searchname.
	 *
	 * @param searchname
	 *            the new searchname
	 */
	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public CategoryEntity getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category
	 *            the new category
	 */
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}
