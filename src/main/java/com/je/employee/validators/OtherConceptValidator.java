package com.je.employee.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.otherconcepts.OtherConcept;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class OtherConceptValidator.
 */
public class OtherConceptValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return OtherConcept.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsJsp.ERRORSELECTDESCRIPTION);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", ConstantsJsp.ERRORSELECTAMOUNT);
		OtherConcept concept = (OtherConcept) arg0;
		if (concept == null || concept.getAmount() == 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		} else {

			String description = concept.getDescription();
			// no pongo pila porque puede ser la de la caja fuerte
			if (StringUtils.containsIgnoreCase(description, "saldo")
					|| StringUtils.containsIgnoreCase(description, "entrante")
					|| StringUtils.containsIgnoreCase(description, "correa")
					|| StringUtils.containsIgnoreCase(description, "nomina")) {
				arg1.rejectValue(Constants.DESCRIPTION, "funcionalityexists");
			}
		}
	}
}
