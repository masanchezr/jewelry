package com.je.dbaccess.managers;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
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
	 * @param object
	 *            the object
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
	 * Search by name desc category actives.
	 *
	 * @param searchName
	 *            the search name
	 * @param category
	 *            the category
	 * @return the list
	 */
	public Page<JewelEntity> searchByNameDescCategoryActives(String searchName, CategoryEntity category,
			Pageable pageable);

	/**
	 * Search by name desc category.
	 *
	 * @param searchName
	 *            the search name
	 * @return the list
	 */
	public List<JewelEntity> searchByNameDescCategory(String searchName);

	/**
	 * Search by id.
	 *
	 * @param idjewel
	 *            the idjewel
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
	 * @param keywordcategory
	 *            the keywordcategory
	 * @return the list
	 */
	public List<JewelEntity> searchByCategoryActive(String keywordcategory);

	/**
	 * Search by reference.
	 *
	 * @param jewel
	 *            the jewel
	 * @return the iterator
	 */
	public List<JewelEntity> searchByReferenceAndCategory(JewelEntity jewel);

	/**
	 * Search by reference category metal place active.
	 *
	 * @param jewel
	 *            the jewel
	 * @return the jewel entity
	 */
	public JewelEntity searchByReferenceCategoryMetalPlaceActive(JewelEntity jewel);

	/**
	 * Search by reference category metal place.
	 *
	 * @param jewelEntity
	 *            the jewel entity
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

	public JewelEntity findOne(Long idjewel);

	public Page<JewelEntity> searchWithImg(Pageable pageable);

	public void setGrams();

}