package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.admin.forms.SearchMissingNumbers;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.services.searchmissingnumbers.SearchMissingNumberService;

@SpringBootTest
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
		Assertions.assertNotNull(searchMissingNumbers.searchMissingShoppings(form));
	}
}
