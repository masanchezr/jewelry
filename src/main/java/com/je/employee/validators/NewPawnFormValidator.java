package com.je.employee.validators;

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

import com.je.dbaccess.entities.ObjectPawnEntity;
import com.je.services.pawns.NewPawn;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.NAME, ConstantsJsp.ERRORSELECTNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsJsp.SURNAME, ConstantsJsp.ERRORSURNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsJsp.NIF, ConstantsJsp.ERRORSELECTNIF);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsJsp.ADDRESS, ConstantsJsp.ERRORSELECTADDRESS);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ConstantsJsp.PERCENT, ConstantsJsp.ERRORSELECTPERCENT);
		String sdate = pawn.getCreationdate();
		String birthday = pawn.getDatebirth();
		String dni = pawn.getNif();
		String numpawn = pawn.getNumpawn();
		BigDecimal percent = pawn.getPercent();
		BigDecimal amount = pawn.getAmount();
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			errors.rejectValue(Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		}
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			errors.rejectValue(Constants.CREATIONDATE, ConstantsJsp.SELECTDATE);
		}
		if (!Util.isEmpty(birthday)) {
			if (!DateUtil.isDate(birthday)) {
				errors.rejectValue(ERRORDATEBIRTH, ConstantsJsp.SELECTDATE);
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
		if (percent.compareTo(BigDecimal.ZERO) <= 0) {
			errors.rejectValue(ConstantsJsp.ERRORSELECTADDRESS, ConstantsJsp.ERRORSELECTPERCENT);
		}
		List<ObjectPawnEntity> lop = pawn.getObjects();
		Iterator<ObjectPawnEntity> ilop = lop.iterator();
		ObjectPawnEntity op;
		boolean isEmpty = true;
		while (ilop.hasNext()) {
			op = ilop.next();
			if (op.getGrossgrams() != null && Util.isEmpty(op.getDescription())) {
				errors.rejectValue(Constants.NUMPAWN, ConstantsJsp.ERRORSELECTDESCRIPTION);
			} else if (op.getGrossgrams() == null && !Util.isEmpty(op.getDescription())) {
				errors.rejectValue(Constants.NUMPAWN, ConstantsJsp.ERRORSELECTGRAMS);
			} else if (op.getGrossgrams() != null && !Util.isEmpty(op.getDescription())) {
				isEmpty = false;
			}
		}
		if (isEmpty) {
			errors.rejectValue(Constants.NUMPAWN, ConstantsJsp.ERRORSELECTDESCRIPTION);
			errors.rejectValue(Constants.NUMPAWN, ConstantsJsp.ERRORSELECTGRAMS);
		}
		if (dni != null && dni.length() > 10) {
			errors.rejectValue(ConstantsJsp.NIF, "niftoolong");
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsJsp.NIF, "nifnotvalid");
		}
		if (numpawn != null) {
			String pattern = "[a-zA-Z0-9]*";
			Pattern pa = Pattern.compile(pattern);
			Matcher matcher = pa.matcher(numpawn);
			if (!matcher.matches()) {
				errors.rejectValue(Constants.NUMPAWN, "novalidcaracter");
			}
		}
	}
}
