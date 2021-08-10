package com.je.jsboot.services.discounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.repositories.DiscountsRepository;
import com.je.jsboot.services.converters.DiscountEntityConverter;

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
