package com.je.jsboot.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.admin.forms.SearchMissingNumbers;

public class SearchMissingNumbersValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return SearchMissingNumbers.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "numfrom",
				"selectnumber");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "numuntil",
				"selectnumber");
		SearchMissingNumbers sms = (SearchMissingNumbers) arg0;
		Long numshopfrom = sms.getNumfrom();
		Long numshopuntil = sms.getNumuntil();
		if (numshopfrom.compareTo(numshopuntil) >= 0) {
			arg1.rejectValue("numfrom", "numbergreaterthan");
		}
	}
}
