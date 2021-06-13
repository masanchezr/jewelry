package com.je.jsboot.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.forms.Sale;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;

/**
 * The Class RemoveSaleFormValidator.
 */
public class RemoveSaleFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Sale.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.NUMSALE, ConstantsViews.ERRORSELECTIDSALE);
		Sale removeSaleForm = (Sale) arg0;
		if (removeSaleForm.getPayment().getIdpayment().equals(Constants.DISCOUNT)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDDISCOUNT, ConstantsViews.ERRORNUMDISCOUNT);
		} else if (removeSaleForm.getPayment().getIdpayment().equals(ConstantsViews.DISCOUNTANDCASH)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDDISCOUNT, ConstantsViews.ERRORNUMDISCOUNT);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "optionalpayment", "erroroptionalpaymentdiscount");
		}
	}

}
