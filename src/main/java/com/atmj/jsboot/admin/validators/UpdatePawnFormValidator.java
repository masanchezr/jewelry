package com.atmj.jsboot.admin.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.jsboot.services.pawns.NewPawn;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

/**
 * The Class UpdatePawnFormValidator.
 */
@Component
public class UpdatePawnFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return NewPawn.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		NewPawn pawn = (NewPawn) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.NUMPAWN, Constants.IDPAWN);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.NAME, ConstantsViews.ERRORSELECTNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.SURNAME, ConstantsViews.ERRORSURNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.NIF, ConstantsViews.ERRORSELECTNIF);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.ADDRESS, ConstantsViews.ERRORSELECTADDRESS);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.PERCENT, ConstantsViews.ERRORSELECTPERCENT);
		String sdate = pawn.getCreationdate();
		BigDecimal percent = Util.getNumber(pawn.getPercent());
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			arg1.rejectValue(Constants.CREATIONDATE, ConstantsViews.SELECTDATE);
		}
		if (percent.compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(ConstantsViews.PERCENT, ConstantsViews.ERRORSELECTPERCENT);
		}
	}
}
