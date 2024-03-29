package com.atmj.jsboot.employee.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.jsboot.services.rentals.Rental;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

@Component
public class RentalValidator implements Validator {

	public static final String RENTALDATE = "rentaldate";

	@Override
	public boolean supports(Class<?> arg0) {
		return Rental.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, RENTALDATE, ConstantsViews.SELECTDATE);
		Rental rental = (Rental) arg0;
		if (rental.getAmount() == null || BigDecimal.ZERO.compareTo(Util.getNumber(rental.getAmount())) == 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
		if (!DateUtil.isDate(rental.getRentaldate())) {
			arg1.rejectValue(RENTALDATE, ConstantsViews.SELECTDATE);
		}
	}
}
