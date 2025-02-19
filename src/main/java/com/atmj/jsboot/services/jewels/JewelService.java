package com.atmj.jsboot.services.jewels;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Interface JewelService.
 */
public interface JewelService {

	/**
	 * Adds the object.
	 *
	 * @param thing the thing
	 * @return
	 */
	public JewelEntity addObject(JewelEntity thing);

	/**
	 * Search all.
	 *
	 * @return the list
	 */
	public Collection<JewelEntity> searchAll();

	/**
	 * Select product.
	 *
	 * @param idjewel the idjewel
	 * @return the jewel
	 */
	public JewelEntity selectProduct(Long idjewel);

	/**
	 * Search all active.
	 *
	 * @return the list
	 */
	public Page<JewelEntity> searchAllActive(int pageNumber);

	/**
	 * Search by reference.
	 *
	 * @param jewelForm the jewel form
	 * @return the list
	 */
	public List<JewelEntity> search(JewelEntity jewelForm);

	/**
	 * Update jewel.
	 *
	 * @param jewel the jewel
	 */
	public void updateJewelEntity(JewelEntity jewel);

	/**
	 * Search by reference category material place active.
	 *
	 * @param jewel the jewel
	 */
	public JewelEntity searchByReferenceCategoryMetalPlaceActive(JewelEntity jewel);

	/**
	 * Search by reference category material place.
	 *
	 * @param jewelForm the jewel form
	 * @return the jewel
	 */
	public JewelEntity searchByReferenceCategoryMetalPlace(JewelEntity jewelForm);

	public List<JewelEntity> searchByReferenceAndCategory(JewelEntity jewelForm);

	public List<JewelEntity> searchAllActiveByPlace(JewelEntity jewel);

	public Page<JewelEntity> searchPageableByPlace(int i, PlaceEntity place);

	public List<JewelEntity> searchByReference(JewelEntity jewel);

	public void revise(JewelEntity jewel);

	public Page<JewelEntity> searchActive(int i);

	public List<JewelEntity> searchJewels(List<JewelEntity> jewels, PlaceEntity place);

	public Page<JewelEntity> searchJewelsByCategory(CategoryEntity category, int i);

	public List<JewelEntity> searchByPrice(JewelEntity map);

	public Map<String, Object> searchByPlaceAndDates(PlaceEntity place, String sfrom, String suntil);

	public File generatePdf(PlaceEntity place, String datefrom, String dateuntil);

}
