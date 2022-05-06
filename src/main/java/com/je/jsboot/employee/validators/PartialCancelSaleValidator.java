package com.je.jsboot.employee.validators;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.jsboot.forms.Sale;
import com.je.jsboot.utils.constants.ConstantsViews;

@Component
public class PartialCancelSaleValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Sale.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Sale sale = (Sale) arg0;
		List<Long> jewelscancel = sale.getJewelstocancel();
		if (jewelscancel == null || jewelscancel.isEmpty()) {
			arg1.rejectValue(ConstantsViews.IDSALE, "selectjeweltocancel");
		}
	}

}
