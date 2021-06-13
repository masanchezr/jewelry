package com.je.jsboot.services.search;

import org.springframework.data.domain.Page;

import com.je.jsboot.dbaccess.entities.JewelEntity;

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
