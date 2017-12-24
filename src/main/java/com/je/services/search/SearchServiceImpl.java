package com.je.services.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.je.dbaccess.entities.CategoryEntity;
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
	public Page<JewelEntity> searchActivesWithImg(String searchName, CategoryEntity category, int pageNumber) {
		Page<JewelEntity> jewels = jewelsManager.searchByNameDescCategoryActives(searchName, category,
				PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE));
		return jewels;
	}

	@Override
	public List<JewelEntity> search(String searchName) {
		return jewelsManager.searchByNameDescCategory(searchName);
	}
}
