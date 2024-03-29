package com.atmj.jsboot.admin.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.forms.Jewel;
import com.atmj.jsboot.utils.constants.ConstantsViews;

/**
 * The Class SelectCategoryValidator.
 */

@Component
public class SelectCategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return JewelEntity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Jewel jewel = (Jewel) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.REFERENCE, ConstantsViews.ERRORSELECTREFERENCE);
		BigDecimal price = jewel.getPrice();
		if (price == null || price.compareTo(BigDecimal.ZERO) == 0) {
			arg1.rejectValue("price", "selectprice");
		}
	}

}
