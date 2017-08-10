package com.je.admin.validators;

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
public class NewShoppingFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Shopping.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "numshop", "selectnumshop");
		Shopping shopping = (Shopping) arg0;
		Long numshop = shopping.getNumshop();
		String description;
		Iterator<ObjectShopEntity> ios = shopping.getObjects().iterator();
		ObjectShopEntity os;
		BigDecimal cashamount = shopping.getCashamount(), grossgrams, amount = BigDecimal.ZERO;
		// comprobamos que es positivo el numero
		if (numshop != null && numshop.compareTo(0L) <= 0) {
			arg1.rejectValue("numshop", "numpositive");
		}
		if (cashamount == null) {
			arg1.rejectValue("cashamount", "selectamount");
		}
		while (ios.hasNext()) {
			os = ios.next();
			if (os.getAmount() != null) {
				amount = amount.add(os.getAmount());
				grossgrams = os.getGrossgrams();
				description = os.getDescription();
				if (description == null) {
					arg1.rejectValue("numshop", "selectdescription");
				}
				if (grossgrams != null && grossgrams.compareTo(BigDecimal.ZERO) <= 0) {
					arg1.rejectValue("numshop", "selectgrams");
				} else if (grossgrams == null) {
					arg1.rejectValue("numshop", "selectgrams");
				}
			}
		}
		if (cashamount != null && amount.compareTo(cashamount) != 0) {
			arg1.rejectValue("cashamount", "sumamounts");
		}
	}
}