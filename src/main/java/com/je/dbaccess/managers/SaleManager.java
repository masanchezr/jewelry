package com.je.dbaccess.managers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.je.dbaccess.entities.Addresses;
import com.je.dbaccess.entities.CancelSaleEntity;
import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.entities.DiscountEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.entities.SalesJewels;

/**
 * The Interface SaleManager.
 */
public interface SaleManager {

	/**
	 * Buy.
	 * 
	 * @param salesJewels the sales jewels
	 * @return the long
	 */
	public Long buy(SaleEntity salesJewels);

	/**
	 * Search all sales.
	 * 
	 * @return the iterable
	 */
	public Iterable<SaleEntity> searchAllSales();

	/**
	 * Search by date.
	 * 
	 * @param date the date
	 * @return the iterable
	 */
	public Iterable<SaleEntity> searchByDate(Date date);

	/**
	 * Search address by client.
	 * 
	 * @param client the client
	 * @return the addresses
	 */
	public Addresses searchAddressByClient(ClientEntity client);

	/**
	 * Search by creation date and place.
	 *
	 * @param date        the date
	 * @param placeEntity the place entity
	 * @return the list
	 */
	public List<SaleEntity> searchByCreationDateAndPlace(Date date, PlaceEntity placeEntity);

	/**
	 * Cancel sale.
	 *
	 * @param cancel   the sale
	 * @param payment
	 * @param discount
	 * @return true, if successful
	 */
	public boolean cancelSale(CancelSaleEntity cancel, List<SalesJewels> salesjewels, DiscountEntity discount);

	public boolean cancelParcialSale(CancelSaleEntity cancel, List<Long> jewelsToCancel, DiscountEntity discount);

	public boolean existSale(Long numsale);

	public Iterable<SaleEntity> searchByDatesAndPayment(Date from, Date until, PaymentEntity pay);

	public Map<String, Object> searchByDatesAndPlace(Date from, Date until, PlaceEntity place);

	public List<Long> calculateNumberMissing(Long numFrom, Long numUntil);

	public SaleEntity searchByPK(Long idsale);

	public SaleEntity searchByNumsaleAndPlace(Long numsale, Long idplace);
}
