package com.je.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.CoinEntity;

/**
 * The Class CoinFormValidator.
 */
public class CoinFormValidator implements Validator {
	public boolean supports(Class<?> arg0) {
		return CoinEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		// Coin coin = (Coin) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description", "description");
	}

}
