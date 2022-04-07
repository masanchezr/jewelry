package com.je.jsboot.employee.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.services.otherconcepts.OtherConcept;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;

/**
 * The Class OtherConceptValidator.
 */

@Component
public class OtherConceptValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return OtherConcept.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.DESCRIPTION, ConstantsViews.ERRORSELECTDESCRIPTION);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount", ConstantsViews.ERRORSELECTAMOUNT);
		OtherConcept concept = (OtherConcept) arg0;
		if (concept == null || Util.getNumber(concept.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		} else {
			String description = concept.getDescription();
			// no pongo pila porque puede ser la de la caja fuerte
			if (description.contains("saldo") || description.contains("entrante") || description.contains("correa")
					|| description.contains("nomina") || description.contains("comisi")
					|| description.contains("incentivo") || description.contains("suplem")
					|| description.contains("venta")) {
				arg1.rejectValue(Constants.DESCRIPTION, "funcionalityexists");
			}
		}
	}
}
