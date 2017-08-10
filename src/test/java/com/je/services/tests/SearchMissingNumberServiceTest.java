package com.je.services.tests;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.searchmissingnumbers.SearchMissingNumberService;
import com.je.services.searchmissingnumbers.SearchMissingNumbers;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class SearchMissingNumberServiceTest {

	@Autowired
	private SearchMissingNumberService searchMissingNumbers;

	@Test
	public void searchMissingShoppingsTest() {
		SearchMissingNumbers form = new SearchMissingNumbers();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		form.setNumfrom(1L);
		form.setNumuntil(1713L);
		form.setYear(2015);
		form.setPlace(place);
		List<Long> missings = searchMissingNumbers.searchMissingShoppings(form);
		Iterator<Long> imissings = missings.iterator();
		while (imissings.hasNext()) {
			System.out.println(imissings.next());
		}
	}
}
