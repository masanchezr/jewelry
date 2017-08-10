package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.holidays.Holiday;
import com.je.utils.date.DateUtil;

public class HolidayValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Holiday.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils
				.rejectIfEmptyOrWhitespace(arg1, "holiday", "selectdate");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description",
				"selectdescription");
		Holiday holiday = (Holiday) arg0;
		if (!DateUtil.isDate(holiday.getHoliday())) {
			arg1.rejectValue("holiday", "selectdate");
		}
	}

}
