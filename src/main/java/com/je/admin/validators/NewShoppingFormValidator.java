package com.je.admin.validators;

import java.math.BigDecimal;
import java.util.Iterator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.services.shoppings.Shopping;
import com.je.utils.constants.ConstantsViews;
import com.je.utils.string.Util;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTNUMSHOP);
		Shopping shopping = (Shopping) arg0;
		Long numshop = shopping.getNumshop();
		String description;
		Iterator<ObjectShopEntity> ios = shopping.getObjects().iterator();
		ObjectShopEntity os;
		BigDecimal cashamount = Util.getNumber(shopping.getCashamount());
		BigDecimal grossgrams;
		BigDecimal amount = BigDecimal.ZERO;
		// comprobamos que es positivo el numero
		if (numshop != null && numshop.compareTo(0L) <= 0) {
			arg1.rejectValue(ConstantsViews.NUMSHOP, "numpositive");
		}
		if (cashamount == null) {
			arg1.rejectValue(ConstantsViews.CASHAMOUNT, ConstantsViews.ERRORSELECTAMOUNT);
		}
		while (ios.hasNext()) {
			os = ios.next();
			if (os.getAmount() != null) {
				amount = amount.add(os.getAmount());
				grossgrams = os.getGrossgrams();
				description = os.getDescription();
				if (description == null) {
					arg1.rejectValue(ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTDESCRIPTION);
				}
				if (grossgrams == null || (grossgrams != null && grossgrams.compareTo(BigDecimal.ZERO) <= 0)) {
					arg1.rejectValue(ConstantsViews.NUMSHOP, ConstantsViews.ERRORSELECTGRAMS);
				}
			}
		}
		if (amount.compareTo(cashamount) != 0) {
			arg1.rejectValue(ConstantsViews.CASHAMOUNT, ConstantsViews.ERRORSUMAMOUNTS);
		}
	}
}