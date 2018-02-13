package com.je.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.web.forms.BuyForm;

/**
 * The Class BuyFormValidator.
 */
public class BuyFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {

		return BuyForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		BuyForm buyform = (BuyForm) arg0;
		if (buyform.getIdaddressmailing() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.ADDRESS, ConstantsJsp.ERRORSELECTADDRESS);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "postalcode", "selectpostcode");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "city", "selectcity");
		}
		// valido campos facturación
		if (buyform.getBilling_same_as_shipping() == Constants.CHECKED) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "namebilling", ConstantsJsp.ERRORSELECTNAME);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "cif", ConstantsJsp.ERRORSELECTNIF);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "addressbilling", ConstantsJsp.ERRORSELECTADDRESS);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "postalcodebilling", "selectpostcode");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "citybilling", "selectcity");
		}
		if (buyform.getPayment() == null) {
			arg1.reject(ConstantsJsp.FORMPAYMENT, "selectpayment");
		}
	}
}
