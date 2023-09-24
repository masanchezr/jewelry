package com.atmj.jsboot.employee.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.jsboot.services.sales.Installment;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.string.Util;

@Component
public class InstallmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Installment.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.IDSALEPOSTPONED, ConstantsViews.ERRORSELECTIDSALE);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", ConstantsViews.ERRORSELECTAMOUNT);
		Installment installment = (Installment) arg0;
		BigDecimal amount = Util.getNumber(installment.getAmount());
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
	}
}
