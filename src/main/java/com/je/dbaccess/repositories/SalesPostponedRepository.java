package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SalePostponedEntity;

public interface SalesPostponedRepository extends CrudRepository<SalePostponedEntity, Long> {

	public List<SalePostponedEntity> findByDateretiredAndPlace(@Temporal(TemporalType.DATE) Date date,
			PlaceEntity place);

	@Query("select s from SalePostponedEntity s join s.spayments p where p.creationdate>=:from and p.creationdate<=:until and p.pay=:pay")
	public List<SalePostponedEntity> findByCreationdateBetweenPay(@Param("from") Date from, @Param("until") Date until,
			@Param("pay") PaymentEntity pay);

}
