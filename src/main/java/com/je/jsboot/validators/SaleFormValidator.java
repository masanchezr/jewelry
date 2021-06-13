package com.je.jsboot.validators;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.forms.Sale;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;

/**
 * The Class SaleFormValidator.
 */
public class SaleFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Sale.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.NUMSALE, ConstantsViews.ERRORSELECTIDSALE);
		Sale sale = (Sale) arg0;
		Long numsale = sale.getNumsale();
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
				arg1.rejectValue(ConstantsViews.NUMSALE, ConstantsViews.ERRORSELECTREFERENCE);
			}
			BigDecimal optionalpay = sale.getOptionalpayment();
			if (optionalpay != null && BigDecimal.ZERO.compareTo(optionalpay) == 0
					&& sale.getPayment().getIdpayment().equals(Constants.CARD)) {
				arg1.rejectValue(ConstantsViews.NUMSALE, "twopaycard");
			}
		} else {
			arg1.rejectValue(ConstantsViews.NUMSALE, ConstantsViews.ERRORSELECTREFERENCE);
		}
		if (numsale.compareTo(0L) <= 0) {
			arg1.rejectValue(ConstantsViews.NUMSALE, "numsaleminuszero");
		}
	}
}
