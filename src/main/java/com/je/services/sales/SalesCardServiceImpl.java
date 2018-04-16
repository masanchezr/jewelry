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
import com.je.dbaccess.entities.SalePostponedEntity;
import com.je.dbaccess.entities.SalesJewels;
import com.je.dbaccess.entities.StrapEntity;
import com.je.dbaccess.managers.SaleManager;
import com.je.dbaccess.repositories.AdjustmentRepository;
import com.je.dbaccess.repositories.BatteriesRepository;
import com.je.dbaccess.repositories.RecordingRepository;
import com.je.dbaccess.repositories.SalesPostponedRepository;
import com.je.dbaccess.repositories.StrapsRepository;
import com.je.services.adjustments.Adjustment;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
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

	@Autowired
	private SalesPostponedRepository salespostponedrepository;

	@Override
	public Map<String, Object> searchByDates(SearchSale searchSale) {
		Date until = new Date();
		Map<String, Object> map = new HashMap<>();
		if (!Util.isEmpty(searchSale.getSuntil())) {
			until = DateUtil.getDate(searchSale.getSuntil());
		}
		PaymentEntity payment = searchSale.getPay();
		Date from = DateUtil.getDate(searchSale.getSfrom());
		BigDecimal total = BigDecimal.ZERO;
		int size = 0;
		size += putSales(map, from, until, payment, total);
		size += putAdjustments(map, from, until, payment, total);
		size += putRecordings(map, from, until, payment, total);
		size += putBatteries(map, from, until, payment, total);
		size += putStraps(map, from, until, payment, total);
		size += putSalesPost(map, from, until, payment, total);
		map.put("numsales", size);
		return map;
	}

	private int putSales(Map<String, Object> map, Date from, Date until, PaymentEntity payment, BigDecimal total) {
		int size = 0;
		Iterable<SaleEntity> salesEntity = saleManager.searchByDatesAndPayment(from, until, payment);
		if (salesEntity != null) {
			Iterator<SaleEntity> isales = salesEntity.iterator();
			List<Sale> sales = new ArrayList<>();
			while (isales.hasNext()) {
				SaleEntity saleEntity = isales.next();
				Sale sale = mapper.map(saleEntity, Sale.class);
				sale.setJewels(mapperListJewels(saleEntity.getSjewels()));
				sales.add(sale);
				total = total.add(sale.getTotal());
			}
			size = sales.size();
			map.put(ConstantsJsp.TOTAL, total.add((BigDecimal) map.get(ConstantsJsp.TOTAL)));
			map.put(Constants.SALES, sales);
		}
		return size;
	}

	private int putAdjustments(Map<String, Object> map, Date from, Date until, PaymentEntity payment,
			BigDecimal total) {
		int size = 0;
		List<AdjustmentEntity> adjustments = adjustmentRepository.findByCarrydateBetweenAndPayment(from, until,
				payment);
		List<Adjustment> ladjustments = null;
		if (adjustments != null && !adjustments.isEmpty()) {
			Iterator<AdjustmentEntity> iadjustments = adjustments.iterator();
			ladjustments = new ArrayList<>();
			while (iadjustments.hasNext()) {
				Adjustment adjustment = mapper.map(iadjustments.next(), Adjustment.class);
				ladjustments.add(adjustment);
				total = total.add(adjustment.getAmount());
			}
			size = ladjustments.size();
			map.put(ConstantsJsp.TOTAL, total.add((BigDecimal) map.get(ConstantsJsp.TOTAL)));
			map.put(Constants.ADJUSTMENTS, ladjustments);
		}
		return size;
	}

	private int putRecordings(Map<String, Object> map, Date from, Date until, PaymentEntity payment, BigDecimal total) {
		List<RecordingEntity> recordings = recordingRepository.findByCreationdateBetweenAndPay(from, until, payment);
		int size = 0;
		if (recordings != null && !recordings.isEmpty()) {
			Iterator<RecordingEntity> irecordings = recordings.iterator();
			while (irecordings.hasNext()) {
				total = total.add(irecordings.next().getAmount());
			}
			size = recordings.size();
			map.put(ConstantsJsp.TOTAL, total.add((BigDecimal) map.get(ConstantsJsp.TOTAL)));
			map.put(Constants.RECORDINGS, recordings);
		}
		return size;
	}

	private int putBatteries(Map<String, Object> map, Date from, Date until, PaymentEntity payment, BigDecimal total) {
		List<BatteryEntity> batteries = batteriesRepository.findByCreationdateBetweenAndPayment(from, until, payment);
		int size = 0;
		if (batteries != null && !batteries.isEmpty()) {
			Iterator<BatteryEntity> ibatteries = batteries.iterator();
			while (ibatteries.hasNext()) {
				total = total.add(ibatteries.next().getAmount());
			}
			size = batteries.size();
			map.put(ConstantsJsp.TOTAL, total.add((BigDecimal) map.get(ConstantsJsp.TOTAL)));
			map.put(Constants.BATTERIES, batteries);
		}
		return size;
	}

	private int putStraps(Map<String, Object> map, Date from, Date until, PaymentEntity payment, BigDecimal total) {
		List<StrapEntity> straps = strapsRepository.findByCreationdateBetweenAndPayment(from, until, payment);
		int size = 0;
		if (straps != null && !straps.isEmpty()) {
			Iterator<StrapEntity> istraps = straps.iterator();
			while (istraps.hasNext()) {
				total = total.add(istraps.next().getAmount());
			}
			size = straps.size();
			map.put(ConstantsJsp.TOTAL, total.add((BigDecimal) map.get(ConstantsJsp.TOTAL)));
			map.put(Constants.STRAPS, straps);
		}
		return size;
	}

	private int putSalesPost(Map<String, Object> map, Date from, Date until, PaymentEntity payment, BigDecimal total) {
		List<SalePostponedEntity> salespost = salespostponedrepository.findByCreationdateBetweenPay(from, until,
				payment);
		int size = 0;
		if (salespost != null && !salespost.isEmpty()) {
			Iterator<SalePostponedEntity> isalespost = salespost.iterator();
			while (isalespost.hasNext()) {
				total = total.add(isalespost.next().getTotalamount());
			}
			size = salespost.size();
		}
		map.put(ConstantsJsp.TOTAL, total.add((BigDecimal) map.get(ConstantsJsp.TOTAL)));
		map.put("salespost", salespost);
		return size;
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
		List<JewelEntity> jewels = new ArrayList<>();
		while (ijewels != null && ijewels.hasNext()) {
			jewels.add(ijewels.next().getJewelEntity());
		}
		return jewels;
	}
}
