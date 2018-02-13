package com.je.workshop.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.adjustments.Adjustment;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class AdjustmentValidator.
 */
public class AdjustmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Adjustment.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.IDADJUSTMENT, ConstantsJsp.ERRORSELECTIDADJUSTMENT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsJsp.ERRORSELECTDESCRIPTION);
		Adjustment adjustment = (Adjustment) arg0;
		if (adjustment.getAmountwork() <= 0) {
			arg1.rejectValue("amountwork", ConstantsJsp.ERRORSELECTAMOUNT);
		}
	}

}
