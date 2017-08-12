package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.PawnEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.RenovationEntity;

/**
 * The Interface RenovationsRepository.
 */
public interface RenovationsRepository extends CrudRepository<RenovationEntity, Long> {

	/**
	 * Find by creationdate and place.
	 *
	 * @param creationdate
	 *            the creationdate
	 * @param placeEntity
	 *            the place entity
	 * @return the list
	 */
	@Query("select o from RenovationEntity o where o.pawn.place = :place and o.creationdate=:creationdate")
	List<RenovationEntity> findByCreationdateAndPlace(
			@Param("creationdate") @Temporal(TemporalType.DATE) Date creationdate,
			@Param("place") PlaceEntity placeEntity);

	/**
	 * con esta query obtenemos los empeños caducados.
	 *
	 * @param creationdate
	 *            the creationdate
	 * @param placeEntity
	 *            the place entity
	 * @return the list
	 */
	@Query("select o from RenovationEntity o where o.pawn=:pawn and o.nextrenovationdate>:date")
	List<RenovationEntity> searchPawnsExpired(@Param("date") Date date, @Param("pawn") PawnEntity pawn);

	@Query("select o from RenovationEntity o where o.pawn.place = :place and o.creationdate>=:dfrom and o.creationdate<=:duntil")
	List<RenovationEntity> findByCreationdateBetweenAndPlace(@Param("dfrom") Date dfrom, @Param("duntil") Date duntil,
			@Param("place") PlaceEntity place);

}