package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.StrapEntity;
import com.je.forms.Strap;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;

public class StrapFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return StrapEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		Strap strap = (Strap) arg0;
		BigDecimal amount = strap.getAmount();
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}

	}

}
