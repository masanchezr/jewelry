package com.atmj.jsboot.dbaccess.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;

/**
 * The Interface CategoriesRepository.
 */
public interface CategoriesRepository extends PagingAndSortingRepository<CategoryEntity, Long> {

	/**
	 * Find by active.
	 * 
	 * @param sort
	 *
	 * @param b
	 *            the b
	 * @return the iterable
	 */
	public Iterable<CategoryEntity> findByActiveTrue(Sort sort);

	/**
	 * Find by keyword.
	 *
	 * @param b
	 *            the b
	 * @return the iterable
	 */
	public CategoryEntity findByKeyword(String b);
}
