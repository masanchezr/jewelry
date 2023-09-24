package com.atmj.jsboot.services.salescard;

import java.util.Map;

import com.atmj.jsboot.services.sales.SearchSale;

public interface SalesCardService {

	public Map<String, Object> searchByDates(SearchSale searchSale);
}
