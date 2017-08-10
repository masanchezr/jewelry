package com.je.employee.validators;

import java.math.BigDecimal;
import java.util.Iterator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.services.shoppings.Shopping;

/**
 * The Class ShoppingsValidator.
 */
public class ShoppingsValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Shopping.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "numshop", "selectnumshop");
		Shopping shopping = (Shopping) arg0;
		Long numshop = shopping.getNumshop();
		BigDecimal cashamount = shopping.getCashamount();
		BigDecimal wiretransfer = shopping.getWiretransfer();
		// comprobamos que es positivo el numero
		if (numshop != null && numshop.compareTo(0L) <= 0) {
			arg1.rejectValue("numshop", "numpositive");
		}
		if (cashamount == null && wiretransfer == null) {
			arg1.rejectValue("cashamount", "selectamount");
		}
		Iterator<ObjectShopEntity> ios = shopping.getObjects().iterator();
		ObjectShopEntity os;
		BigDecimal netgrams, grossgrams, amount = BigDecimal.ZERO;
		while (ios.hasNext()) {
			os = ios.next();
			netgrams = os.getNetgrams();
			grossgrams = os.getGrossgrams();
			if (os.getAmount() != null) {
				amount = amount.add(os.getAmount());
			}
			if (netgrams == null && grossgrams != null) {
				arg1.rejectValue("numshop", "selectgrams");
			}
			if (netgrams != null && grossgrams != null) {
				if (os.getNetgrams().compareTo(BigDecimal.ZERO) <= 0
						|| os.getGrossgrams().compareTo(BigDecimal.ZERO) <= 0) {
					arg1.rejectValue("numshop", "selectgrams");
				}
			}
			if (netgrams != null && grossgrams == null) {
				arg1.rejectValue("numshop", "selectgrams");
			}
		}
		if (cashamount != null && wiretransfer != null && amount.compareTo(cashamount.add(wiretransfer)) != 0) {
			arg1.rejectValue("cashamount", "sumamounts");
		} else if (cashamount == null && wiretransfer != null && amount.compareTo(wiretransfer) != 0) {
			arg1.rejectValue("cashamount", "sumamounts");
		} else if (cashamount != null && wiretransfer == null && amount.compareTo(cashamount) != 0) {
			arg1.rejectValue("cashamount", "sumamounts");
		}
	}
}