package com.atmj.jsboot.services.sales;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atmj.jsboot.admin.forms.SearchMissingNumbers;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.forms.Sale;

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

	public Map<String, Object> searchByDatesAndPlace(String sDateFrom, String sDateUntil, PlaceEntity place);

	public Sale searchByPK(Long idsale);

	public List<Long> calculateMissingSales(SearchMissingNumbers form);

	public boolean exists(Long numsale);

	public Sale searchByNumsaleAndYear(Long numsale, int year);
}
