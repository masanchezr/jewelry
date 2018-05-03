package com.je.services.salescard;

import java.util.Map;

import com.je.services.sales.SearchSale;

public interface SalesCardService {

	public Map<String, Object> searchByDates(SearchSale searchSale);
}
