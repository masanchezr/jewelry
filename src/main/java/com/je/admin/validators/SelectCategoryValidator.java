package com.je.admin.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.JewelEntity;
import com.je.forms.Jewel;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class SelectCategoryValidator.
 */
public class SelectCategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return JewelEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Jewel jewel = (Jewel) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.REFERENCE, ConstantsJsp.ERRORSELECTREFERENCE);
		BigDecimal price = jewel.getPrice();
		if (price == null || price.compareTo(BigDecimal.ZERO) == 0) {
			arg1.rejectValue("price", "selectprice");
		}
	}

}
