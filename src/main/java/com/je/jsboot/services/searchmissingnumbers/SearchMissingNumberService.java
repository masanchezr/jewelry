package com.je.jsboot.services.searchmissingnumbers;

import java.util.List;

import com.je.jsboot.admin.forms.SearchMissingNumbers;

public interface SearchMissingNumberService {

	public List<Long> searchMissingShoppings(SearchMissingNumbers form);
}
