package com.je.employee.validators;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.JewelEntity;
import com.je.services.sales.SalePostPoned;
import com.je.utils.string.Util;

public class SalePostPonedValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return SalePostPoned.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idsale", "selectidsale");
		SalePostPoned sale = (SalePostPoned) arg0;
		Long numsale = sale.getIdsale();
		List<JewelEntity> jewels = sale.getJewels();
		if (jewels != null) {
			Iterator<JewelEntity> ijewels = jewels.iterator();
			boolean noreference = true;
			while (ijewels.hasNext() && noreference) {
				if (!Util.isEmpty(ijewels.next().getReference())) {
					noreference = false;
				}
			}
			if (noreference) {
				arg1.rejectValue("idsale", "selectreference");
			}
			BigDecimal optionalpay = sale.getOptionalpayment();
			if (optionalpay != null && BigDecimal.ZERO.compareTo(optionalpay) == 0) {
				arg1.rejectValue("idsale", "twopaycard");
			}
		} else {
			arg1.rejectValue("idsale", "selectreference");
		}
		if (numsale.compareTo(0L) <= 0) {
			arg1.rejectValue("idsale", "numsaleminuszero");
		}
	}

}
