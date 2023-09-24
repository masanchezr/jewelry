package com.atmj.jsboot.services.payroll;

import com.atmj.jsboot.dbaccess.entities.PayrollEntity;
import com.atmj.jsboot.dbaccess.entities.PayrolltypeEntity;
import com.atmj.jsboot.services.dailies.Daily;

public interface PayrollService {

	public boolean existsPayroll(PayrollEntity payroll);

	public Daily addPayroll(PayrollEntity payroll);

	public Iterable<PayrolltypeEntity> getPayrollTypes();
}
