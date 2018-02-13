package com.je.dbaccess.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Interface JewelRepository.
 */
public interface JewelRepository extends PagingAndSortingRepository<JewelEntity, Long> {

	/**
	 * Find by name actives.
	 *
	 * @param searchName
	 *            the search name
	 * @return the iterable
	 */
	@Query("select o from JewelEntity o where (o.name = :searchName or o.description = :searchName) and o.active=true and o.img is not null")
	public Page<JewelEntity> findByNameActivesWithImg(@Param("searchName") String searchName, Pageable page);

	/**
	 * Find by category actives.
	 *
	 * @param category
	 *            the category
	 * @return the iterable
	 */
	@Query("select o from JewelEntity o where o.category = :category and o.img is not null")
	public Page<JewelEntity> findByCategoryActivesWithImg(@Param(ConstantsJsp.CATEGORY) CategoryEntity category,
			Pageable page);

	/**
	 * Find by name and category actives.
	 *
	 * @param searchName
	 *            the search name
	 * @param category
	 *            the category
	 * @return the iterable
	 */
	@Query("select o from JewelEntity o where (o.category = :category or o.name = :searchName or o.description = :searchName) and o.active=true and o.img is not null")
	public Page<JewelEntity> findByNameAndCategoryActivesWithImg(@Param("searchName") String searchName,
			@Param(ConstantsJsp.CATEGORY) CategoryEntity category, Pageable page);

	/**
	 * Find by name and category.
	 *
	 * @param searchName
	 *            the search name
	 * @return the iterable
	 */
	@Query("select distinct o from JewelEntity o where o.category.namecategory like :searchName or o.name like :searchName or o.description like :searchName")
	public Iterable<JewelEntity> findByNameAndCategory(@Param("searchName") String searchName);

	/**
	 * Search all active.
	 *
	 * @return the iterable
	 */
	@Query("select j from JewelEntity j where j.active=true and j.category.active=true")
	public Iterable<JewelEntity> searchAllActive();

	@Query("select j from JewelEntity j where j.active=true and j.category.active=true")
	public Page<JewelEntity> searchAllActivePageable(Pageable pageable);

	/**
	 * Find by reference.
	 *
	 * @param reference
	 *            the reference
	 * @param category
	 *            the category
	 * @return the list
	 */
	public List<JewelEntity> findByReferenceAndCategory(String reference, CategoryEntity category);

	/**
	 * Find by reference and category and material and place and active.
	 *
	 * @param reference
	 *            the reference
	 * @param category
	 *            the category
	 * @param material
	 *            the material
	 * @param place
	 *            the place
	 * @param active
	 *            the active
	 * @return the list
	 */
	public List<JewelEntity> findByReferenceAndCategoryAndMetalAndPlaceAndActive(String reference,
			CategoryEntity category, MetalEntity material, PlaceEntity place, Boolean active);

	/**
	 * Find by constraint unique.
	 *
	 * @param reference
	 *            the reference
	 * @param category
	 *            the category
	 * @param material
	 *            the material
	 * @param place
	 *            the place
	 * @return JewelEntity
	 */
	public List<JewelEntity> findByReferenceAndCategoryAndMetalAndPlace(String reference, CategoryEntity category,
			MetalEntity material, PlaceEntity place);

	public List<JewelEntity> findByPlace(PlaceEntity place);

	public List<JewelEntity> findByActiveTrue();

	public List<JewelEntity> findByReference(String reference);

	public List<JewelEntity> findByCategory(CategoryEntity category);

	public List<JewelEntity> findByPlaceAndMetal(PlaceEntity place, MetalEntity material);

	public List<JewelEntity> findByReferenceAndPlaceAndMetal(String reference, PlaceEntity place, MetalEntity material);

	public List<JewelEntity> findByCategoryAndPlaceAndMetal(CategoryEntity category, PlaceEntity place,
			MetalEntity material);

	public List<JewelEntity> findByPlaceAndActiveTrueOrderByReference(PlaceEntity place);

	public Page<JewelEntity> findByPlaceAndActiveTrue(PlaceEntity place, Pageable pageable);

	public Page<JewelEntity> findByImgIsNotNull(Pageable pageable);

	public Page<JewelEntity> findByImgIsNotNullAndActiveTrue(Pageable pageable);

	public Iterable<JewelEntity> findByCategoryAndActiveTrueAndImgNotNull(CategoryEntity category);

	public List<JewelEntity> findByMetalAndGramsIsNull(MetalEntity material);

}
