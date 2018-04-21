package com.je.services.searchmissingnumbers;

import java.util.List;

public interface SearchMissingNumberService {

	public List<Long> searchMissingShoppings(SearchMissingNumbers form);

	public List<Long> calculateMissingSales(SearchMissingNumbers form);
}
