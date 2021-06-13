package com.je.jsboot.dbaccess.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.jsboot.dbaccess.entities.InstallmentEntity;
import com.je.jsboot.dbaccess.entities.SalePostponedEntity;

public interface InstallmentsRepository extends CrudRepository<InstallmentEntity, Long> {

	@Query("select sum(i.amount) from InstallmentEntity i where i.salepostponed=:sppentity")
	public BigDecimal sumBySalepostponed(@Param("sppentity") SalePostponedEntity sppentity);

}
