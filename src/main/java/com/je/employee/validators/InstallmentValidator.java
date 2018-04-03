package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.sales.Installment;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

public class InstallmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Installment.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDSALEPOSTPONED, ConstantsJsp.ERRORSELECTIDSALE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", ConstantsJsp.ERRORSELECTAMOUNT);
		Installment installment = (Installment) arg0;
		BigDecimal amount = installment.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		}
	}
}
