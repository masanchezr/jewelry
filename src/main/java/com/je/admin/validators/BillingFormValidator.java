package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.admin.forms.BillingForm;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class BillingFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return BillingForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		BillingForm billingSearch = (BillingForm) arg0;
		String sdate = billingSearch.getDate();
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			arg1.rejectValue(ConstantsJsp.DATE, ConstantsJsp.SELECTDATE);
		}

	}

}
