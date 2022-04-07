package com.je.jsboot.services.payroll;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;

import com.je.jsboot.dbaccess.entities.PayrolltypeEntity;
import com.je.jsboot.utils.constants.ConstantsViews;

public class Payroll {

	private String payrolldate;

	@DecimalMin(value = "0.1", message = ConstantsViews.ERRORSELECTAMOUNT)
	private BigDecimal amount;

	private PayrolltypeEntity payrolltype;

	public String getPayrolldate() {
		return payrolldate;
	}

	public void setPayrolldate(String payrolldate) {
		this.payrolldate = payrolldate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PayrolltypeEntity getPayrolltype() {
		return payrolltype;
	}

	public void setPayrolltype(PayrolltypeEntity payrolltype) {
		this.payrolltype = payrolltype;
	}
}
