package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.BatteryEntity;
import com.je.forms.Battery;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

public class BatteryFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return BatteryEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", ConstantsJsp.ERRORSELECTAMOUNT);
		Battery battery = (Battery) arg0;
		BigDecimal amount = battery.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		}
	}

}
