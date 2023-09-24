package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.jsboot.dbaccess.entities.PawnEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.RenovationEntity;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;

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
			@Param(Constants.CREATIONDATE) @Temporal(TemporalType.DATE) Date creationdate,
			@Param(Constants.PLACE) PlaceEntity placeEntity);

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
	List<RenovationEntity> searchPawnsExpired(@Param(ConstantsViews.DATE) Date date, @Param("pawn") PawnEntity pawn);

	@Query("select o from RenovationEntity o where o.pawn.place = :place and o.creationdate>=:dfrom and o.creationdate<=:duntil")
	List<RenovationEntity> findByCreationdateBetweenAndPlace(@Param("dfrom") Date dfrom, @Param("duntil") Date duntil,
			@Param(Constants.PLACE) PlaceEntity place);

}
