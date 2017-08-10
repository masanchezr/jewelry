package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.JewelEntity;

public class JewelFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return JewelEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "reference", "selectreference");
	}
}