package com.atmj.jsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.jsboot.dbaccess.entities.InstallmentEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.SalePostponedEntity;

import jakarta.persistence.TemporalType;

public interface InstallmentsRepository extends CrudRepository<InstallmentEntity, Long> {

	@Query("select sum(i.amount) from InstallmentEntity i where i.salepostponed=:sppentity")
	public BigDecimal sumBySalepostponed(@Param("sppentity") SalePostponedEntity sppentity);

	public List<InstallmentEntity> findByCreationdateBetweenAndPay(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity pay);
}
