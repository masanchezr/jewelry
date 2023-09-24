package com.atmj.jsboot.admin.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atmj.jsboot.services.sales.SearchSale;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

@Component
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
