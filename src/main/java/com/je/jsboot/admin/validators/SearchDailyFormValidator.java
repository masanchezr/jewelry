package com.je.jsboot.admin.validators;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.jsboot.admin.forms.SearchDailyForm;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;

@Component
public class SearchDailyFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return SearchDailyForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		SearchDailyForm dailyForm = (SearchDailyForm) arg0;
		Date date = DateUtil.getDate(dailyForm.getDate());
		if (new Date().before(date)) {
			arg1.rejectValue(ConstantsViews.DATE, "datecannotbegreater");
		}
	}

}
