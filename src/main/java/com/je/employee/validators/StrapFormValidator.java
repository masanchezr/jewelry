package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.StrapEntity;

public class StrapFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return StrapEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", "selectamount");
		StrapEntity strap = (StrapEntity) arg0;
		BigDecimal amount = strap.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}

	}

}
