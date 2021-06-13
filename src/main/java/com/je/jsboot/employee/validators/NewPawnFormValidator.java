package com.je.jsboot.employee.validators;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.dbaccess.entities.ObjectPawnEntity;
import com.je.jsboot.services.pawns.NewPawn;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;
import com.je.jsboot.utils.string.Util;

/**
 * The Class NewPawnFormValidator.
 */
public class NewPawnFormValidator implements Validator {

	private static final String ERRORDATEBIRTH = "datebirth";

	@Override
	public boolean supports(Class<?> clazz) {
		return NewPawn.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewPawn pawn = (NewPawn) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.NUMPAWN, Constants.IDPAWN);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.NAME, ConstantsViews.ERRORSELECTNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsViews.SURNAME, ConstantsViews.ERRORSURNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsViews.NIF, ConstantsViews.ERRORSELECTNIF);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsViews.ADDRESS, ConstantsViews.ERRORSELECTADDRESS);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsViews.PERCENT, ConstantsViews.ERRORSELECTPERCENT);
		String sdate = pawn.getCreationdate();
		String birthday = pawn.getDatebirth();
		String dni = pawn.getNif();
		String numpawn = pawn.getNumpawn();
		BigDecimal percent = Util.getNumber(pawn.getPercent());
		BigDecimal amount = Util.getNumber(pawn.getAmount());
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			errors.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
		validateDates(sdate, birthday, errors);
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
		if (dni != null && dni.length() > 13) {
			errors.rejectValue(ConstantsViews.NIF, "niftoolong");
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsViews.NIF, "nifnotvalid");
		}
		validateNumpawn(numpawn, errors);
	}

	private void validateNumpawn(String numpawn, Errors errors) {
		if (numpawn != null) {
			String pattern = "[a-zA-Z0-9]*";
			Pattern pa = Pattern.compile(pattern);
			Matcher matcher = pa.matcher(numpawn);
			if (!matcher.matches()) {
				errors.rejectValue(Constants.NUMPAWN, "novalidcaracter");
			}
		}
	}

	private void validateDates(String sdate, String birthday, Errors errors) {
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			errors.rejectValue(Constants.CREATIONDATE, ConstantsViews.SELECTDATE);
		}
		if (!Util.isEmpty(birthday)) {
			if (!DateUtil.isDate(birthday)) {
				errors.rejectValue(ERRORDATEBIRTH, ConstantsViews.SELECTDATE);
			} else {
				Date dbirthday = DateUtil.getDate(birthday);
				Calendar c = Calendar.getInstance();
				Calendar cbirthday = Calendar.getInstance();
				cbirthday.setTime(dbirthday);
				cbirthday.add(Calendar.YEAR, Constants.AGE);
				if (c.before(cbirthday)) {
					errors.rejectValue(ERRORDATEBIRTH, "no18");
				}
			}
		}
	}
}
