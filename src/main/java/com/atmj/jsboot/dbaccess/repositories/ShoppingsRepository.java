package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.jsboot.dbaccess.entities.ClientPawnEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.ShoppingEntity;
import com.atmj.jsboot.utils.constants.Constants;

/**
 * The Interface ShoppingsRepository.
 */
public interface ShoppingsRepository extends CrudRepository<ShoppingEntity, Long> {

	/**
	 * Find by creationdate and place.
	 *
	 * @param creationdate the creationdate
	 * @param place        the place
	 * @return the list
	 */
	public List<ShoppingEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity place);

	/**
	 * Find by numshop and place.
	 *
	 * @param numshop the numshop
	 * @param place   the place
	 * @return the list
	 */
	public List<ShoppingEntity> findByNumshopAndPlace(Long numshop, PlaceEntity place);

	/**
	 * Find by numshop and creationdate and place.
	 *
	 * @param numshop      the numshop
	 * @param creationdate the creationdate
	 * @param place        the place
	 * @return the list
	 */
	public List<ShoppingEntity> findByNumshopAndCreationdateAndPlace(Long numshop,
			@Temporal(TemporalType.DATE) Date creationdate, PlaceEntity place);

	/**
	 * Find by creationdate between and place.
	 *
	 * @param datefrom  the datefrom
	 * @param dateuntil the dateuntil
	 * @param place     the place
	 * @return the list
	 */
	public List<ShoppingEntity> findByCreationdateBetweenAndPlace(@Temporal(TemporalType.DATE) Date datefrom,
			@Temporal(TemporalType.DATE) Date dateuntil, PlaceEntity place);

	public List<ShoppingEntity> findByNumshopLessThanAndCreationdateBetweenAndPlace(Long numshop,
			@Temporal(TemporalType.DATE) Date datefrom, @Temporal(TemporalType.DATE) Date dateuntil, PlaceEntity place);

	public List<ShoppingEntity> findByNumshopAndCreationdateBetweenAndPlace(Long numshop,
			@Temporal(TemporalType.DATE) Date datefrom, @Temporal(TemporalType.DATE) Date dateuntil, PlaceEntity place);

	public List<ShoppingEntity> findByNumshopAndPlaceAndCreationdateBetween(Long numshop, PlaceEntity place,
			@Temporal(TemporalType.DATE) Date datefrom, @Temporal(TemporalType.DATE) Date dateuntil);

	public ShoppingEntity findByNumshopAndPlaceAndYear(Long numshop, PlaceEntity place, Integer year);

	@Query("select s.numshop from ShoppingEntity s, ObjectShopEntity os where s.place=:place and s.creationdate>=:dateFrom and s.creationdate<=:dateUntil and os.shop.idshop=s.idshop and os.realgrams is null")
	public List<Long> findGramsNull(@Param(Constants.PLACE) PlaceEntity place,
			@Param("dateFrom") @Temporal(TemporalType.DATE) Date datefrom,
			@Param("dateUntil") @Temporal(TemporalType.DATE) Date dateuntil);

	public ShoppingEntity findFirstByPlaceOrderByIdshopDesc(PlaceEntity placeEntity);

	public List<ShoppingEntity> findByPlaceAndYearOrderByNumshopDesc(PlaceEntity placeEntity, int i);

	@Query("select sum(os.realgrams), sum(os.grossgrams), sum(os.netgrams), sum(os.amount), os.metal from ShoppingEntity s, ObjectShopEntity os where s.place=:place and s.creationdate>=:dateFrom and s.creationdate<=:dateUntil and os.shop.idshop=s.idshop GROUP BY os.metal")
	public List<Object[]> findGramsByMetal(@Param("dateFrom") @Temporal(TemporalType.DATE) Date datefrom,
			@Param("dateUntil") @Temporal(TemporalType.DATE) Date dateuntil, @Param(Constants.PLACE) PlaceEntity place);

	public List<ShoppingEntity> findByClient(ClientPawnEntity client);
}
