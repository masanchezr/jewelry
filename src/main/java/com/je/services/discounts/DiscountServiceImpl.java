package com.je.services.discounts;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.DiscountEntity;
import com.je.dbaccess.repositories.DiscountsRepository;
import com.je.utils.date.DateUtil;

public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountsRepository discountsRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public void save(Discount discount) {
		DiscountEntity discountEntity = mapper.map(discount, DiscountEntity.class);
		Date today = new Date();
		discountEntity.setCreationdate(today);
		discountEntity.setYear(DateUtil.getYear(today));
		discountsRepository.save(discountEntity);
	}
}
