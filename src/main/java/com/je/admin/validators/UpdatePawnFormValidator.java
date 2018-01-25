package com.je.admin.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.pawns.NewPawn;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class UpdatePawnFormValidator.
 */
public class UpdatePawnFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return NewPawn.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {

		NewPawn pawn = (NewPawn) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "numpawn", "idpawn");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "selectname");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "surname", "selectsurname");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "nif", "selectnif");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "address", "selectaddress");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "percent", "selectpercent");
		String sdate = pawn.getCreationdate();
		BigDecimal percent = pawn.getPercent();
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			arg1.rejectValue("creationdate", "selectdate");
		}
		if (percent.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue("percent", "selectpercent");
		}
	}
}
