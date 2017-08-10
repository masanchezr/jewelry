package com.je.employee.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.otherconcepts.OtherConcept;

/**
 * The Class OtherConceptValidator.
 */
public class OtherConceptValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return OtherConcept.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description",
				"selectdescription");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount",
				"selectamount");
		OtherConcept concept = (OtherConcept) arg0;
		if (concept == null || concept.getAmount() == 0) {
			arg1.rejectValue("amount", "selectamount");
		}

		String description = concept.getDescription();
		// no pongo pila porque puede ser la de la caja fuerte
		if (StringUtils.containsIgnoreCase(description, "saldo")
				|| StringUtils.containsIgnoreCase(description, "entrante")
				|| StringUtils.containsIgnoreCase(description, "correa")
				|| StringUtils.containsIgnoreCase(description, "nomina")) {
			arg1.rejectValue("description", "funcionalityexists");
		}
	}

}
