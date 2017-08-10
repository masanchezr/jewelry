package com.je.admin.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.admin.forms.SearchDailyForm;
import com.je.utils.date.DateUtil;

public class SearchDailyFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return SearchDailyForm.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		SearchDailyForm dailyForm = (SearchDailyForm) arg0;
		Date date = DateUtil.getDate(dailyForm.getDate());
		if (new Date().before(date)) {
			arg1.rejectValue("date", "datecannotbegreater");
		}
	}

}
