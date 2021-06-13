package com.je.jsboot.services.othersale;

import com.je.jsboot.dbaccess.entities.OtherSaleEntity;
import com.je.jsboot.dbaccess.entities.TypeOfSaleEntity;

public interface OtherSaleService {

	public void save(OtherSaleEntity recording);

	public Iterable<TypeOfSaleEntity> findAllTypes();

}
