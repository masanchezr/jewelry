package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.rentals.Rental;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class RentalValidator implements Validator {

	public static final String RENTALDATE = "rentaldate";

	@Override
	public boolean supports(Class<?> arg0) {
		return Rental.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, RENTALDATE, ConstantsJsp.SELECTDATE);
		Rental rental = (Rental) arg0;
		if (rental.getAmount() == null || Util.getNumber(rental.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		}
		if (!DateUtil.isDate(rental.getRentaldate())) {
			arg1.rejectValue(RENTALDATE, ConstantsJsp.SELECTDATE);
		}
	}
}
