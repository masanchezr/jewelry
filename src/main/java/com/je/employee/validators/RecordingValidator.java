package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.RecordingEntity;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

public class RecordingValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return RecordingEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
	}

}
