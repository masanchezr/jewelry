package com.je.services.workshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.AdjustmentEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.WorkshopEntity;
import com.je.dbaccess.repositories.AdjustmentRepository;
import com.je.dbaccess.repositories.WorkshopRepository;
import com.je.services.metal.MetalService;
import com.je.utils.constants.Constants;

/**
 * The Class BillingServiceImpl.
 */
public class BillingServiceImpl implements BillingService {

	/** The adjustment repository. */
	@Autowired
	private AdjustmentRepository adjustmentRepository;

	/** The workshop repository. */
	@Autowired
	private WorkshopRepository workshopRepository;

	@Autowired
	private MetalService metalService;

	public Map<String, Object> getBill(Calendar current) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Billing> billing = new ArrayList<Billing>();
		Calendar from = new GregorianCalendar(current.get(Calendar.YEAR), current.get(Calendar.MONTH), 1);
		Calendar until = new GregorianCalendar(current.get(Calendar.YEAR), current.get(Calendar.MONTH),
				current.getActualMaximum(Calendar.DAY_OF_MONTH));
		List<AdjustmentEntity> adjustments = adjustmentRepository.findByCreationdateBetweenAndWorkTrue(from.getTime(),
				until.getTime());
		List<WorkshopEntity> works = workshopRepository.findByCreationdateBetween(from.getTime(), until.getTime());
		Iterator<AdjustmentEntity> iadjustments = adjustments.iterator();
		Iterator<WorkshopEntity> iworks = works.iterator();
		List<Worngrams> lworngrams = new ArrayList<Worngrams>(), lwg = new ArrayList<Worngrams>();
		Billing bill;
		AdjustmentEntity adjustment;
		WorkshopEntity work;
		MetalEntity metalEntity;
		Worngrams worngrams;
		BigDecimal amount, grams = BigDecimal.ZERO, totalamount = BigDecimal.ZERO;
		MetalEntity metal = metalService.findOne(Constants.ORO18K);
		while (iadjustments.hasNext()) {
			bill = new Billing();
			adjustment = iadjustments.next();
			amount = adjustment.getAmountwork();
			bill.setAmount(amount);
			bill.setDescription(adjustment.getDescription());
			bill.setIdadjustment(adjustment.getIdadjustment());
			bill.setCreationdate(adjustment.getCreationdate());
			bill.setGrams(adjustment.getGrams());
			bill.setMetal(metal);
			billing.add(bill);
			totalamount = totalamount.add(amount);
		}
		while (iworks.hasNext()) {
			bill = new Billing();
			work = iworks.next();
			amount = work.getAmount();
			if (work.getGrams() != null) {
				grams = work.getGrams();
			}
			bill.setAmount(amount);
			bill.setDescription(work.getDescription());
			bill.setIdadjustment(work.getIdworkshop());
			bill.setCreationdate(work.getCreationdate());
			metalEntity = work.getMetal();
			if (metalEntity != null) {
				worngrams = new Worngrams();
				worngrams.setGrams(grams);
				worngrams.setMetal(work.getMetal());
				bill.setGrams(grams);
				bill.setMetal(worngrams.getMetal());
				lworngrams.add(worngrams);
			}
			billing.add(bill);
			totalamount = totalamount.add(amount);
		}
		Iterable<MetalEntity> metals = metalService.getAllMetals();
		Iterator<MetalEntity> imetals = metals.iterator();
		while (imetals.hasNext()) {
			metal = imetals.next();
			grams = BigDecimal.ZERO;
			Iterator<Worngrams> iwg = lworngrams.iterator();
			while (iwg.hasNext()) {
				worngrams = iwg.next();
				if (worngrams.getMetal().equals(metal)) {
					grams = grams.add(worngrams.getGrams());
				}
			}
			if (grams.compareTo(BigDecimal.ZERO) > 0) {
				worngrams = new Worngrams();
				worngrams.setGrams(grams);
				worngrams.setMetal(metal);
				lwg.add(worngrams);
			}
		}
		map.put("totalamount", totalamount);
		map.put("billing", billing);
		map.put("grams", lwg);
		return map;
	}
}
