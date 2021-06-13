package com.je.jsboot.services.workshop;

import java.util.Calendar;
import java.util.Map;

/**
 * The Interface BillingService.
 */
public interface BillingService {

	/**
	 * Gets the current month bill.
	 *
	 * @return the current month bill
	 */
	public Map<String, Object> getBill(Calendar current);
}
