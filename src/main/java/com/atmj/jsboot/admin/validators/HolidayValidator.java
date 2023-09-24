package com.atmj.jsboot.admin.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.jsboot.services.holidays.Holiday;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;

@Component
public class HolidayValidator implements Validator {

	public static final String DATEHOLIDAY = "dateholiday";

	@Override
	public boolean supports(Class<?> arg0) {
		return Holiday.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, DATEHOLIDAY, ConstantsViews.SELECTDATE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsViews.ERRORSELECTDESCRIPTION);
		Holiday holiday = (Holiday) arg0;
		if (!DateUtil.isDate(holiday.getDateholiday())) {
			arg1.rejectValue(DATEHOLIDAY, ConstantsViews.SELECTDATE);
		}
	}

}
