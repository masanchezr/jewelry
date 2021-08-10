package com.je.jsboot.services.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.ShoppingEntity;
import com.je.jsboot.services.shoppings.Shopping;
import com.je.jsboot.utils.date.DateUtil;

@Component
public class ShoppingEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public Shopping convertToShopping(ShoppingEntity entity) {
		Shopping shop = mapper.map(entity, Shopping.class);
		shop.setAmount(entity.getTotalamount().toString());
		return shop;
	}

	public ShoppingEntity convertToShoppingEntity(Shopping shop) {
		ShoppingEntity entity = mapper.map(shop, ShoppingEntity.class);
		entity.setCreationdate(DateUtil.getDate(shop.getCreationdate()));
		return entity;
	}

}
