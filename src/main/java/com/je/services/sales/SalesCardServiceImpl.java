package com.je.services.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.AdjustmentEntity;
import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.RecordingEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.entities.SalesJewels;
import com.je.dbaccess.entities.StrapEntity;
import com.je.dbaccess.managers.SaleManager;
import com.je.dbaccess.repositories.AdjustmentRepository;
import com.je.dbaccess.repositories.BatteriesRepository;
import com.je.dbaccess.repositories.RecordingRepository;
import com.je.dbaccess.repositories.StrapsRepository;
import com.je.services.adjustments.Adjustment;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class SalesCardServiceImpl implements SalesCardService {

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	/** The sale manager. */
	@Autowired
	private SaleManager saleManager;

	@Autowired
	private AdjustmentRepository adjustmentRepository;

	@Autowired
	private RecordingRepository recordingRepository;

	@Autowired
	private BatteriesRepository batteriesRepository;

	@Autowired
	private StrapsRepository strapsRepository;

	@Override
	public Map<String, Object> searchByDates(SearchSale searchSale) {
		Date until = new Date();
		List<Sale> sales = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if (!Util.isEmpty(searchSale.getSuntil())) {
			until = DateUtil.getDate(searchSale.getSuntil());
		}
		PaymentEntity payment = searchSale.getPay();
		Date from = DateUtil.getDate(searchSale.getSfrom());
		Iterable<SaleEntity> salesEntity = saleManager.searchByDatesAndPayment(from, until, payment);
		BigDecimal total = BigDecimal.ZERO;
		int size = 0;
		if (salesEntity != null) {
			Iterator<SaleEntity> isales = salesEntity.iterator();
			sales = new ArrayList<Sale>();
			while (isales.hasNext()) {
				SaleEntity saleEntity = isales.next();
				Sale sale = mapper.map(saleEntity, Sale.class);
				sale.setJewels(mapperListJewels(saleEntity.getSjewels()));
				sales.add(sale);
				total = total.add(sale.getTotal());
			}
			size = sales.size();
		}
		List<AdjustmentEntity> adjustments = adjustmentRepository.findByCarrydateBetweenAndPayment(from, until,
				payment);
		List<Adjustment> ladjustments = null;
		if (adjustments != null && !adjustments.isEmpty()) {
			Iterator<AdjustmentEntity> iadjustments = adjustments.iterator();
			ladjustments = new ArrayList<Adjustment>();
			while (iadjustments.hasNext()) {
				Adjustment adjustment = mapper.map(iadjustments.next(), Adjustment.class);
				ladjustments.add(adjustment);
				total = total.add(adjustment.getAmount());
			}
			size += ladjustments.size();
		}
		List<RecordingEntity> recordings = recordingRepository.findByCreationdateBetweenAndPay(from, until, payment);
		if (recordings != null && !recordings.isEmpty()) {
			Iterator<RecordingEntity> irecordings = recordings.iterator();
			while (irecordings.hasNext()) {
				total = total.add(irecordings.next().getAmount());
			}
			size += recordings.size();
		}
		List<BatteryEntity> batteries = batteriesRepository.findByCreationdateBetweenAndPayment(from, until, payment);
		if (batteries != null && !batteries.isEmpty()) {
			Iterator<BatteryEntity> ibatteries = batteries.iterator();
			while (ibatteries.hasNext()) {
				total = total.add(ibatteries.next().getAmount());
			}
			size += batteries.size();
		}
		List<StrapEntity> straps = strapsRepository.findByCreationdateBetweenAndPayment(from, until, payment);
		if (straps != null && !straps.isEmpty()) {
			Iterator<StrapEntity> istraps = straps.iterator();
			while (istraps.hasNext()) {
				total = total.add(istraps.next().getAmount());
			}
			size += straps.size();
		}
		map.put("straps", straps);
		map.put("batteries", batteries);
		map.put("recordings", recordings);
		map.put("adjustments", ladjustments);
		map.put("numsales", size);
		map.put("sales", sales);
		map.put("total", total);
		return map;
	}

	/**
	 * Mapper list jewels.
	 * 
	 * @param sjewels
	 *            the sjewels
	 * @return the list
	 */
	private List<JewelEntity> mapperListJewels(List<SalesJewels> sjewels) {
		Iterator<SalesJewels> ijewels = sjewels.iterator();
		List<JewelEntity> jewels = new ArrayList<JewelEntity>();
		while (ijewels != null && ijewels.hasNext()) {
			jewels.add(ijewels.next().getJewelEntity());
		}
		return jewels;
	}
}
