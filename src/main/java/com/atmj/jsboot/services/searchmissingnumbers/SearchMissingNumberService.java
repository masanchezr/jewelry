package com.atmj.jsboot.services.searchmissingnumbers;

import java.util.List;

import com.atmj.jsboot.admin.forms.SearchMissingNumbers;

public interface SearchMissingNumberService {

	public List<Long> searchMissingShoppings(SearchMissingNumbers form);
}
