package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.entities.PayrolltypeEntity;
import com.je.dbaccess.entities.UserEntity;

public interface PayrollRepository extends CrudRepository<PayrollEntity, Long> {

	public List<PayrollEntity> findByCreationdateAndUser(@Temporal(TemporalType.DATE) Date date, UserEntity user);

	public PayrollEntity findByYearAndMonthAndUserAndPayrolltype(Integer year, Integer month, UserEntity user,
			PayrolltypeEntity payrolltype);

}
