package com.je.services.searchmissingnumbers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.PawnEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.ShoppingEntity;
import com.je.dbaccess.managers.SaleManager;
import com.je.dbaccess.repositories.PawnsRepository;
import com.je.dbaccess.repositories.ShoppingsRepository;

public class SearchMissingNumberServiceImpl implements SearchMissingNumberService {

	/** The shoppings repository. */
	@Autowired
	private ShoppingsRepository shoppingsRepository;

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;

	/** The sale manager. */
	@Autowired
	private SaleManager saleManager;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public List<Long> searchMissingShoppings(SearchMissingNumbers form) {
		List<Long> nummissings = new ArrayList<Long>();
		PlaceEntity place = mapper.map(form.getPlace(), PlaceEntity.class);
		int year = form.getYear();
		ShoppingEntity entity;
		PawnEntity pawn;
		for (long i = form.getNumfrom(); i <= form.getNumuntil(); i++) {
			entity = shoppingsRepository.findByNumshopAndPlaceAndYear(i, place, year);
			if (entity == null) {
				pawn = pawnsRepository.findByNumpawnAndPlaceAndYear(String.valueOf(i), place, year);
				if (pawn == null) {
					nummissings.add(new Long(i));
				}
			} else {
				pawn = pawnsRepository.findByNumpawnAndPlaceAndYear(String.valueOf(i), place, year);
				if (pawn != null) {
					if (entity.getTotalamount().equals(pawn.getAmount())) {
						nummissings.add(new Long(i));
					}
				}
			}
		}
		return nummissings;
	}

	public List<Long> calculateMissingSales(SearchMissingNumbers form) {
		return saleManager.calculateNumberMissing(form.getNumfrom(), form.getNumuntil(),
				mapper.map(form.getPlace(), PlaceEntity.class));
	}

	public Long getSalePostponedMissing(PlaceEntity place) {
		return saleManager.numsalepostponed(place);
	}
}
