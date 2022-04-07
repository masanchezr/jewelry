package com.je.jsboot.admin.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.jsboot.admin.forms.ShoppingSearch;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;

/**
 * The Class ShoppingFormValidator.
 */

@Component
public class ShoppingFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ShoppingSearch.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ShoppingSearch ssf = (ShoppingSearch) arg0;
		if (Util.isEmpty(ssf.getDatefrom()) && Util.isEmpty(ssf.getDateuntil()) && ssf.getNumshop() == null) {
			arg1.rejectValue(ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTNUMSHOP);
			arg1.rejectValue(ConstantsViews.DATEFROM, ConstantsViews.SELECTDATE);
		} else if (Util.isEmpty(ssf.getDatefrom()) && !Util.isEmpty(ssf.getDateuntil())) {
			arg1.rejectValue(ConstantsViews.DATEFROM, ConstantsViews.SELECTDATE);
		}
	}

}
