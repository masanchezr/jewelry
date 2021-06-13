package com.je.jsboot.services.salescard;

import java.util.Map;

import com.je.jsboot.services.sales.SearchSale;

public interface SalesCardService {

	public Map<String, Object> searchByDates(SearchSale searchSale);
}
