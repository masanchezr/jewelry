package com.je.employee.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.payroll.Payroll;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class PayrollFormValidator implements Validator {

	public static final String ERRORPAYROLLDATE = "payrolldate";

	@Override
	public boolean supports(Class<?> arg0) {
		return Payroll.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ERRORPAYROLLDATE, ConstantsJsp.SELECTDATE);
		Payroll payroll = (Payroll) arg0;
		if (Util.getNumber(payroll.getAmount()).compareTo(BigDecimal.ZERO) <= 0) {
			arg1.rejectValue(Constants.AMOUNT, ConstantsJsp.ERRORSELECTAMOUNT);
		}
		if (!DateUtil.isDate(payroll.getPayrolldate())) {
			arg1.rejectValue(ERRORPAYROLLDATE, ConstantsJsp.SELECTDATE);
		}
	}
}
