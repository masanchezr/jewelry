package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.pawns.Pawn;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class PawnFormValidator.
 */
public class PawnFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Pawn.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Pawn pawn = (Pawn) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.NUMPAWN, Constants.IDPAWN);
		double percent = pawn.getPercent();
		if (percent > 100 || percent < 0) {
			arg1.rejectValue(ConstantsJsp.ERRORSELECTADDRESS, "errorpercent");
		}
	}

}
