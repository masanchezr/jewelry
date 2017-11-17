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
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class NewPawnFormValidator.
 */
public class NewPawnFormValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return NewPawn.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewPawn pawn = (NewPawn) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numpawn", "idpawn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "selectname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "selectsurname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nif", "selectnif");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "selectaddress");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "percent", "selectpercent");
		String sdate = pawn.getCreationdate();
		String birthday = pawn.getDatebirth();
		String dni = pawn.getNif();
		String numpawn = pawn.getNumpawn();
		double percent = pawn.getPercent();
		BigDecimal amount = pawn.getAmount();
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			errors.rejectValue("amount", "selectamount");
		}
		if (!Util.isEmpty(sdate) && !DateUtil.isDate(sdate)) {
			errors.rejectValue("creationdate", "selectdate");
		}
		if (!Util.isEmpty(birthday)) {
			if (!DateUtil.isDate(birthday)) {
				errors.rejectValue("datebirth", "selectdate");
			} else {
				Date dbirthdaty = DateUtil.getDate(birthday);
				Calendar c = Calendar.getInstance();
				Calendar cbirthday = Calendar.getInstance();
				cbirthday.setTime(dbirthdaty);
				if (c.get(Calendar.YEAR) < cbirthday.get(Calendar.YEAR) + Constants.AGE) {
					errors.rejectValue("datebirth", "no18");
				} else if (c.get(Calendar.YEAR) == cbirthday.get(Calendar.YEAR) + Constants.AGE) {
					if (c.get(Calendar.MONTH) < c.get(Calendar.MONTH)) {
						errors.rejectValue("datebirth", "no18");
					} else if (c.get(Calendar.MONTH) == c.get(Calendar.MONTH)) {
						if (c.get(Calendar.DAY_OF_MONTH) < c.get(Calendar.DAY_OF_MONTH)) {
							errors.rejectValue("datebirth", "no18");
						}
					}
				}
			}
		}
		if (percent <= 0) {
			errors.rejectValue("percent", "selectpercent");
		}
		List<ObjectPawnEntity> lop = pawn.getObjects();
		Iterator<ObjectPawnEntity> ilop = lop.iterator();
		ObjectPawnEntity op;
		boolean isEmpty = true;
		while (ilop.hasNext()) {
			op = ilop.next();
			if (op.getGrossgrams() != null && Util.isEmpty(op.getDescription())) {
				errors.rejectValue("numpawn", "selectdescription");
			} else if (op.getGrossgrams() == null && !Util.isEmpty(op.getDescription())) {
				errors.rejectValue("numpawn", "selectgrams");
			} else if (op.getGrossgrams() != null && !Util.isEmpty(op.getDescription())) {
				isEmpty = false;
			}
		}
		if (isEmpty) {
			errors.rejectValue("numpawn", "selectdescription");
			errors.rejectValue("numpawn", "selectgrams");
		}
		if (dni != null && dni.length() > 10) {
			errors.rejectValue("nif", "niftoolong");
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue("nif", "nifnotvalid");
		}
		if (numpawn != null) {
			String pattern = "[a-zA-Z0-9]*";
			Pattern pa = Pattern.compile(pattern);
			Matcher matcher = pa.matcher(numpawn);
			if (!matcher.matches()) {
				errors.rejectValue("numpawn", "novalidcaracter");
			}
		}
	}
}
