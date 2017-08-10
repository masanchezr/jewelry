package com.je.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.utils.constants.Constants;
import com.je.web.forms.BuyForm;

/**
 * The Class BuyFormValidator.
 */
public class BuyFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {

		return BuyForm.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		// Es todo obligatorio excepto el mail y el telefono, aunque uno de los
		// dos si debe estar
		BuyForm buyform = (BuyForm) arg0;
		if (buyform.getIdaddressmailing() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "address", "selectaddress");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "postalcode", "selectpostcode");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "city", "selectcity");
		}
		// valido campos facturación
		if (buyform.getBilling_same_as_shipping() == Constants.CHECKED) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "namebilling", "selectname");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "cif", "selectnif");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "addressbilling", "selectaddress");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "postalcodebilling", "selectpostcode");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "citybilling", "selectcity");
		}
		if (buyform.getPayment() == null) {
			arg1.reject("payment", "selectpayment");
		}
	}
}
