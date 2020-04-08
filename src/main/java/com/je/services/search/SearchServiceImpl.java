package com.je.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.managers.JewelsManager;
import com.je.utils.constants.Constants;

/**
 * The Class SearchServiceImpl.
 */
public class SearchServiceImpl implements SearchService {

	/** The jewels manager. */
	@Autowired
	private JewelsManager jewelsManager;

	@Override
	public Page<JewelEntity> searchActives(String searchName, int pageNumber) {
		return jewelsManager.searchByNameDescCategory(searchName, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE));
	}
}
