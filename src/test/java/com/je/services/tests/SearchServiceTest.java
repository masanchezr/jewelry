package com.je.services.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.JewelEntity;
import com.je.services.search.SearchService;

/**
 * The Class SearchServiceTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class SearchServiceTest {

	/** The search service. */
	@Autowired
	SearchService searchService;

	/**
	 * Search test.
	 */
	@Test
	public void searchTest() {
		Page<JewelEntity> jewels = searchService.searchActives("sello", 1);
		assertNotNull(jewels);
	}
}
