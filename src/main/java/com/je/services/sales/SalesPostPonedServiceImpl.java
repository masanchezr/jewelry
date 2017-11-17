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
		List<SalePostPonedJewel> salesJewels = new ArrayList<SalePostPonedJewel>();
		List<InstallmentEntity> payments = new ArrayList<InstallmentEntity>();
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
		SalePostponedEntity sppentity = salespostponedrepository.findOne(installment.getIdsalepostponed());
		SalePostPoned sale = null;
		if (sppentity != null) {
			InstallmentEntity entity = mapper.map(installment, InstallmentEntity.class);
			entity.setSalepostponed(sppentity);
			entity.setCreationdate(new Date());
			installmentsrepository.save(entity);
			BigDecimal amount = installmentsrepository.sumBySalepostponed(sppentity);
			if (amount.compareTo(sppentity.getTotalamount()) == 0) {
				sppentity.setDateretired(new Date());
				salespostponedrepository.save(sppentity);
			}
			sppentity = salespostponedrepository.findOne(installment.getIdsalepostponed());
			sale = mapper.map(sppentity, SalePostPoned.class);
		}
		return sale;
	}

	public BigDecimal howmanyamount(SalePostponedEntity sppentity) {
		BigDecimal amount = installmentsrepository.sumBySalepostponed(sppentity);
		return sppentity.getTotalamount().subtract(amount);
	}

	@Override
	public SalePostponedEntity searchByNumsale(Long numsale) {
		return salespostponedrepository.findOne(numsale);
	}
}
