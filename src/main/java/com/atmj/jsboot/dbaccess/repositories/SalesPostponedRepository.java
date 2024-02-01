package com.atmj.jsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.SalePostponedEntity;

import jakarta.persistence.TemporalType;

public interface SalesPostponedRepository extends CrudRepository<SalePostponedEntity, Long> {

	/**
	 * Ventas a plazos retiradas, como hay en ocasiones que se dan de baja después
	 * de retirar porque el cliente lo cambia por otra cosa, por eso hay que mirar
	 * el timeout para calcular el daily
	 * 
	 * @param date
	 * @param place
	 * @return ventas a plazos
	 */
	public List<SalePostponedEntity> findByDateretiredAndPlaceAndTimeoutFalse(@Temporal(TemporalType.DATE) Date date,
			PlaceEntity place);

	/**
	 * 
	 * @return número más alto
	 */
	public SalePostponedEntity findFirstByOrderByIdsalepostponedDesc();

	/**
	 * 
	 * @return número más bajo
	 */
	public SalePostponedEntity findFirstByOrderByIdsalepostponed();

	/**
	 * 
	 * @return lista ventas caducadas en tienda
	 */

	public List<SalePostponedEntity> findByDeadlineBeforeAndPlaceAndTimeoutFalseAndDateretiredIsNull(
			@Temporal(TemporalType.DATE) Date date, PlaceEntity place);

	public List<SalePostponedEntity> findByDateretiredBetweenAndPlace(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PlaceEntity place);

	@Query("select sum(s.totalamount) from SalePostponedEntity s where s.dateretired>=:from and s.dateretired<=:until and s.place=:place")
	public BigDecimal sumDateretiredBetweenAndPlace(@Param("from") Date from, @Param("until") Date until,
			@Param("place") PlaceEntity place);

	public List<SalePostponedEntity> findByDeadlineBeforeAndTimeoutFalseAndDateretiredIsNull(
			@Temporal(TemporalType.DATE) Date date);

}
