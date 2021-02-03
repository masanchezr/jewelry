package com.je.services.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.admin.forms.SearchMissingNumbers;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.searchmissingnumbers.SearchMissingNumberService;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
class SearchMissingNumberServiceTest {

	@Autowired
	private SearchMissingNumberService searchMissingNumbers;

	@Test
	void searchMissingShoppingsTest() {
		SearchMissingNumbers form = new SearchMissingNumbers();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		form.setNumfrom(1L);
		form.setNumuntil(2L);
		form.setYear(2018);
		form.setPlace(place);
		Assert.assertNotNull(searchMissingNumbers.searchMissingShoppings(form));
	}
}
