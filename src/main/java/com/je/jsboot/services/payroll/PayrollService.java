package com.je.jsboot.services.payroll;

import com.je.jsboot.dbaccess.entities.PayrollEntity;
import com.je.jsboot.dbaccess.entities.PayrolltypeEntity;
import com.je.jsboot.services.dailies.Daily;

public interface PayrollService {

	public boolean existsPayroll(PayrollEntity payroll);

	public Daily addPayroll(PayrollEntity payroll);

	public Iterable<PayrolltypeEntity> getPayrollTypes();
}
