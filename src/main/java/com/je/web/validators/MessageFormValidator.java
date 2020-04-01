package com.je.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;
import com.je.utils.string.Util;
import com.je.web.forms.MessageForm;

public class MessageFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MessageForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MessageForm data = (MessageForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.NAME, ConstantsViews.ERRORSELECTNAME);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", ConstantsViews.ERRORWRITEMESSAGE);
		String email = data.getEmail();
		if (!Util.isEmpty(email)) {
			// aqui validar email
			Pattern pattern = Pattern.compile(ConstantsViews.EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				errors.rejectValue(ConstantsViews.EMAIL, ConstantsViews.ERRORSELECTMAIL);
			}
		}
	}

}
