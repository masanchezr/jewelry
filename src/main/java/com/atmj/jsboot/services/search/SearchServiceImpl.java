package com.atmj.jsboot.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.managers.JewelsManager;
import com.atmj.jsboot.utils.constants.Constants;

/**
 * The Class SearchServiceImpl.
 */
@Service
public class SearchServiceImpl implements SearchService {

	/** The jewels manager. */
	@Autowired
	private JewelsManager jewelsManager;

	@Override
	public Page<JewelEntity> searchActives(String searchName, int pageNumber) {
		return jewelsManager.searchByNameDescCategory(searchName, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE));
	}
}
