package com.je.jsboot.services.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.SalePostponedEntity;
import com.je.jsboot.forms.SalePostPoned;

@Component
public class SalePostPonedEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public SalePostPoned convertToDTO(SalePostponedEntity entity) {
		SalePostPoned saleView = mapper.map(entity, SalePostPoned.class);
		saleView.setTotal(entity.getTotalamount());
		saleView.setIdsale(entity.getIdsalepostponed());
		return saleView;
	}

}
