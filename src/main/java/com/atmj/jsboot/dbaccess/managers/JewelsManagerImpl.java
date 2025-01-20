package com.atmj.jsboot.dbaccess.managers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.JewelRepository;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.string.Util;

/**
 * The Class JewelsManagerImpl.
 */
@Service
public class JewelsManagerImpl implements JewelsManager {

	/** The object repository. */
	@Autowired
	private JewelRepository jewelRepository;

	@Override
	public JewelEntity save(JewelEntity object) {
		object.setCreationdate(new Date());
		if (Util.isEmpty(object.getImg())) {
			object.setImg(null);
		}
		return jewelRepository.save(object);
	}

	@Override
	public List<JewelEntity> searchAll() {
		List<JewelEntity> target = new ArrayList<>();
		Iterable<JewelEntity> jewels = jewelRepository.findAll();
		Iterator<JewelEntity> ijewels = jewels.iterator();
		while (ijewels.hasNext()) {
			target.add(ijewels.next());
		}
		return target;
	}

	/**
	 * Busca por nombre y descripcion de joya y categoria que estan activas. Si el
	 * nombre y la categoria son nulas, buscara todas las joyas activas. Si la
	 * categoria es nula buscará solo por nombres.
	 *
	 * @param searchName the search name
	 * @param category   the category
	 * @return the list
	 */

	@Override
	public JewelEntity searchById(Long idjewel) {
		return jewelRepository.findById(idjewel).orElse(null);
	}

	@Override
	public List<JewelEntity> searchAllActive() {
		return iterableToList(jewelRepository.searchAllActive());
	}

	@Override
	public Page<JewelEntity> searchByNameDescCategory(String searchName, PageRequest page) {
		return jewelRepository.findByNameAndCategory("%" + searchName + "%", page);
	}

	/**
	 * Iterable to list.
	 * 
	 * @param ijewels the ijewels
	 * @return the list
	 */
	private List<JewelEntity> iterableToList(Iterable<JewelEntity> ijewels) {
		List<JewelEntity> jewels = null;
		if (ijewels != null) {
			jewels = new ArrayList<>();
			Iterator<JewelEntity> itJewels = ijewels.iterator();
			while (itJewels.hasNext()) {
				JewelEntity jewel = itJewels.next();
				jewels.add(jewel);
			}
		}
		return jewels;
	}

	@Override
	public List<JewelEntity> searchByReferenceAndCategory(JewelEntity jewel) {
		List<JewelEntity> jewels;
		if (Util.isEmpty(jewel.getReference())) {
			jewels = jewelRepository.findByCategory(jewel.getCategory());
		} else {
			jewels = jewelRepository.findByReferenceAndCategory(jewel.getReference(), jewel.getCategory());
		}

		return jewels;
	}

	@Override
	public JewelEntity searchByReferenceCategoryMetalPlaceActive(JewelEntity jewel) {
		List<JewelEntity> jewels = jewelRepository.findByReferenceAndCategoryAndMetalAndPlaceAndActive(
				jewel.getReference(), jewel.getCategory(), jewel.getMetal(), jewel.getPlace(), jewel.getActive());
		JewelEntity jewelEntity = null;
		if (jewels != null && !jewels.isEmpty()) {
			jewelEntity = jewels.get(0);
		}
		return jewelEntity;
	}

	@Override
	public JewelEntity searchByReferenceCategoryMetalPlace(JewelEntity jewel) {
		List<JewelEntity> jewels = jewelRepository.findByReferenceAndCategoryAndMetalAndPlace(jewel.getReference(),
				jewel.getCategory(), jewel.getMetal(), jewel.getPlace());
		JewelEntity jewelEntity = null;
		if (jewels != null && !jewels.isEmpty()) {
			jewelEntity = jewels.get(0);
		}
		return jewelEntity;
	}

	@Override
	public Page<JewelEntity> searchAllActivePageable(Pageable p) {
		return jewelRepository.searchAllActivePageable(p);
	}

	@Override
	public Page<JewelEntity> findByPlaceAndActiveTrue(PlaceEntity place, Pageable pageable) {
		return jewelRepository.findByPlaceAndActiveTrue(place, pageable);
	}

	@Override
	public List<JewelEntity> searchByReference(String reference) {
		return jewelRepository.findByReference(reference);
	}

	@Override
	public List<JewelEntity> searchByReferenceAndPlaceAndMetal(String reference, PlaceEntity place,
			MetalEntity material) {
		return jewelRepository.findByReferenceAndPlaceAndMetal(reference, place, material);
	}

	@Override
	public List<JewelEntity> searchByPlaceAndMetal(PlaceEntity map, MetalEntity map2) {
		return jewelRepository.findByPlaceAndMetal(map, map2);
	}

	@Override
	public List<JewelEntity> searchByPlaceAndMetalAndCategory(PlaceEntity map, MetalEntity map2, CategoryEntity map3) {
		return jewelRepository.findByCategoryAndPlaceAndMetal(map3, map, map2);
	}

	@Override
	public List<JewelEntity> searchAllActiveByPlace(JewelEntity map) {
		return jewelRepository.findByPlaceAndActiveTrueOrderByReference(map.getPlace());
	}

	@Override
	public JewelEntity findById(Long idjewel) {
		return jewelRepository.findById(idjewel).orElse(null);
	}

	@Override
	public Page<JewelEntity> searchActive(Pageable pageable) {
		return jewelRepository.findByActiveTrue(pageable);
	}

	@Override
	public void setGrams() {
		MetalEntity material = new MetalEntity();
		material.setIdmetal(Constants.ORO18K);
		List<JewelEntity> jewels = jewelRepository.findByMetalAndGramsIsNull(material);
		Iterator<JewelEntity> ijewels = jewels.iterator();
		JewelEntity jewel;
		String reference;
		BigDecimal pricegram;
		BigDecimal referencegrams;
		BigDecimal grams;
		BigDecimal hundred = new BigDecimal(100);
		while (ijewels.hasNext()) {
			jewel = ijewels.next();
			reference = jewel.getReference();
			if (Util.isNumeric(reference)) {
				referencegrams = new BigDecimal(reference);
				grams = referencegrams.divide(hundred);
				pricegram = jewel.getPrice().divide(grams, RoundingMode.HALF_UP);
				if (pricegram.compareTo(hundred) <= 0 && pricegram.compareTo(BigDecimal.valueOf(30)) > 0) {
					jewel.setGrams(grams);
					jewelRepository.save(jewel);
				}
			}
		}
	}

	@Override
	public Page<JewelEntity> searchByCategoryActive(CategoryEntity category, Pageable page) {
		Page<JewelEntity> jewels = null;
		if (category != null) {
			jewels = jewelRepository.findByCategoryAndActiveTrue(category, page);
		}
		return jewels;
	}

	@Override
	public List<JewelEntity> searchByPrice(BigDecimal price) {

		return jewelRepository.findByPrice(price);
	}

	@Override
	public List<JewelEntity> searchByPriceAndReference(BigDecimal price, String reference) {
		return jewelRepository.findByPriceAndReference(price, reference);
	}

	@Override
	public List<JewelEntity> findByPlaceAndBetweenCreationdate(PlaceEntity place, Date from, Date until) {
		if (until == null) {
			return jewelRepository.findByPlaceAndCreationdate(place, from);
		} else {
			return jewelRepository.findByPlaceAndCreationdateBetween(place, from, until);
		}
	}

	@Override
	public BigDecimal sumGramsByPlaceAndBetweenCreationdate(PlaceEntity place, Date from, Date until) {
		if (until == null) {
			return jewelRepository.sumGramsByPlaceAndCreationdate(place, from);
		} else {
			return jewelRepository.sumGramsByPlaceAndCreationdateBetween(place, from, until);
		}
	}
}
