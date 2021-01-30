package com.je.dbaccess.managers;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Interface ObjectsManager.
 */
public interface JewelsManager {

	/**
	 * Save.
	 *
	 * @param object the object
	 * @return
	 */
	public JewelEntity save(JewelEntity object);

	/**
	 * Search all.
	 *
	 * @return the iterable
	 */
	public Collection<JewelEntity> searchAll();

	/**
	 * Search by name desc category.
	 *
	 * @param searchName  the search name
	 * @param pageRequest
	 * @param pageRequest
	 * @return the list
	 */
	public Page<JewelEntity> searchByNameDescCategory(String searchName, PageRequest pageRequest);

	/**
	 * Search by id.
	 *
	 * @param idjewel the idjewel
	 * @return the jewel entity
	 */
	public JewelEntity searchById(Long idjewel);

	/**
	 * Search all active.
	 *
	 * @return the list
	 */
	public List<JewelEntity> searchAllActive();

	/**
	 * Search by category active.
	 *
	 * @param category the keywordcategory
	 * @return the list
	 */
	public Page<JewelEntity> searchByCategoryActive(CategoryEntity category, Pageable page);

	/**
	 * Search by reference.
	 *
	 * @param jewel the jewel
	 * @return the iterator
	 */
	public List<JewelEntity> searchByReferenceAndCategory(JewelEntity jewel);

	/**
	 * Search by reference category material place active.
	 *
	 * @param jewel the jewel
	 * @return the jewel entity
	 */
	public JewelEntity searchByReferenceCategoryMetalPlaceActive(JewelEntity jewel);

	/**
	 * Search by reference category material place.
	 *
	 * @param jewelEntity the jewel entity
	 * @return the jewel entity
	 */
	public JewelEntity searchByReferenceCategoryMetalPlace(JewelEntity jewelEntity);

	public Page<JewelEntity> searchAllActivePageable(Pageable p);

	public List<JewelEntity> searchByReference(String reference);

	public List<JewelEntity> searchByReferenceAndPlaceAndMetal(String reference, PlaceEntity map, MetalEntity map2);

	public List<JewelEntity> searchByPlaceAndMetal(PlaceEntity map, MetalEntity map2);

	public List<JewelEntity> searchByPlaceAndMetalAndCategory(PlaceEntity map, MetalEntity map2, CategoryEntity map3);

	public List<JewelEntity> searchAllActiveByPlace(JewelEntity map);

	public Page<JewelEntity> findByPlaceAndActiveTrue(PlaceEntity place, Pageable pageable);

	public JewelEntity findById(Long idjewel);

	public Page<JewelEntity> searchActive(Pageable pageable);

	public void setGrams();

	public List<JewelEntity> searchByPrice(BigDecimal price);

	public List<JewelEntity> searchByPriceAndReference(BigDecimal price, String reference);

}
