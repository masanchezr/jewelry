package com.je.services.sales;

import com.je.dbaccess.entities.SalePostponedEntity;

public interface SalesPostPonedService {

	public void buy(Sale saleForm);

	public SalePostponedEntity searchByNumsale(Long numsale);

}
