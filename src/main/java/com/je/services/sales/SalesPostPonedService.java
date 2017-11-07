package com.je.services.sales;

import com.je.dbaccess.entities.SalePostponedEntity;

public interface SalesPostPonedService {

	public void buy(SalePostPoned saleForm);

	public SalePostponedEntity searchByNumsale(Long numsale);

	public SalePostPoned addInstallment(Installment installment);

}
