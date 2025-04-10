package com.atmj.jsboot.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.web.forms.BuyForm;

/**
 * The Class BuyFormValidator.
 */

@Component
public class BuyFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {

		return BuyForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		BuyForm buyform = (BuyForm) arg0;
		if (buyform.getIdaddressmailing() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.ADDRESS, ConstantsViews.ERRORSELECTADDRESS);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "postalcode", "selectpostcode");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "city", "selectcity");
		}
		// valido campos facturación
		if (buyform.getBilling_same_as_shipping() == Constants.CHECKED) {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "namebilling", ConstantsViews.ERRORSELECTNAME);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "cif", ConstantsViews.ERRORSELECTNIF);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "addressbilling", ConstantsViews.ERRORSELECTADDRESS);
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "postalcodebilling", "selectpostcode");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "citybilling", "selectcity");
		}
		if (buyform.getPayment() == null) {
			arg1.rejectValue(ConstantsViews.FORMPAYMENT, "selectpayment");
		}
	}
}
