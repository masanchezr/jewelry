package com.je.jsboot.services.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.ShoppingEntity;
import com.je.jsboot.services.shoppings.Shopping;

@Component
public class ShoppingEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public Shopping convertTo(ShoppingEntity entity) {
		Shopping shop = mapper.map(entity, Shopping.class);
		shop.setAmount(entity.getTotalamount().toString());
		return shop;
	}

}
