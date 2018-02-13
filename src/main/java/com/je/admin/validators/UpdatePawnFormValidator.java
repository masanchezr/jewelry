package com.je.admin.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.pawns.NewPawn;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
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
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.NUMPAWN, Constants.IDPAWN);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.NAME, ConstantsJsp.ERRORSELECTNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.SURNAME, ConstantsJsp.ERRORSURNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.NIF, ConstantsJsp.ERRORSELECTNIF);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.ADDRESS, ConstantsJsp.ERRORSELECTADDRESS);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.ERRORSELECTADDRESS, ConstantsJsp.ERRORSELECTPERCENT);
		String sdate = pawn.getCreationdate();
		BigDecimal percent = pawn.getPercent();
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			arg1.rejectValue(Constants.CREATIONDATE, ConstantsJsp.SELECTDATE);
		}
		if (percent.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(ConstantsJsp.ERRORSELECTADDRESS, ConstantsJsp.ERRORSELECTPERCENT);
		}
	}
}
