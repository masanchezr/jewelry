package com.atmj.jsboot.admin.validators;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atmj.jsboot.admin.forms.SearchDailyForm;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

@Component
public class SearchDailyFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return SearchDailyForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		SearchDailyForm dailyForm = (SearchDailyForm) arg0;
		String sdate = dailyForm.getDate();
		if (!Util.isEmpty(sdate)) {
			Date date = DateUtil.getDate(sdate);
			if (new Date().before(date)) {
				arg1.rejectValue(ConstantsViews.DATE, "datecannotbegreater");
			}
		}
	}

}
