package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.EntryMoneyEntity;
import com.je.forms.EntryMoney;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;

public class EntryMoneyValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return EntryMoneyEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", ConstantsViews.ERRORSELECTAMOUNT);
		EntryMoney money = (EntryMoney) arg0;
		if (money.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
	}

}
