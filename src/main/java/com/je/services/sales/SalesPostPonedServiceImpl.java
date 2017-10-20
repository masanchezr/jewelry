package com.je.services.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	public void buy(Sale sale) {
		// Tengo que crear un saleentity y varios salesjewels
		SalePostponedEntity saleEntity = new SalePostponedEntity();
		saleEntity.setIdsalepostponed(sale.getNumsale());
		List<SalePostPonedJewel> salesJewels = new ArrayList<SalePostPonedJewel>();
		List<InstallmentEntity> payments = new ArrayList<InstallmentEntity>();
		saleEntity.setCreationdate(new Date());
		Iterator<JewelEntity> ijewels = sale.getJewels().iterator();
		BigDecimal importeTotal = BigDecimal.ZERO;
		while (ijewels.hasNext()) {
			// Deshabilito el objeto comprado
			JewelEntity jewel = ijewels.next();
			jewel.setActive(false);
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
		salespostponedrepository.save(saleEntity);
		mapper.map(saleEntity, sale);
	}

	public SalePostponedEntity addInstallment(Installment installment) {
		InstallmentEntity entity = mapper.map(installment, InstallmentEntity.class);
		SalePostponedEntity sppentity = salespostponedrepository.findOne(installment.getIdsalepostponed());
		entity.setSalepostponed(sppentity);
		installmentsrepository.save(entity);
		BigDecimal amount = installmentsrepository.sumBySalepostponed(sppentity);
		if (amount.equals(sppentity.getTotalamount())) {
			sppentity.setDateretired(new Date());
		}
		return sppentity;
	}

	@Override
	public SalePostponedEntity searchByNumsale(Long numsale) {
		return salespostponedrepository.findOne(numsale);
	}
}
