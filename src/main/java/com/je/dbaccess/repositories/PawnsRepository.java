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

/**
 * The Interface PawnsRepository.
 */
public interface PawnsRepository extends CrudRepository<PawnEntity, Long> {

	/**
	 * Find by creationdate and place.
	 *
	 * @param creationdate
	 *            the creationdate
	 * @param placeEntity
	 *            the place entity
	 * @return the list
	 */
	public List<PawnEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	public List<PawnEntity> findByCreationdateBetweenAndPlace(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PlaceEntity placeEntity);

	/**
	 * Find by numpawn and place.
	 *
	 * @param numpawn
	 *            the numpawn
	 * @param placeEntity
	 *            the place entity
	 * @return the list
	 */
	public List<PawnEntity> findByNumpawnAndPlace(String numpawn, PlaceEntity placeEntity);

	/**
	 * Find by numpawn and place and creationdate.
	 *
	 * @param numpawn
	 *            the numpawn
	 * @param placeEntity
	 *            the place entity
	 * @param creationdate
	 *            the creationdate
	 * @return the list
	 */
	public List<PawnEntity> findByNumpawnAndPlaceAndCreationdate(String numpawn, PlaceEntity placeEntity,
			Date creationdate);

	@Query("select sum(p.amount) from PawnEntity p join p.objects o where p.place=:place and p.dateretired=null and o.realgrams is null")
	public Double sumPawnsActive(@Param("place") PlaceEntity placeEntity);

	/**
	 * Find by numpawn and place and retired.
	 *
	 * @param numpawn
	 *            the numpawn
	 * @param placeEntity
	 *            the place entity
	 * @param retired
	 *            the retired
	 * @return the list
	 */
	@Query("select distinct p from PawnEntity p join p.objects o where p.numpawn=:numpawn and p.place=:place and p.dateretired is null and o.realgrams is null")
	public List<PawnEntity> findByNumpawnAndPlaceAndRetired(@Param("numpawn") String numpawn,
			@Param("place") PlaceEntity placeEntity);

	public List<PawnEntity> findByDateretiredAndPlace(@Temporal(TemporalType.DATE) Date date, PlaceEntity placeEntity);

	public PawnEntity findByNumpawnAndPlaceAndYear(String string, PlaceEntity place, int year);

	public List<PawnEntity> findByDateretiredBetweenAndPlace(Date dfrom, Date duntil, PlaceEntity place);

	@Query("select distinct p from PawnEntity p join p.objects o where p.place=:place and p.dateretired is null and o.realgrams is null")
	public List<PawnEntity> searchByPlaceAndNotRetired(@Param("place") PlaceEntity placeEntity);

	@Query("select p from PawnEntity p where p.idpawn=:idpawn and PERIOD_DIFF(DATE_FORMAT(p.dateretired,'%Y%m'),DATE_FORMAT(p.creationdate,'%Y%m'))>(SELECT COUNT(*) FROM RenovationEntity where idpawn=:idpawn)+1")
	public List<PawnEntity> searchMissingRenovations(@Param("idpawn") Long idpawn);

	@Query("select p from PawnEntity p where p.idpawn=:idpawn and PERIOD_DIFF(DATE_FORMAT(p.dateretired,'%Y%m'),DATE_FORMAT(p.creationdate,'%Y%m'))>p.months")
	public List<PawnEntity> searchMissingMonths(@Param("idpawn") Long idpawn);

	public List<PawnEntity> findByPlaceAndYearOrderByCreationdateDesc(PlaceEntity placeEntity, int i);
}
