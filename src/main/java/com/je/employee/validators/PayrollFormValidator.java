package com.je.employee.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.je.services.payroll.Payroll;
import com.je.utils.date.DateUtil;

public class PayrollFormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Payroll.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "amount",
				"selectamount");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "payrolldate",
				"selectdate");
		Payroll payroll = (Payroll) arg0;
		if (payroll.getAmount() <= 0) {
			arg1.rejectValue("amount", "selectamount");
		}
		if (!DateUtil.isDate(payroll.getPayrolldate())) {
			arg1.rejectValue("payrolldate", "selectdate");
		}
	}
}
