package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.EntryMoneyEntity;

public class EntryMoneyValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return EntryMoneyEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", "selectamount");
		EntryMoneyEntity money = (EntryMoneyEntity) arg0;
		if (money.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}
	}

}
