package com.atmj.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.IPEntity;

public interface IpRepository extends CrudRepository<IPEntity, Long> {

	IPEntity findByIp(String ipAddress);

}
