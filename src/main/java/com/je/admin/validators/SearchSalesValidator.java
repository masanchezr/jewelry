package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.services.sales.SearchSale;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class SearchSalesValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return SearchSale.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		SearchSale searchSale = (SearchSale) arg0;
		String sfrom = searchSale.getSfrom();
		String suntil = searchSale.getSuntil();
		if (Util.isEmpty(sfrom) || !DateUtil.isDate(sfrom)) {
			arg1.rejectValue("sfrom", "selectdate");
		}
		if (!Util.isEmpty(suntil) && !DateUtil.isDate(suntil)) {
			arg1.rejectValue("suntil", "selectdate");
		}
	}

}
