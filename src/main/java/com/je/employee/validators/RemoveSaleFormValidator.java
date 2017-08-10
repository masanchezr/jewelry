package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.sales.Sale;

/**
 * The Class RemoveSaleFormValidator.
 */
public class RemoveSaleFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Sale.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "numsale", "selectidsale");
	}

}
