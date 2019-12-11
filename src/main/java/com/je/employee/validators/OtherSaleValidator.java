package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.OtherSaleEntity;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;

public class OtherSaleValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return OtherSaleEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
	}

}