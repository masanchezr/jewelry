package com.je.services.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.InstallmentEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SalePostPonedJewel;
import com.je.dbaccess.entities.SalePostponedEntity;
import com.je.dbaccess.repositories.InstallmentsRepository;
import com.je.dbaccess.repositories.SalesPostponedRepository;
import com.je.forms.SalePostPoned;
import com.je.utils.string.Util;

public class SalesPostPonedServiceImpl implements SalesPostPonedService {

	@Autowired
	private SalesPostponedRepository salespostponedrepository;

	@Autowired
	private InstallmentsRepository installmentsrepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	@Override
	public void buy(SalePostPoned sale) {
		// Tengo que crear un saleentity y varios salesjewels
		SalePostponedEntity saleEntity = new SalePostponedEntity();
		saleEntity.setIdsalepostponed(sale.getIdsale());
		List<SalePostPonedJewel> salesJewels = new ArrayList<>();
		List<InstallmentEntity> payments = new ArrayList<>();
		saleEntity.setCreationdate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 6);
		saleEntity.setDeadline(calendar.getTime());
		Iterator<JewelEntity> ijewels = sale.getJewels().iterator();
		BigDecimal importeTotal = BigDecimal.ZERO;
		while (ijewels.hasNext()) {
			// Deshabilito el objeto comprado
			JewelEntity jewel = ijewels.next();
			jewel.setActive(false);
			jewel.setSaledate(new Date());
			importeTotal = importeTotal.add(jewel.getPrice());
			SalePostPonedJewel e = new SalePostPonedJewel();
			e.setSalepostponed(saleEntity);
			e.setJewel(jewel);
			salesJewels.add(e);
		}
		saleEntity.setTotalamount(importeTotal);
		BigDecimal firstpayment = sale.getOptionalpayment();
		PaymentEntity payment = mapper.map(sale.getPayment(), PaymentEntity.class);
		InstallmentEntity sps = new InstallmentEntity();
		sps.setPay(payment);
		sps.setCreationdate(new Date());
		sps.setSalepostponed(saleEntity);
		sps.setAmount(firstpayment);
		payments.add(sps);
		saleEntity.setPlace(mapper.map(sale.getPlace(), PlaceEntity.class));
		saleEntity.setSjewels(salesJewels);
		saleEntity.setSpayments(payments);
		saleEntity.setTimeout(Boolean.FALSE);
		if (importeTotal.compareTo(firstpayment) == 0) {
			saleEntity.setDateretired(new Date());
		}
		salespostponedrepository.save(saleEntity);
		mapper.map(saleEntity, sale);
	}

	@Override
	public SalePostPoned addInstallment(Installment installment) {
		SalePostponedEntity sppentity = salespostponedrepository.findById(installment.getIdsalepostponed())
				.orElse(null);
		SalePostPoned sale = null;
		if (sppentity != null) {
			BigDecimal amount = installmentsrepository.sumBySalepostponed(sppentity);
			if (amount.add(Util.getNumber(installment.getAmount())).compareTo(sppentity.getTotalamount()) < 0) {
				InstallmentEntity entity = mapper.map(installment, InstallmentEntity.class);
				entity.setSalepostponed(sppentity);
				entity.setCreationdate(new Date());
				installmentsrepository.save(entity);
				sppentity = salespostponedrepository.findById(installment.getIdsalepostponed()).orElse(null);
				sale = mapper.map(sppentity, SalePostPoned.class);
			} else if (amount.add(Util.getNumber(installment.getAmount())).compareTo(sppentity.getTotalamount()) == 0) {
				InstallmentEntity entity = mapper.map(installment, InstallmentEntity.class);
				entity.setSalepostponed(sppentity);
				entity.setCreationdate(new Date());
				installmentsrepository.save(entity);
				sppentity.setDateretired(new Date());
				salespostponedrepository.save(sppentity);
				sppentity = salespostponedrepository.findById(installment.getIdsalepostponed()).orElse(null);
				sale = mapper.map(sppentity, SalePostPoned.class);
			}
		}
		return sale;
	}

	@Override
	public BigDecimal howmanyamount(SalePostponedEntity sppentity) {
		BigDecimal amount = installmentsrepository.sumBySalepostponed(sppentity);
		return sppentity.getTotalamount().subtract(amount);
	}

	@Override
	public SalePostponedEntity searchByNumsale(Long numsale) {
		return salespostponedrepository.findById(numsale).orElse(null);
	}

	@Override
	public SalePostPoned searchByPK(long id) {
		SalePostponedEntity saleEntity = salespostponedrepository.findById(id).orElse(null);
		if (saleEntity != null) {
			SalePostPoned sale = mapper.map(saleEntity, SalePostPoned.class);
			List<SalePostPonedJewel> sjewels = saleEntity.getSjewels();
			Iterator<SalePostPonedJewel> isjewels = sjewels.iterator();
			sale.setJewels(new ArrayList<JewelEntity>());
			while (isjewels.hasNext()) {
				sale.getJewels().add(isjewels.next().getJewel());
			}
			return sale;
		} else {
			return null;
		}
	}

	public SalePostPoned searchByIdAndPlace(long id, PlaceEntity place) {
		SalePostponedEntity saleEntity = salespostponedrepository.findById(id).orElse(null);
		if (saleEntity != null && saleEntity.getPlace().equals(place)) {
			SalePostPoned sale = mapper.map(saleEntity, SalePostPoned.class);
			List<SalePostPonedJewel> sjewels = saleEntity.getSjewels();
			Iterator<SalePostPonedJewel> isjewels = sjewels.iterator();
			sale.setJewels(new ArrayList<JewelEntity>());
			while (isjewels.hasNext()) {
				sale.getJewels().add(isjewels.next().getJewel());
			}
			return sale;
		} else {
			return null;
		}
	}

	@Override
	public long getMissing() {
		long number = 0;
		SalePostponedEntity last = salespostponedrepository.findFirstByOrderByIdsalepostponedDesc();
		for (long l = 201; l < last.getIdsalepostponed() && number == 0; l++) {
			if (!salespostponedrepository.findById(l).isPresent()) {
				number = l;
			}
		}
		return number;
	}

	public List<SalePostponedEntity> getListTimeout(PlaceEntity place) {
		return salespostponedrepository.findByDeadlineBeforeAndPlaceAndTimeoutFalseAndDateretiredIsNull(new Date(),
				place);
	}

	@Override
	public void timeout(long id) {
		SalePostponedEntity spe = salespostponedrepository.findById(id).orElse(null);
		if (spe != null) {
			spe.setTimeout(Boolean.TRUE);
			salespostponedrepository.save(spe);
		}

	}

	@Override
	public List<SalePostponedEntity> getListTimeout() {
		return salespostponedrepository.findByDeadlineBeforeAndTimeoutFalseAndDateretiredIsNull(new Date());
	}
}
