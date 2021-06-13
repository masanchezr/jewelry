package com.je.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.EntryMoneyEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;

public interface EntryMoneyRepository extends CrudRepository<EntryMoneyEntity, Long> {

	public List<EntryMoneyEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	public List<EntryMoneyEntity> findByCreationdateBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);
}
