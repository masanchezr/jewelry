package com.je.services.search;

import java.util.List;

import org.springframework.data.domain.Page;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;

/**
 * The Interface SearchService.
 */
public interface SearchService {

	/**
	 * Search.
	 * 
	 * @param searchName
	 *            the search name
	 * @return the list
	 */
	public List<JewelEntity> search(String searchName);

	/**
	 * Search actives.
	 * 
	 * @param searchname
	 *            the searchname
	 * @param category
	 *            the category
	 * @return the list
	 */
	public Page<JewelEntity> searchActivesWithImg(String searchname, CategoryEntity category, int pageNumber);
}
