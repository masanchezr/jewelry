package com.atmj.jsboot.services.othersale;

import com.atmj.jsboot.dbaccess.entities.OtherSaleEntity;
import com.atmj.jsboot.dbaccess.entities.TypeOfSaleEntity;

public interface OtherSaleService {

	public void save(OtherSaleEntity recording);

	public Iterable<TypeOfSaleEntity> findAllTypes();

}
