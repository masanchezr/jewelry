package com.je.services.payroll;

import com.je.services.dailies.Daily;

public interface PayrollService {

	public boolean existsPayroll(Payroll payroll);

	public Daily addPayroll(Payroll payroll);
}
