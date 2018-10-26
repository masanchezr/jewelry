package com.je.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.IPEntity;

public interface IpRepository extends CrudRepository<IPEntity, Long> {

	IPEntity findByIp(String ipAddress);

}
