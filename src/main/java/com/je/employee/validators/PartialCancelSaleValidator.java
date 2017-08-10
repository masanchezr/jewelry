package com.je.employee.validators;

import java.util.Iterator;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.JewelEntity;
import com.je.services.sales.Sale;

public class PartialCancelSaleValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Sale.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Sale sale = (Sale) arg0;
		List<JewelEntity> jewelscancel = sale.getJewelstocancel();
		Iterator<JewelEntity> ijewelscancel = jewelscancel.iterator();
		boolean isValid = false;
		while (ijewelscancel.hasNext() && !isValid) {
			if (ijewelscancel.next().getIdjewel() != null) {
				isValid = true;
			}
		}
		if (!isValid) {
			arg1.rejectValue("idsale", "selectjeweltocancel");
		}
	}

}
