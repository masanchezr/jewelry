package com.je.admin.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.JewelEntity;

/**
 * The Class SelectCategoryValidator.
 */
public class SelectCategoryValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return JewelEntity.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		JewelEntity jewel = (JewelEntity) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "reference", "selectreference");
		BigDecimal price = jewel.getPrice();
		if (price == null || price.compareTo(BigDecimal.ZERO) == 0) {
			arg1.rejectValue("price", "selectprice");
		}
	}

}
