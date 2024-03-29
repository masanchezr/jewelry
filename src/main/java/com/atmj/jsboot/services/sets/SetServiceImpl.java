package com.atmj.jsboot.services.sets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.SetEntity;
import com.atmj.jsboot.dbaccess.managers.JewelsManager;
import com.atmj.jsboot.dbaccess.repositories.CategoriesRepository;
import com.atmj.jsboot.dbaccess.repositories.SetsRepository;

/**
 * The Class SetServiceImpl.
 */
@Service
public class SetServiceImpl implements SetService {

	/** The category repository. */
	@Autowired
	private CategoriesRepository categoryRepository;

	/** The jewels manager. */
	@Autowired
	private JewelsManager jewelsManager;

	/** The set repository. */
	@Autowired
	private SetsRepository setRepository;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public void saveSet(NewSet set) {
		MetalEntity material = mapper.map(set.getMetal(), MetalEntity.class);
		PlaceEntity place = mapper.map(set.getPlace(), PlaceEntity.class);
		CategoryEntity categoryRing = categoryRepository.findByKeyword("anillo");
		JewelEntity jewel = new JewelEntity();
		jewel.setMetal(material);
		jewel.setPlace(place);
		jewel.setCategory(categoryRing);
		jewel.setReference(set.getReferencering());
		JewelEntity ring = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		CategoryEntity categoryBrazalet = categoryRepository.findByKeyword("pulsera");
		jewel.setCategory(categoryBrazalet);
		jewel.setReference(set.getReferencebrazalet());
		JewelEntity brazalet = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		CategoryEntity categoryPendant = categoryRepository.findByKeyword("colgante");
		jewel.setCategory(categoryPendant);
		jewel.setReference(set.getReferencependant());
		JewelEntity pendant = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		CategoryEntity categoryEarrings = categoryRepository.findByKeyword("pendientes");
		jewel.setCategory(categoryEarrings);
		jewel.setReference(set.getReferenceearrings());
		JewelEntity earrings = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		CategoryEntity categoryChoker = categoryRepository.findByKeyword("gargantilla");
		jewel.setCategory(categoryChoker);
		jewel.setReference(set.getReferencechoker());
		JewelEntity choker = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		CategoryEntity categoryTiePin = categoryRepository.findByKeyword("pasacorbatas");
		jewel.setCategory(categoryTiePin);
		jewel.setReference(set.getReferencetiepin());
		JewelEntity tiepin = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		CategoryEntity categoryCufflinks = categoryRepository.findByKeyword("gemelos");
		jewel.setCategory(categoryCufflinks);
		jewel.setReference(set.getReferencecufflinks());
		JewelEntity cufflinks = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		CategoryEntity categoryDiamondRing = categoryRepository.findByKeyword("sortija-diamante");
		jewel.setCategory(categoryDiamondRing);
		jewel.setReference(set.getReferencediamondring());
		JewelEntity diamondring = jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
		SetEntity setEntity = new SetEntity();
		setEntity.setBracelet(brazalet);
		setEntity.setEarrings(earrings);
		setEntity.setPendant(pendant);
		setEntity.setRing(ring);
		setEntity.setChocker(choker);
		setEntity.setCufflins(cufflinks);
		setEntity.setTiepin(tiepin);
		setEntity.setDiamondring(diamondring);
		setRepository.save(setEntity);
	}

	public List<Set> allSets() {
		Iterable<SetEntity> itsets = setRepository.findAll();
		List<Set> sets = null;
		if (itsets != null) {
			Iterator<SetEntity> isets = itsets.iterator();
			if (isets != null) {
				SetEntity setEntity;
				List<JewelEntity> jewels;
				Set set;
				sets = new ArrayList<>();
				while (isets.hasNext()) {
					jewels = new ArrayList<>();
					setEntity = isets.next();
					if (setEntity.getBracelet() != null) {
						jewels.add(setEntity.getBracelet());
					}
					if (setEntity.getChocker() != null) {
						jewels.add(setEntity.getChocker());
					}
					if (setEntity.getCufflins() != null) {
						jewels.add(setEntity.getCufflins());
					}
					if (setEntity.getEarrings() != null) {
						jewels.add(setEntity.getEarrings());
					}
					if (setEntity.getPendant() != null) {
						jewels.add(setEntity.getPendant());
					}
					if (setEntity.getRing() != null) {
						jewels.add(setEntity.getRing());
					}
					if (setEntity.getTiepin() != null) {
						jewels.add(setEntity.getTiepin());
					}
					if (setEntity.getDiamondring() != null) {
						jewels.add(setEntity.getDiamondring());
					}
					set = new Set();
					set.setIdset(setEntity.getIdset());
					set.setJewels(jewels);
					sets.add(set);
				}
			}
		}
		return sets;
	}
}
