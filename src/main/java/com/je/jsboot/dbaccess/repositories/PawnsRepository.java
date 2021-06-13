package com.je.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.jsboot.dbaccess.entities.ClientPawnEntity;
import com.je.jsboot.dbaccess.entities.PawnEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.utils.constants.Constants;

/**
 * The Interface PawnsRepository.
 */
public interface PawnsRepository extends CrudRepository<PawnEntity, Long> {

	/**
	 * Find by creationdate and place.
	 *
	 * @param creationdate the creationdate
	 * @param placeEntity  the place entity
	 * @return the list
	 */
	public List<PawnEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	/**
	 * Find by numpawn and place.
	 *
	 * @param numpawn     the numpawn
	 * @param placeEntity the place entity
	 * @return the list
	 */
	public List<PawnEntity> findByNumpawnAndPlace(String numpawn, PlaceEntity placeEntity);

	@Query("select sum(p.amount) from PawnEntity p join p.objects o where p.place=:place and p.dateretired is null and o.realgrams is null")
	public Double sumPawnsActive(@Param(Constants.PLACE) PlaceEntity placeEntity);

	/**
	 * Find by numpawn and place and retired.
	 *
	 * @param numpawn     the numpawn
	 * @param placeEntity the place entity
	 * @param retired     the retired
	 * @return the list
	 */
	@Query("select distinct p from PawnEntity p join p.objects o where p.numpawn=:numpawn and p.place=:place and p.dateretired is null and o.realgrams is null")
	public List<PawnEntity> findByNumpawnAndPlaceAndRetired(@Param(Constants.NUMPAWN) String numpawn,
			@Param(Constants.PLACE) PlaceEntity placeEntity);

	public List<PawnEntity> findByDateretiredAndPlace(@Temporal(TemporalType.DATE) Date date, PlaceEntity placeEntity);

	public List<PawnEntity> findByNumpawnAndPlaceAndYear(String string, PlaceEntity place, int year);

	public List<PawnEntity> findByDateretiredBetweenAndPlace(Date dfrom, Date duntil, PlaceEntity place);

	@Query("select distinct p from PawnEntity p join p.objects o where p.place=:place and p.dateretired is null and o.realgrams is null")
	public List<PawnEntity> searchByPlaceAndNotRetired(@Param(Constants.PLACE) PlaceEntity placeEntity);

	@Query("select p from PawnEntity p where p.idpawn=:idpawn and PERIOD_DIFF(DATE_FORMAT(p.dateretired,'%Y%m'),DATE_FORMAT(p.creationdate,'%Y%m'))>(SELECT COUNT(*) FROM RenovationEntity where idpawn=:idpawn)+1")
	public List<PawnEntity> searchMissingRenovations(@Param(Constants.IDPAWN) Long idpawn);

	@Query("select p from PawnEntity p where p.idpawn=:idpawn and PERIOD_DIFF(DATE_FORMAT(p.dateretired,'%Y%m'),DATE_FORMAT(p.creationdate,'%Y%m'))>p.months")
	public List<PawnEntity> searchMissingMonths(@Param(Constants.IDPAWN) Long idpawn);

	/**
	 * 
	 * @param datefrom
	 * @param dateuntil
	 * @return Suma de gramos reales, gramos brutos e importe de los empeños nuevos
	 *         dado un rango de fechas
	 */
	@Query("select sum(os.realgrams), sum(os.grossgrams), sum(s.amount), os.metal from PawnEntity s join s.objects os where s.creationdate>=:dateFrom and s.creationdate<=:dateUntil and s.idreturnpawn is null and os.pawn.idpawn=s.idpawn GROUP BY os.metal")
	public List<Object[]> findGrossGramsByMetal(@Param("dateFrom") @Temporal(TemporalType.DATE) Date datefrom,
			@Param("dateUntil") @Temporal(TemporalType.DATE) Date dateuntil);

	public List<PawnEntity> findByClient(ClientPawnEntity client);

	public List<PawnEntity> findByClientAndPlaceAndDateretiredIsNotNullAndReturnpawnFalse(ClientPawnEntity client,
			PlaceEntity placeEntity);
}
