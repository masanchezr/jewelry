package com.je.jsboot.services.sales;

import java.math.BigDecimal;
import java.util.List;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.SalePostponedEntity;
import com.je.jsboot.forms.SalePostPoned;

public interface SalesPostPonedService {

	public void buy(SalePostPoned sale);

	public SalePostponedEntity searchByNumsale(Long numsale);

	public SalePostPoned addInstallment(Installment installment);

	public BigDecimal howmanyamount(SalePostponedEntity sale);

	public SalePostPoned searchByPK(long id);

	public SalePostPoned searchByIdAndPlace(long id, PlaceEntity place);

	public List<SalePostponedEntity> getListTimeout(PlaceEntity place);

	public long getMissing();

	public void timeout(long id);

	public List<SalePostponedEntity> getListTimeout();

}
