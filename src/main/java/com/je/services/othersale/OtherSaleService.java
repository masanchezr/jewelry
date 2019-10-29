package com.je.services.othersale;

import com.je.dbaccess.entities.OtherSaleEntity;
import com.je.dbaccess.entities.TypeOfSaleEntity;

public interface OtherSaleService {

	public void save(OtherSaleEntity recording);

	public Iterable<TypeOfSaleEntity> findAllTypes();

}
