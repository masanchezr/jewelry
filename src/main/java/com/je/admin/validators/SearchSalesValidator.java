package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.services.sales.SearchSale;
import com.je.utils.constants.ConstantsViews;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class SearchSalesValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return SearchSale.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		SearchSale searchSale = (SearchSale) arg0;
		String sfrom = searchSale.getSfrom();
		String suntil = searchSale.getSuntil();
		if (Util.isEmpty(sfrom) || !DateUtil.isDate(sfrom)) {
			arg1.rejectValue(ConstantsViews.SFROM, ConstantsViews.SELECTDATE);
		}
		if (!Util.isEmpty(suntil) && !DateUtil.isDate(suntil)) {
			arg1.rejectValue(ConstantsViews.SUNTIL, ConstantsViews.SELECTDATE);
		}
	}

}
