package com.atmj.jsboot.services.searchmissingnumbers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.admin.forms.SearchMissingNumbers;
import com.atmj.jsboot.dbaccess.entities.PawnEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.ShoppingEntity;
import com.atmj.jsboot.dbaccess.repositories.PawnsRepository;
import com.atmj.jsboot.dbaccess.repositories.ShoppingsRepository;

@Service
public class SearchMissingNumberServiceImpl implements SearchMissingNumberService {

	/** The shoppings repository. */
	@Autowired
	private ShoppingsRepository shoppingsRepository;

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;
	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public List<Long> searchMissingShoppings(SearchMissingNumbers form) {
		List<Long> nummissings = new ArrayList<>();
		PlaceEntity place = mapper.map(form.getPlace(), PlaceEntity.class);
		int year = form.getYear();
		ShoppingEntity entity;
		List<PawnEntity> pawns;
		for (long i = form.getNumfrom(); i <= form.getNumuntil(); i++) {
			entity = shoppingsRepository.findByNumshopAndPlaceAndYear(i, place, year);
			if (entity == null) {
				pawns = pawnsRepository.findByNumpawnAndPlaceAndYearAndIdreturnpawnIsNull(String.valueOf(i), place,
						year);
				if (pawns == null || pawns.isEmpty()) {
					nummissings.add(i);
				}
			}
		}
		return nummissings;
	}
}
