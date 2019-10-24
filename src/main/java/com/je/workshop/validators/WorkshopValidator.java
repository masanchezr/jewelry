package com.je.workshop.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.workshop.Workshop;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;

/**
 * The Class WorkshopValidator.
 */
public class WorkshopValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Workshop.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsViews.ERRORSELECTDESCRIPTION);
		Workshop work = (Workshop) arg0;
		if (work.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
	}

}
