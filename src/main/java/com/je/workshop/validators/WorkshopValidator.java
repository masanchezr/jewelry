package com.je.workshop.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.workshop.Workshop;

/**
 * The Class WorkshopValidator.
 */
public class WorkshopValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Workshop.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "selectdescription");
		Workshop work = (Workshop) arg0;
		if (work.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}
	}

}
