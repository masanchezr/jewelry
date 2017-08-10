package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.rentals.Rental;
import com.je.utils.date.DateUtil;

public class RentalValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Rental.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", "selectamount");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "rentaldate", "selectdate");

		Rental rental = (Rental) arg0;
		if (rental.getAmount() == null || rental.getAmount().compareTo(BigDecimal.ZERO) == 0) {
			arg1.rejectValue("amount", "selectamount");
		}
		if (!DateUtil.isDate(rental.getRentaldate())) {
			arg1.rejectValue("rentaldate", "selectdate");
		}
	}

}
