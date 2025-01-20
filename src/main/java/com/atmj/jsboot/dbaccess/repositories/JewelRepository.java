package com.atmj.jsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.utils.constants.Constants;

import jakarta.persistence.TemporalType;

/**
 * The Interface JewelRepository.
 */
public interface JewelRepository
		extends PagingAndSortingRepository<JewelEntity, Long>, ListCrudRepository<JewelEntity, Long> {

	/**
	 * Find by name and category.
	 *
	 * @param searchName the search name
	 * @param page
	 * @return the iterable
	 */
	@Query("select distinct o from JewelEntity o where o.active=true and o.category.active=true and o.category.namecategory like :searchName or o.name like :searchName or o.description like :searchName")
	public Page<JewelEntity> findByNameAndCategory(@Param("searchName") String searchName, Pageable page);

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
	 * @param reference the reference
	 * @param category  the category
	 * @return the list
	 */
	public List<JewelEntity> findByReferenceAndCategory(String reference, CategoryEntity category);

	/**
	 * Find by reference and category and material and place and active.
	 *
	 * @param reference the reference
	 * @param category  the category
	 * @param material  the material
	 * @param place     the place
	 * @param active    the active
	 * @return the list
	 */
	public List<JewelEntity> findByReferenceAndCategoryAndMetalAndPlaceAndActive(String reference,
			CategoryEntity category, MetalEntity material, PlaceEntity place, Boolean active);

	/**
	 * Find by constraint unique.
	 *
	 * @param reference the reference
	 * @param category  the category
	 * @param material  the material
	 * @param place     the place
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

	public Page<JewelEntity> findByActiveTrue(Pageable pageable);

	public Page<JewelEntity> findByCategoryAndActiveTrue(CategoryEntity category, Pageable pageable);

	public List<JewelEntity> findByMetalAndGramsIsNull(MetalEntity material);

	public List<JewelEntity> findByPrice(BigDecimal price);

	public List<JewelEntity> findByPriceAndReference(BigDecimal price, String reference);

	public List<JewelEntity> findByPlaceAndCreationdate(PlaceEntity place, Date creationdate);

	public List<JewelEntity> findByPlaceAndCreationdateBetween(PlaceEntity place, Date from, Date until);

	@Query("select sum(a.grams) from JewelEntity a where a.creationdate=:from and a.place=:place")
	public BigDecimal sumGramsByPlaceAndCreationdate(@Param(Constants.PLACE) PlaceEntity place,
			@Param("from") @Temporal(TemporalType.DATE) Date creationdate);

	@Query("select sum(a.grams) from JewelEntity a where a.creationdate>=:from and a.creationdate<=:until and a.place=:place")
	public BigDecimal sumGramsByPlaceAndCreationdateBetween(@Param(Constants.PLACE) PlaceEntity place,
			@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until);

}
