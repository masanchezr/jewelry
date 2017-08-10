package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.admin.forms.BillingForm;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class BillingFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return BillingForm.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		BillingForm billingSearch = (BillingForm) arg0;
		String sdate = billingSearch.getDate();
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			arg1.rejectValue("date", "selectdate");
		}

	}

}
