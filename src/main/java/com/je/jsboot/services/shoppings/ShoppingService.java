package com.je.jsboot.services.shoppings;

import java.io.File;
import java.util.List;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.ShoppingEntity;
import com.je.jsboot.services.dailies.Daily;

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

	public Long getNextNumber(String user);

	public void saveAdmin(Shopping shoppingForm);

	public List<ShoppingEntity> getByNIF(String nif);

	boolean isRepeatNumber(String num, String user, int year);

}
