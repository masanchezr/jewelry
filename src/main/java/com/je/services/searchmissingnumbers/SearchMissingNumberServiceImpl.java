package com.je.services.searchmissingnumbers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.admin.forms.SearchMissingNumbers;
import com.je.dbaccess.entities.PawnEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.ShoppingEntity;
import com.je.dbaccess.repositories.PawnsRepository;
import com.je.dbaccess.repositories.ShoppingsRepository;

public class SearchMissingNumberServiceImpl implements SearchMissingNumberService {

	/** The shoppings repository. */
	@Autowired
	private ShoppingsRepository shoppingsRepository;

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;
	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public List<Long> searchMissingShoppings(SearchMissingNumbers form) {
		List<Long> nummissings = new ArrayList<>();
		PlaceEntity place = mapper.map(form.getPlace(), PlaceEntity.class);
		int year = form.getYear();
		ShoppingEntity entity;
		List<PawnEntity> pawns;
		for (long i = form.getNumfrom(); i <= form.getNumuntil(); i++) {
			entity = shoppingsRepository.findByNumshopAndPlaceAndYear(i, place, year);
			if (entity == null) {
				pawns = pawnsRepository.findByNumpawnAndPlaceAndYear(String.valueOf(i), place, year);
				if (pawns == null || pawns.isEmpty()) {
					nummissings.add(i);
				}
			}
		}
		return nummissings;
	}
}
