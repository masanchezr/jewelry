package com.atmj.jsboot.services.discounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.repositories.DiscountsRepository;
import com.atmj.jsboot.services.converters.DiscountEntityConverter;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountsRepository discountsRepository;

	@Autowired
	private DiscountEntityConverter converterDiscount;

	public void save(Discount discount) {
		discountsRepository.save(converterDiscount.convertToDiscount(discount));
	}
}
