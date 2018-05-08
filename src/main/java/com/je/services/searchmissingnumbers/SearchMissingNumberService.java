package com.je.services.searchmissingnumbers;

import java.util.List;

import com.je.admin.forms.SearchMissingNumbers;

public interface SearchMissingNumberService {

	public List<Long> searchMissingShoppings(SearchMissingNumbers form);
}
