package com.je.services.tests;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<JewelEntity> jewels = searchService.search("sello");
		if (jewels != null) {
			Iterator<JewelEntity> ijewels = jewels.iterator();
			while (ijewels.hasNext()) {
				JewelEntity jewel = ijewels.next();
				System.out.println("Id:" + jewel.getIdjewel() + "Nombre:" + jewel.getName() + "Descripcion:"
						+ jewel.getDescription() + "Img:" + jewel.getImg());
			}
		} else {
			System.out.println("Lista nula");
		}
	}
}
