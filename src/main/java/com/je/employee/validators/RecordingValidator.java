package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.RecordingEntity;

public class RecordingValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return RecordingEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", "selectamount");
	}

}
