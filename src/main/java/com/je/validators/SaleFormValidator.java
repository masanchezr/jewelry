package com.je.validators;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.JewelEntity;
import com.je.services.sales.Sale;
import com.je.utils.constants.Constants;
import com.je.utils.string.Util;

/**
 * The Class SaleFormValidator.
 */
public class SaleFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Sale.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "numsale", "selectidsale");
		Sale sale = (Sale) arg0;
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
				arg1.rejectValue("numsale", "selectreference");
			}
			BigDecimal optionalpay = sale.getOptionalpayment();
			if (optionalpay != null && BigDecimal.ZERO.compareTo(optionalpay) == 0
					&& sale.getPayment().getIdpayment().equals(Constants.CARD)) {
				arg1.rejectValue("numsale", "twopaycard");
			}
		} else {
			arg1.rejectValue("numsale", "selectreference");
		}
	}
}
