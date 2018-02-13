package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.users.User;
import com.je.utils.constants.Constants;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.USERNAME, "selectuser");

	}

}
