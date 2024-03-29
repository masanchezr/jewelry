package com.atmj.jsboot.employee.validators;

import java.math.BigDecimal;
import java.util.Iterator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atmj.jsboot.dbaccess.entities.ObjectShopEntity;
import com.atmj.jsboot.services.shoppings.Shopping;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.string.Util;

/**
 * The Class ShoppingsValidator.
 */
@Component
public class ShoppingsValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Shopping.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTNUMSHOP);
		Shopping shopping = (Shopping) arg0;
		Long numshop = shopping.getNumshop();
		BigDecimal cashamount = Util.getNumber(shopping.getCashamount());
		BigDecimal wiretransfer = Util.getNumber(shopping.getWiretransfer());
		// comprobamos que es positivo el numero
		if (numshop != null && numshop.compareTo(0L) <= 0) {
			arg1.rejectValue(ConstantsViews.NUMSHOP, "numpositive");
		}
		Iterator<ObjectShopEntity> ios = shopping.getObjects().iterator();
		ObjectShopEntity os;
		BigDecimal netgrams;
		BigDecimal grossgrams;
		BigDecimal amount = BigDecimal.ZERO;
		while (ios.hasNext()) {
			os = ios.next();
			netgrams = os.getNetgrams();
			grossgrams = os.getGrossgrams();
			if (os.getAmount() != null) {
				amount = amount.add(os.getAmount());
			}
			if (netgrams == null && grossgrams != null) {
				arg1.rejectValue(ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTGRAMS);
			}
			if (netgrams != null && grossgrams != null
					&& (netgrams.compareTo(BigDecimal.ZERO) <= 0 || grossgrams.compareTo(BigDecimal.ZERO) <= 0)) {
				arg1.rejectValue(ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTGRAMS);
			}
			if (netgrams != null && grossgrams == null) {
				arg1.rejectValue(ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTGRAMS);
			}
		}
		checkAmounts(amount, cashamount, wiretransfer, arg1);
	}

	private void checkAmounts(BigDecimal amount, BigDecimal cashamount, BigDecimal wiretransfer, Errors arg1) {
		if (cashamount == null && wiretransfer == null) {
			arg1.rejectValue(ConstantsViews.CASHAMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		} else if (cashamount != null && wiretransfer != null && amount.compareTo(cashamount.add(wiretransfer)) != 0) {
			arg1.rejectValue(ConstantsViews.CASHAMOUNT, ConstantsViews.ERRORSUMAMOUNTS);
		} else if (cashamount != null && wiretransfer == null && amount.compareTo(cashamount) != 0) {
			arg1.rejectValue(ConstantsViews.CASHAMOUNT, ConstantsViews.ERRORSUMAMOUNTS);
		} else if (cashamount == null && amount.compareTo(wiretransfer) != 0) {
			arg1.rejectValue(ConstantsViews.CASHAMOUNT, ConstantsViews.ERRORSUMAMOUNTS);
		}
	}
}