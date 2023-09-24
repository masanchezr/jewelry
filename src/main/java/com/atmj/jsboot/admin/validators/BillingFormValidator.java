package com.atmj.jsboot.admin.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atmj.jsboot.admin.forms.BillingForm;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

@Component
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
			arg1.rejectValue(ConstantsViews.DATE, ConstantsViews.SELECTDATE);
		}

	}

}
