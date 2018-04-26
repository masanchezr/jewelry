package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.holidays.Holiday;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

public class HolidayValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Holiday.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "holiday", ConstantsJsp.SELECTDATE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsJsp.ERRORSELECTDESCRIPTION);
		Holiday holiday = (Holiday) arg0;
		if (!DateUtil.isDate(holiday.getDateholiday())) {
			arg1.rejectValue(ConstantsJsp.FORMHOLIDAY, ConstantsJsp.SELECTDATE);
		}
	}

}
