package com.je.workshop.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.adjustments.Adjustment;

/**
 * The Class AdjustmentValidator.
 */
public class AdjustmentValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Adjustment.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idadjustment",
				"selectidadjustment");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description",
				"selectdescription");
		Adjustment adjustment = (Adjustment) arg0;
		if (adjustment.getAmountwork() <= 0) {
			arg1.rejectValue("amountwork", "selectamount");
		}
	}

}
