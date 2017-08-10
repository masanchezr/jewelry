package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.admin.forms.ShoppingSearch;
import com.je.utils.string.Util;

/**
 * The Class ShoppingFormValidator.
 */
public class ShoppingFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return ShoppingSearch.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ShoppingSearch ssf = (ShoppingSearch) arg0;
		if (Util.isEmpty(ssf.getDatefrom()) && Util.isEmpty(ssf.getDateuntil()) && ssf.getNumshop() == null) {
			arg1.rejectValue("numshop", "selectnumshop");
			arg1.rejectValue("datefrom", "selectdate");
		} else if (Util.isEmpty(ssf.getDatefrom()) && !Util.isEmpty(ssf.getDateuntil())) {
			arg1.rejectValue("datefrom", "selectdate");
		}
	}

}
