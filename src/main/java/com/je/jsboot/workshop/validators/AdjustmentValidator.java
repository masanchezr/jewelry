package com.je.jsboot.workshop.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.services.adjustments.Adjustment;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.IDADJUSTMENT,
				ConstantsViews.ERRORSELECTIDADJUSTMENT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsViews.ERRORSELECTDESCRIPTION);
		Adjustment adjustment = (Adjustment) arg0;
		if (Util.getNumber(adjustment.getAmountwork()).compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amountwork", ConstantsViews.ERRORSELECTAMOUNT);
		}
	}

}
