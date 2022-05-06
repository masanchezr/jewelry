package com.je.jsboot.employee.validators;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.forms.SalePostPoned;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;

@Component
public class SalePostPonedValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return SalePostPoned.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.IDSALE, ConstantsViews.ERRORSELECTIDSALE);
		SalePostPoned sale = (SalePostPoned) arg0;
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
				arg1.rejectValue(ConstantsViews.IDSALE, ConstantsViews.ERRORSELECTREFERENCE);
			}
			BigDecimal optionalpay = sale.getOptionalpayment();
			if (optionalpay != null && BigDecimal.ZERO.compareTo(optionalpay) == 0) {
				arg1.rejectValue(ConstantsViews.IDSALE, "twopaycard");
			}
		} else {
			arg1.rejectValue(ConstantsViews.IDSALE, ConstantsViews.ERRORSELECTREFERENCE);
		}
	}
}