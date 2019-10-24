package com.je.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.forms.SearchForm;
import com.je.utils.constants.ConstantsViews;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class SearchFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return SearchForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		SearchForm search = (SearchForm) arg0;
		String sfrom = search.getDatefrom();
		String suntil = search.getDateuntil();
		if (Util.isEmpty(sfrom) || !DateUtil.isDate(sfrom)) {
			arg1.rejectValue(ConstantsViews.SFROM, ConstantsViews.SELECTDATE);
		}
		if (!Util.isEmpty(suntil) && !DateUtil.isDate(suntil)) {
			arg1.rejectValue(ConstantsViews.SUNTIL, ConstantsViews.SELECTDATE);
		}
		if (!Util.isEmpty(suntil) && DateUtil.isDate(suntil) && !Util.isEmpty(sfrom) && DateUtil.isDate(sfrom)) {
			Date dfrom = DateUtil.getDate(sfrom);
			Date duntil = DateUtil.getDate(suntil);
			if (dfrom.after(duntil)) {
				arg1.rejectValue(ConstantsViews.SFROM, "datecannotbegreater");
			}
		}
	}
}
