package com.atmj.jsboot.admin.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atmj.jsboot.services.sets.NewSet;
import com.atmj.jsboot.utils.string.Util;

/**
 * The Class NewSetFormValidator.
 */
public class NewSetFormValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> arg0) {
		return NewSet.class.isAssignableFrom(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object arg0, Errors arg1) {
		NewSet set = (NewSet) arg0;
		String referencependant = set.getReferencependant(), referenceearrings = set.getReferenceearrings(),
				referencebrazalet = set.getReferencebrazalet(), referencering = set.getReferencering(),
				referencechoker = set.getReferencechoker(), referencecufflinks = set.getReferencecufflinks(),
				referencetiepin = set.getReferencetiepin();
		if (Util.isEmpty(referencering) && Util.isEmpty(referenceearrings) && Util.isEmpty(referencebrazalet)
				&& Util.isEmpty(referencependant) && Util.isEmpty(referencechoker) && Util.isEmpty(referencetiepin)
				&& Util.isEmpty(referencecufflinks)) {
			arg1.rejectValue("referencering", "selectreferencering");
			arg1.rejectValue("referenceearrings", "selectreferenceearrrings");
			arg1.rejectValue("referencependat", "selectreferencependat");
			arg1.rejectValue("referencebrazalet", "selectreferencebrazalet");
			arg1.rejectValue("referencechoker", "selectreferencechoker");
			arg1.rejectValue("referencebrazalet", "selectreferencecufflinks");
			arg1.rejectValue("referencechoker", "selectreferencetiepin");
		} else if (Util.isEmpty(referencering) && Util.isEmpty(referenceearrings) && Util.isEmpty(referencebrazalet)
				&& Util.isEmpty(referencechoker) && Util.isEmpty(referencetiepin) && Util.isEmpty(referencecufflinks)) {
			arg1.rejectValue("referencering", "selectreferencering");
			arg1.rejectValue("referencependat", "selectreferenceearrings");
			arg1.rejectValue("referencebrazalet", "selectreferencebrazalet");
			arg1.rejectValue("referencechoker", "selectreferencechoker");
			arg1.rejectValue("referencebrazalet", "selectreferencecufflinks");
			arg1.rejectValue("referencechoker", "selectreferencetiepin");
		} else if (Util.isEmpty(referenceearrings) && Util.isEmpty(referencebrazalet) && Util.isEmpty(referencependant)
				&& Util.isEmpty(referencechoker) && Util.isEmpty(referencetiepin) && Util.isEmpty(referencecufflinks)) {
			arg1.rejectValue("referenceearrings", "selectreferenceearrrings");
			arg1.rejectValue("referencependat", "selectreferencependat");
			arg1.rejectValue("referencebrazalet", "selectreferencebrazalet");
			arg1.rejectValue("referencechoker", "selectreferencechoker");
			arg1.rejectValue("referencebrazalet", "selectreferencecufflinks");
			arg1.rejectValue("referencechoker", "selectreferencetiepin");
		} else if (Util.isEmpty(referencering) && Util.isEmpty(referencebrazalet) && Util.isEmpty(referencependant)
				&& Util.isEmpty(referencechoker) && Util.isEmpty(referencetiepin) && Util.isEmpty(referencecufflinks)) {
			arg1.rejectValue("referencering", "selectreferencering");
			arg1.rejectValue("referencependat", "selectreferencependant");
			arg1.rejectValue("referencebrazalet", "selectreferencebrazalet");
			arg1.rejectValue("referencechoker", "selectreferencechoker");
			arg1.rejectValue("referencebrazalet", "selectreferencecufflinks");
			arg1.rejectValue("referencechoker", "selectreferencetiepin");
		} else if (Util.isEmpty(referencering) && Util.isEmpty(referenceearrings) && Util.isEmpty(referencebrazalet)
				&& Util.isEmpty(referencependant) && Util.isEmpty(referencetiepin)
				&& Util.isEmpty(referencecufflinks)) {
			arg1.rejectValue("referencering", "selectreferencering");
			arg1.rejectValue("referencependat", "selectreferencependant");
			arg1.rejectValue("referencebrazalet", "selectreferencebrazalet");
			arg1.rejectValue("referenceearrings", "selectreferenceearrrings");
			arg1.rejectValue("referencebrazalet", "selectreferencecufflinks");
			arg1.rejectValue("referencechoker", "selectreferencetiepin");
		}
	}

}
