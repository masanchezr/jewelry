package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.CoinEntity;
import com.je.utils.constants.Constants;

/**
 * The Class CoinFormValidator.
 */
public class CoinFormValidator implements Validator {
	@Override
	public boolean supports(Class<?> arg0) {
		return CoinEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, Constants.DESCRIPTION);
	}

}
