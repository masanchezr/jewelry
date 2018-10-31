package com.je.services.shoppings;

import java.io.File;
import java.util.List;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.dailies.Daily;

/**
 * The Interface ShoppingService.
 */
public interface ShoppingService {

	/**
	 * Save.
	 *
	 * @param shopping the shopping
	 * @return Daily
	 */
	public Daily save(Shopping shopping);

	/**
	 * Search shoppings.
	 *
	 * @param shopping the shopping
	 * @return the list
	 */
	public List<Shopping> searchShoppings(String sDateFrom, String sDateUntil, PlaceEntity place, Long numshop);

	/**
	 * Find shop by pk.
	 *
	 * @param idshop the idshop
	 * @return the shopping
	 */
	public Shopping findShopByPK(Long idshop);

	/**
	 * Update.
	 *
	 * @param shoppingForm the shopping form
	 */
	public void update(Shopping shoppingForm);

	public List<Long> searchGramsNull(String sDateFrom, String sDateUntil, PlaceEntity place);

	public List<QuarterMetal> searchGramsByMetal(String sDateFrom, String sDateUntil, PlaceEntity place);

	public Shopping searchClient(String refactorNIF);

	public File generateExcel(String datefrom, String dateuntil);

	public boolean isCorrectNumber(Shopping shoppingForm);

	public Long getNextNumber(String user);

	public void saveAdmin(Shopping shoppingForm);

}
