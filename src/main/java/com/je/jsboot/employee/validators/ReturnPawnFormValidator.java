package com.je.jsboot.employee.validators;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.dbaccess.entities.ObjectPawnEntity;
import com.je.jsboot.services.pawns.NewPawn;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;

@Component
public class ReturnPawnFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NewPawn.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewPawn pawn = (NewPawn) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsViews.PERCENT, ConstantsViews.ERRORSELECTPERCENT);
		BigDecimal percent = Util.getNumber(pawn.getPercent());
		BigDecimal amount = Util.getNumber(pawn.getAmount());
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			errors.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
		if (percent.compareTo(BigDecimal.ZERO) <= 0) {
			errors.rejectValue(ConstantsViews.PERCENT, ConstantsViews.ERRORSELECTPERCENT);
		}
		List<ObjectPawnEntity> lop = pawn.getObjects();
		Iterator<ObjectPawnEntity> ilop = lop.iterator();
		ObjectPawnEntity op;
		boolean isEmpty = true;
		while (ilop.hasNext()) {
			op = ilop.next();
			if (op.getGrossgrams() != null && Util.isEmpty(op.getDescription())) {
				errors.rejectValue(Constants.NUMPAWN, ConstantsViews.ERRORSELECTDESCRIPTION);
			} else if (op.getGrossgrams() == null && !Util.isEmpty(op.getDescription())) {
				errors.rejectValue(Constants.NUMPAWN, ConstantsViews.ERRORSELECTGRAMS);
			} else if (op.getGrossgrams() != null && !Util.isEmpty(op.getDescription())) {
				isEmpty = false;
			}
		}
		if (isEmpty) {
			errors.rejectValue(Constants.NUMPAWN, ConstantsViews.ERRORSELECTDESCRIPTION);
			errors.rejectValue(Constants.NUMPAWN, ConstantsViews.ERRORSELECTGRAMS);
		}
	}
}