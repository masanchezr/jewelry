package com.je.services.sales;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.je.admin.forms.SearchMissingNumbers;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.forms.Sale;

/**
 * The Interface SaleService.
 */
public interface SaleService {

	/**
	 * Buy.
	 *
	 * @param sale the sale
	 * @return the long
	 */
	public Long buy(Sale sale);

	/**
	 * Search all sales.
	 *
	 * @return the list
	 */
	public List<Sale> searchAllSales();

	/**
	 * Search by date.
	 *
	 * @param date the date
	 * @return the list
	 */
	public List<Sale> searchByDate(Date date);

	/**
	 * Search address by client.
	 *
	 * @param nif the nif
	 * @return the addresses
	 */
	public Addresses searchAddressByClient(String nif);

	/**
	 * Removes the sale.
	 *
	 * @param removeSaleForm the remove sale form
	 */
	public void removeSale(Sale removeSaleForm);

	public boolean removeSaleParcial(Sale removeSaleForm);

	/**
	 * Search by id and place.
	 *
	 * @param idsale  the idsale
	 * @param valueOf the value of
	 * @return the sale
	 */
	public Sale searchByNumsaleAndPlace(Long numsale, Long valueOf);

	public Map<String, Object> searchByDatesAndPlace(String sDateFrom, String sDateUntil, PlaceEntity place);

	public Sale searchByPK(Long idsale);

	public List<Long> calculateMissingSales(SearchMissingNumbers form);

	public boolean exists(Long numsale, Long idplace);
}
