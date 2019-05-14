package com.je.services.tests;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.admin.forms.SearchMissingNumbers;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.searchmissingnumbers.SearchMissingNumberService;

@ExtendWith(SpringExtension.class)
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
		form.setNumuntil(2L);
		form.setYear(2018);
		form.setPlace(place);
		List<Long> missings = searchMissingNumbers.searchMissingShoppings(form);
		Iterator<Long> imissings = missings.iterator();
		while (imissings.hasNext()) {
			System.out.println(imissings.next());
		}
	}
}
