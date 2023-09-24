package com.atmj.jsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.services.search.SearchService;

/**
 * The Class SearchServiceTest.
 */
@SpringBootTest
class SearchServiceTest {

	/** The search service. */
	@Autowired
	SearchService searchService;

	/**
	 * Search test.
	 */
	@Test
	void searchTest() {
		Page<JewelEntity> jewels = searchService.searchActives("sello", 1);
		assertNotNull(jewels);
	}
}
