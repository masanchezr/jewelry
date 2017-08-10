package com.je.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.utils.string.Util;
import com.je.web.forms.DataClientForm;

/**
 * The Class DataClientFormValidator.
 */
public class DataClientFormValidator implements Validator {

	/** The Constant EMAIL_PATTERN. */
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/** The pattern. */
	private Pattern pattern;

	/** The matcher. */
	private Matcher matcher;

	public boolean supports(Class<?> arg0) {
		return DataClientForm.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		DataClientForm data = (DataClientForm) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "selectname");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "nif", "selectnif");
		String email = data.getEmail();
		String nif = data.getNif();
		if (!Util.isEmpty(email)) {
			// aqui validar email
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				arg1.rejectValue("email", "selectmail");
			}
		} else if (data.getTelephone() == null) {
			arg1.rejectValue("email", "selectmail");
			arg1.rejectValue("telephone", "selecttelephone");
		}
		if (Util.isEmpty(nif) || !Util.isNifNie(nif)) {
			arg1.rejectValue("nif", "selectnif");
		}
	}
}