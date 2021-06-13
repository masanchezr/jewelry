package com.je.jsboot.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.utils.constants.ConstantsViews;

public class JewelFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return JewelEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.REFERENCE, ConstantsViews.ERRORSELECTREFERENCE);
	}
}