package com.je.jsboot.services.jewels;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.CategoryEntity;
import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.managers.JewelsManager;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.string.Util;

/**
 * The Class JewelServiceImpl.
 */
@Service
public class JewelServiceImpl implements JewelService {

	/** The jewels manager. */
	@Autowired
	private JewelsManager jewelsManager;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	/**
	 * Adds the jewel.
	 * 
	 * @param thing the thing
	 */

	@Override
	public JewelEntity addObject(JewelEntity thing) {
		if (Util.isEmpty(thing.getImg())) {
			thing.setImg(null);
		}
		return jewelsManager.findById(jewelsManager.save(thing).getIdjewel());
	}

	@Override
	public Collection<JewelEntity> searchAll() {
		return jewelsManager.searchAll();
	}

	@Override
	public JewelEntity selectProduct(Long idjewel) {
		return jewelsManager.searchById(idjewel);
	}

	@Override
	public Page<JewelEntity> searchAllActive(int pageNumber) {
		return jewelsManager.searchAllActivePageable(PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE));
	}

	@Override
	public Page<JewelEntity> searchPageableByPlace(int pageNumber, PlaceEntity place) {
		return jewelsManager.findByPlaceAndActiveTrue(place, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE));
	}

	@Override
	public List<JewelEntity> search(JewelEntity jewelForm) {
		List<JewelEntity> jewels = null;
		String reference = jewelForm.getReference();
		if (!Util.isEmpty(reference)) {
			if (jewelForm.getCategory().getIdcategory().equals(Constants.ALLCATEGORIES)) {
				jewels = jewelsManager.searchByReferenceAndPlaceAndMetal(reference,
						mapper.map(jewelForm.getPlace(), PlaceEntity.class),
						mapper.map(jewelForm.getMetal(), MetalEntity.class));
			} else {
				jewels = new ArrayList<>();
				JewelEntity jewel = jewelsManager
						.searchByReferenceCategoryMetalPlace(mapper.map(jewelForm, JewelEntity.class));
				if (jewel != null) {
					jewels.add(mapper.map(jewel, JewelEntity.class));
				}
			}
		} else if (jewelForm.getCategory().getIdcategory().equals(Constants.ALLCATEGORIES)) {
			jewels = jewelsManager.searchByPlaceAndMetal(jewelForm.getPlace(), jewelForm.getMetal());
		} else {
			jewels = jewelsManager.searchByPlaceAndMetalAndCategory(jewelForm.getPlace(), jewelForm.getMetal(),
					jewelForm.getCategory());
		}
		return jewels;
	}

	@Override
	public void updateJewelEntity(JewelEntity jewelForm) {
		jewelsManager.save(jewelForm);
	}

	@Override
	public JewelEntity searchByReferenceCategoryMetalPlaceActive(JewelEntity jewel) {
		return jewelsManager.searchByReferenceCategoryMetalPlaceActive(jewel);
	}

	@Override
	public JewelEntity searchByReferenceCategoryMetalPlace(JewelEntity jewel) {
		return jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
	}

	@Override
	public List<JewelEntity> searchByReferenceAndCategory(JewelEntity jewelForm) {
		return jewelsManager.searchByReferenceAndCategory(jewelForm);
	}

	@Override
	public List<JewelEntity> searchAllActiveByPlace(JewelEntity jewel) {
		return jewelsManager.searchAllActiveByPlace(jewel);
	}

	@Override
	public List<JewelEntity> searchByReference(JewelEntity jewel) {
		return jewelsManager.searchByReference(jewel.getReference());
	}

	@Override
	public void revise(JewelEntity jewel) {
		JewelEntity jewelEntity = jewelsManager.searchById(jewel.getIdjewel());
		jewelEntity.setRevised(Boolean.TRUE);
		jewelsManager.save(jewelEntity);

	}

	@Override
	public Page<JewelEntity> searchActive(int i) {
		return jewelsManager.searchActive(PageRequest.of(i - 1, Constants.PAGE_SIZE));
	}

	public List<JewelEntity> searchJewels(List<JewelEntity> jewels, PlaceEntity place) {
		JewelEntity jewel;
		List<JewelEntity> newjewels = new ArrayList<>();
		Iterator<JewelEntity> ijewels = jewels.iterator();
		while (ijewels.hasNext() && newjewels != null) {
			jewel = ijewels.next();
			if (!Util.isEmpty(jewel.getReference())) {
				jewel.setPlace(place);
				jewel.setActive(true);
				jewel = searchByReferenceCategoryMetalPlaceActive(jewel);
				if (jewel != null && jewel.getIdjewel() != null) {
					newjewels.add(jewel);
				} else {
					newjewels = null;
				}
			}
		}
		return newjewels;
	}

	@Override
	public Page<JewelEntity> searchJewelsByCategory(CategoryEntity category, int i) {
		return jewelsManager.searchByCategoryActive(category, PageRequest.of(i - 1, Constants.PAGE_SIZE));
	}

	@Override
	public List<JewelEntity> searchByPrice(JewelEntity jewel) {
		String reference = jewel.getReference();
		BigDecimal price = jewel.getPrice();
		if (!Util.isEmpty(reference)) {
			if (price != null) {
				return jewelsManager.searchByPriceAndReference(price, reference);
			} else {
				return jewelsManager.searchByReference(reference);
			}
		} else {
			return jewelsManager.searchByPrice(price);
		}
	}
}
