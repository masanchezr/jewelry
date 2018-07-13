package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.discounts.Discount;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

public class DiscountsValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Discount.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDDISCOUNT, ConstantsJsp.ERRORNUMDISCOUNT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.SDISCOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
	}
}
