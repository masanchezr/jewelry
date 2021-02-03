package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SaleEntity;

/**
 * The Interface SaleRepository.
 */
public interface SalesRepository extends CrudRepository<SaleEntity, Long> {

	/**
	 * Find by date.
	 * 
	 * @param date the date
	 * @return the iterable
	 */
	@Query("select s from SaleEntity s where s.creationdate>=?1")
	public Iterable<SaleEntity> findByDate(Date date);

	/**
	 * Find by client.
	 * 
	 * @param client the client
	 * @return the iterable
	 */
	public Iterable<SaleEntity> findByClient(ClientEntity client);

	/**
	 * Find by creationdate and place.
	 *
	 * @param creationdate the creationdate
	 * @param place        the place
	 * @return the list
	 */
	public List<SaleEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity place);

	/**
	 * Find by idsale and place.
	 *
	 * @param idsale the idsale
	 * @param place  the place
	 * @return the sale entity
	 */
	public SaleEntity findByNumsaleAndPlace(Long idsale, PlaceEntity place);

	public Iterable<SaleEntity> findByCreationdateBetweenAndPlaceOrderByIdsaleAsc(
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until, PlaceEntity place);

	@Query("select s from SaleEntity s join s.spayments p where s.creationdate>=:from and s.creationdate<=:until and p.pay=:pay")
	public Iterable<SaleEntity> findByCreationdateBetweenPay(@Param("from") Date from, @Param("until") Date until,
			@Param("pay") PaymentEntity pay);

	public List<SaleEntity> findByPlaceAndCreationdateBeforeAndNumsaleLessThan(PlaceEntity place,
			@Temporal(TemporalType.DATE) Date from, Long numsale);

	public List<SaleEntity> findByNumsale(Long numsale);

	public List<SaleEntity> findByNumsaleAndYear(Long num, int year);
}
