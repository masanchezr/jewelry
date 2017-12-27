package com.je.services.sales;

import java.math.BigDecimal;

import com.je.dbaccess.entities.SalePostponedEntity;

public interface SalesPostPonedService {

	public void buy(SalePostPoned saleForm);

	public SalePostponedEntity searchByNumsale(Long numsale);

	public SalePostPoned addInstallment(Installment installment);

	public BigDecimal howmanyamount(SalePostponedEntity sale);

	public SalePostPoned searchByPK(long id);

}
