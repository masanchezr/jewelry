package com.je.jsboot.services.adjustments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.services.dailies.Daily;

/**
 * The Interface AdjustmentService.
 */
public interface AdjustmentService {

	/**
	 * Save.
	 *
	 * @param adjustment the adjustment
	 * @return
	 */
	public Daily save(Adjustment adjustment);

	/**
	 * Save workshop.
	 *
	 * @param adjustment the adjustment
	 * @return
	 */
	Daily saveWorkshop(Adjustment adjustment);

	public Map<String, BigDecimal> sumAdjustmentByDates(Date from, Date until, PlaceEntity place);

	public Adjustment findById(Long idadjustment);

}
