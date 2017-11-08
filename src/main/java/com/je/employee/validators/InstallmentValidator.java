package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.sales.Installment;

public class InstallmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Installment.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idsalepostponed", "selectidsale");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", "selectamount");
		Installment installment = (Installment) arg0;
		BigDecimal amount = installment.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}
	}
}