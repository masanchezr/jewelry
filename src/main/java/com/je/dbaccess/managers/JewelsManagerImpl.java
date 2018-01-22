package com.je.dbaccess.managers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.CategoriesRepository;
import com.je.dbaccess.repositories.JewelRepository;
import com.je.utils.constants.Constants;
import com.je.utils.string.Util;

/**
 * The Class JewelsManagerImpl.
 */
public class JewelsManagerImpl implements JewelsManager {

	/** The object repository. */
	@Autowired
	private JewelRepository jewelRepository;

	/** The category repository. */
	@Autowired
	private CategoriesRepository categoryRepository;

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
		List<JewelEntity> target = new ArrayList<JewelEntity>();
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
	 * @param searchName
	 *            the search name
	 * @param category
	 *            the category
	 * @return the list
	 */

	@Override
	public Page<JewelEntity> searchByNameDescCategoryActives(String searchName, CategoryEntity category,
			Pageable pageable) {
		Page<JewelEntity> jewels = null;
		if (searchName == null && category == null) {
			jewels = searchAllActivePageable(pageable);
		} else if (searchName != null && category == null) {
			jewels = jewelRepository.findByNameActivesWithImg(searchName, pageable);

		} else if (searchName == null && category != null) {
			jewels = jewelRepository.findByCategoryActivesWithImg(category, pageable);

		} else if (searchName != null && category != null) {
			jewels = jewelRepository.findByNameAndCategoryActivesWithImg(searchName, category, pageable);
		}
		return jewels;
	}

	@Override
	public JewelEntity searchById(Long idjewel) {
		return jewelRepository.findById(idjewel).get();
	}

	@Override
	public List<JewelEntity> searchAllActive() {
		return iterableToList(jewelRepository.searchAllActive());
	}

	@Override
	public List<JewelEntity> searchByNameDescCategory(String searchName) {
		List<JewelEntity> jewels = null;
		if (Util.isEmpty(searchName)) {
			Iterable<JewelEntity> iJewels = searchAll();
			if (iJewels != null) {
				jewels = new ArrayList<JewelEntity>();
				Iterator<JewelEntity> itJewels = iJewels.iterator();
				while (itJewels.hasNext()) {
					JewelEntity jewel = itJewels.next();
					jewels.add(jewel);
				}
			}
		} else {
			jewels = iterableToList(jewelRepository.findByNameAndCategory("%" + searchName + "%"));
		}
		return jewels;
	}

	@Override
	public List<JewelEntity> searchByCategoryActive(String keywordcategory) {
		List<JewelEntity> jewels = null;
		CategoryEntity category = categoryRepository.findByKeyword(keywordcategory);
		if (category != null) {
			jewels = new ArrayList<JewelEntity>();
			jewels.addAll(iterableToList(jewelRepository.findByCategoryAndActiveTrueAndImgNotNull(category)));
		}
		return jewels;
	}

	/**
	 * Iterable to list.
	 * 
	 * @param ijewels
	 *            the ijewels
	 * @return the list
	 */
	private List<JewelEntity> iterableToList(Iterable<JewelEntity> ijewels) {
		List<JewelEntity> jewels = null;
		if (ijewels != null) {
			jewels = new ArrayList<JewelEntity>();
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
	public List<JewelEntity> searchByReferenceAndPlaceAndMetal(String reference, PlaceEntity place, MetalEntity metal) {
		return jewelRepository.findByReferenceAndPlaceAndMetal(reference, place, metal);
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
		return jewelRepository.findById(idjewel).get();
	}

	@Override
	public Page<JewelEntity> searchWithImg(Pageable pageable) {
		return jewelRepository.findByImgIsNotNullAndActiveTrue(pageable);
	}

	@Override
	public void setGrams() {
		MetalEntity metal = new MetalEntity();
		metal.setIdmetal(Constants.ORO18K);
		List<JewelEntity> jewels = jewelRepository.findByMetalAndGramsIsNull(metal);
		Iterator<JewelEntity> ijewels = jewels.iterator();
		JewelEntity jewel;
		String reference;
		BigDecimal pricegram, referencegrams, grams, hundred = new BigDecimal(100);
		while (ijewels.hasNext()) {
			jewel = ijewels.next();
			reference = jewel.getReference();
			if (Util.isNumeric(reference)) {
				referencegrams = new BigDecimal(reference);
				grams = referencegrams.divide(hundred);
				pricegram = jewel.getPrice().divide(grams, RoundingMode.HALF_UP);
				if (pricegram.compareTo(hundred) <= 0 && pricegram.compareTo(new BigDecimal(30)) > 0) {
					jewel.setGrams(grams);
					jewelRepository.save(jewel);
				}
			}
		}
	}
}
