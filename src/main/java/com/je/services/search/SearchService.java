package com.je.services.search;

import org.springframework.data.domain.Page;

import com.je.dbaccess.entities.JewelEntity;

/**
 * The Interface SearchService.
 */
public interface SearchService {

	/**
	 * Search actives.
	 * 
	 * @param searchname the searchname
	 * @param category   the category
	 * @return the list
	 */
	public Page<JewelEntity> searchActives(String searchName, int pageNumber);
}
