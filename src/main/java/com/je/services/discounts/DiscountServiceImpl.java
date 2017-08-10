package com.je.services.discounts;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.DiscountEntity;
import com.je.dbaccess.repositories.DiscountsRepository;

public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountsRepository discountsRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public void save(Discount discount) {
		DiscountEntity discountEntity = mapper.map(discount, DiscountEntity.class);
		discountEntity.setCreationdate(new Date());
		discountsRepository.save(discountEntity);
	}
}
