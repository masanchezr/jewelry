package com.je.services.payroll;

import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.entities.PayrolltypeEntity;
import com.je.services.dailies.Daily;

public interface PayrollService {

	public boolean existsPayroll(PayrollEntity payroll);

	public Daily addPayroll(PayrollEntity payroll);

	public Iterable<PayrolltypeEntity> getPayrollTypes();
}
