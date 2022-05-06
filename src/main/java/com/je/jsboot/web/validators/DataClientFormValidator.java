package com.je.jsboot.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;
import com.je.jsboot.web.forms.DataClientForm;

/**
 * The Class DataClientFormValidator.
 */
@Component
public class DataClientFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return DataClientForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		DataClientForm data = (DataClientForm) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.NAME, ConstantsViews.ERRORSELECTNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.NIF, ConstantsViews.ERRORSELECTNIF);
		String email = data.getEmail();
		String nif = data.getNif();
		if (!Util.isEmpty(email)) {
			// aqui validar email
			Pattern pattern = Pattern.compile(ConstantsViews.EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				arg1.rejectValue(ConstantsViews.EMAIL, ConstantsViews.ERRORSELECTMAIL);
			}
		} else if (data.getTelephone() == null) {
			arg1.rejectValue(ConstantsViews.EMAIL, ConstantsViews.ERRORSELECTMAIL);
			arg1.rejectValue("telephone", "selecttelephone");
		}
		if (Util.isEmpty(nif) || !Util.isNifNie(nif)) {
			arg1.rejectValue(ConstantsViews.NIF, ConstantsViews.ERRORSELECTNIF);
		}
	}
}