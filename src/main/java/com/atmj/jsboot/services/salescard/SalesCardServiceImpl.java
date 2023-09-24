package com.atmj.jsboot.services.salescard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.AdjustmentEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.OtherSaleEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.SaleEntity;
import com.atmj.jsboot.dbaccess.entities.SalePostponedEntity;
import com.atmj.jsboot.dbaccess.entities.SalesJewels;
import com.atmj.jsboot.dbaccess.managers.SaleManager;
import com.atmj.jsboot.dbaccess.repositories.AdjustmentRepository;
import com.atmj.jsboot.dbaccess.repositories.OtherSaleRepository;
import com.atmj.jsboot.dbaccess.repositories.SalesPostponedRepository;
import com.atmj.jsboot.forms.Sale;
import com.atmj.jsboot.services.adjustments.Adjustment;
import com.atmj.jsboot.services.converters.SaleEntityConverter;
import com.atmj.jsboot.services.sales.SearchSale;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

@Service
public class SalesCardServiceImpl implements SalesCardService {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	/** The sale manager. */
	@Autowired
	private SaleManager saleManager;

	@Autowired
	private AdjustmentRepository adjustmentRepository;

	@Autowired
	private OtherSaleRepository othersalerepository;

	@Autowired
	private SalesPostponedRepository salespostponedrepository;

	@Autowired
	private SaleEntityConverter converter;

	@Override
	public Map<String, Object> searchByDates(SearchSale searchSale) {
		Date until = new Date();
		Map<String, Object> map = new HashMap<>();
		if (!Util.isEmpty(searchSale.getSuntil())) {
			until = DateUtil.getDate(searchSale.getSuntil());
		}
		PaymentEntity payment = searchSale.getPay();
		Date from = DateUtil.getDate(searchSale.getSfrom());
		int size = 0;
		size += putSales(map, from, until, payment);
		size += putAdjustments(map, from, until, payment);
		size += putOthersales(map, from, until, payment);
		size += putSalesPost(map, from, until, payment);
		map.put("numsales", size);
		return map;
	}

	private int putSales(Map<String, Object> map, Date from, Date until, PaymentEntity payment) {
		BigDecimal total = (BigDecimal) map.get(ConstantsViews.TOTAL);
		int size = 0;
		Iterable<SaleEntity> salesEntity = saleManager.searchByDatesAndPayment(from, until, payment);
		if (total == null) {
			total = BigDecimal.ZERO;
		}
		if (salesEntity != null) {
			Iterator<SaleEntity> isales = salesEntity.iterator();
			List<Sale> sales = new ArrayList<>();
			while (isales.hasNext()) {
				SaleEntity saleEntity = isales.next();
				Sale sale = converter.convertToDTO(saleEntity);
				sale.setJewels(mapperListJewels(saleEntity.getSjewels()));
				sales.add(sale);
				total = total.add(sale.getTotal());
			}
			size = sales.size();
			map.put(ConstantsViews.TOTAL, total);
			map.put(Constants.SALES, sales);
		}
		return size;
	}

	private int putAdjustments(Map<String, Object> map, Date from, Date until, PaymentEntity payment) {
		BigDecimal total = (BigDecimal) map.get(ConstantsViews.TOTAL);
		int size = 0;
		List<AdjustmentEntity> adjustments = adjustmentRepository.findByCreationdateBetweenAndPayment(from, until,
				payment);
		List<Adjustment> ladjustments = null;
		if (total == null) {
			total = BigDecimal.ZERO;
		}
		if (adjustments != null && !adjustments.isEmpty()) {
			Iterator<AdjustmentEntity> iadjustments = adjustments.iterator();
			AdjustmentEntity entity;
			ladjustments = new ArrayList<>();
			while (iadjustments.hasNext()) {
				entity = iadjustments.next();
				Adjustment adjustment = mapper.map(entity, Adjustment.class);
				ladjustments.add(adjustment);
				total = total.add(entity.getAmount());
			}
			size = ladjustments.size();
			map.put(ConstantsViews.TOTAL, total);
			map.put(Constants.ADJUSTMENTS, ladjustments);
		}
		return size;
	}

	private int putOthersales(Map<String, Object> map, Date from, Date until, PaymentEntity payment) {
		BigDecimal total = (BigDecimal) map.get(ConstantsViews.TOTAL);
		List<OtherSaleEntity> recordings = othersalerepository.findByCreationdateBetweenAndPay(from, until, payment);
		int size = 0;
		if (total == null) {
			total = BigDecimal.ZERO;
		}
		if (recordings != null && !recordings.isEmpty()) {
			Iterator<OtherSaleEntity> irecordings = recordings.iterator();
			while (irecordings.hasNext()) {
				total = total.add(irecordings.next().getAmount());
			}
			size = recordings.size();
			map.put(ConstantsViews.TOTAL, total);
			map.put(Constants.RECORDINGS, recordings);
		}
		return size;
	}

	private int putSalesPost(Map<String, Object> map, Date from, Date until, PaymentEntity payment) {
		List<SalePostponedEntity> salespost = salespostponedrepository.findByCreationdateBetweenPay(from, until,
				payment);
		BigDecimal total = (BigDecimal) map.get(ConstantsViews.TOTAL);
		int size = 0;
		if (total == null) {
			total = BigDecimal.ZERO;
		}
		if (salespost != null && !salespost.isEmpty()) {
			Iterator<SalePostponedEntity> isalespost = salespost.iterator();
			while (isalespost.hasNext()) {
				total = total.add(isalespost.next().getTotalamount());
			}
			size = salespost.size();
		}
		map.put(ConstantsViews.TOTAL, total);
		map.put("salespost", salespost);
		return size;
	}

	/**
	 * Mapper list jewels.
	 * 
	 * @param sjewels the sjewels
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
