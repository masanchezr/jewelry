package com.je.services.searchmissingnumbers;

import java.util.List;

import com.je.dbaccess.entities.PlaceEntity;

public interface SearchMissingNumberService {

	public List<Long> searchMissingShoppings(SearchMissingNumbers form);

	public List<Long> calculateMissingSales(SearchMissingNumbers form);

	public Long getSalePostponedMissing(PlaceEntity place);
}
