package com.je.dbaccess.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.InstallmentEntity;
import com.je.dbaccess.entities.SalePostponedEntity;

public interface InstallmentsRepository extends CrudRepository<InstallmentEntity, Long> {

	@Query("select sum(i.amount) from InstallmentEntity i where s.salepostponed=:sppentity")
	public BigDecimal sumBySalepostponed(@Param("sppentity") SalePostponedEntity sppentity);

}
