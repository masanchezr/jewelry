package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.forms.Sale;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.NUMSALE, ConstantsJsp.ERRORSELECTIDSALE);
		Sale removeSaleForm = (Sale) arg0;
		if (removeSaleForm.getPayment().getIdpayment().equals(Constants.DISCOUNT)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDDISCOUNT, ConstantsJsp.ERRORNUMDISCOUNT);
		} else if (removeSaleForm.getPayment().getIdpayment().equals(ConstantsJsp.DISCOUNTANDCASH)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDDISCOUNT, ConstantsJsp.ERRORNUMDISCOUNT);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "optionalpayment", "erroroptionalpaymentdiscount");
		}
	}

}
