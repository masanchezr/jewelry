package com.je.services.pawns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ClientPawnEntity;
import com.je.dbaccess.entities.ObjectPawnEntity;
import com.je.dbaccess.entities.PawnEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.RenovationEntity;
import com.je.dbaccess.entities.ShoppingEntity;
import com.je.dbaccess.repositories.ClientPawnsRepository;
import com.je.dbaccess.repositories.PawnsRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.dbaccess.repositories.RenovationsRepository;
import com.je.dbaccess.repositories.ShoppingsRepository;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.services.mails.MailService;
import com.je.utils.constants.Constants;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class PawnServiceImpl.
 */
public class PawnServiceImpl implements PawnService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;

	/** The renovations repository. */
	@Autowired
	private RenovationsRepository renovationsRepository;

	/** The client pawns repository. */
	@Autowired
	private ClientPawnsRepository clientPawnsRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private ShoppingsRepository shoppingsRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	@Override
	public Daily save(NewPawn pawn) {
		// cuidado con el mapeo del creationdate
		PawnEntity pawnEntity = mapper.map(pawn, PawnEntity.class);
		ClientPawnEntity cpe = clientPawnsRepository.findOne(pawn.getNif());
		PlaceEntity place = placeUserRepository.findByUsername(pawn.getUser()).get(0).getPlace();
		if (Util.isEmpty(pawn.getCreationdate())) {
			pawnEntity.setCreationdate(new Date());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pawnEntity.getCreationdate());
		int year = calendar.get(Calendar.YEAR);
		List<ObjectPawnEntity> newobjects = new ArrayList<ObjectPawnEntity>();
		List<ObjectPawnEntity> object = pawnEntity.getObjects();
		Iterator<ObjectPawnEntity> iobjects = object.iterator();
		if (cpe == null) {
			// TODO creo que esto así no actualiza el cliente en el caso de que
			// se lo muestre
			cpe = mapper.map(pawn, ClientPawnEntity.class);
			cpe.setCreationclient(new Date());
		}
		pawnEntity.setPlace(place);
		pawnEntity.setClient(cpe);
		pawnEntity.setYear(year);
		ObjectPawnEntity ope;
		while (iobjects.hasNext()) {
			ope = iobjects.next();
			if (ope.getGrossgrams() != null) {
				ope.setPawn(pawnEntity);
				newobjects.add(ope);
			}
		}
		pawnEntity.setObjects(newobjects);
		pawnsRepository.save(pawnEntity);
		return dailyService.getDaily(pawnEntity.getCreationdate(), place, null);
	}

	@Override
	public void update(NewPawn pawn) {
		PawnEntity pawnEntity = pawnsRepository.findOne(pawn.getIdpawn());
		List<ObjectPawnEntity> objects = pawnEntity.getObjects();
		List<ObjectPawnEntity> newobjects = new ArrayList<ObjectPawnEntity>();
		Iterator<ObjectPawnEntity> iobjects;
		ObjectPawnEntity object, op;
		Iterator<ObjectPawnEntity> iop = pawn.getObjects().iterator();
		boolean exists;
		while (iop.hasNext()) {
			op = iop.next();
			iobjects = objects.iterator();
			exists = false;
			while (iobjects.hasNext() && !exists) {
				object = iobjects.next();
				if (object.getMetal().getIdmetal().equals(op.getMetal().getIdmetal())) {
					exists = true;
					object.setRealgrams(op.getRealgrams());
					object.setDescription(op.getDescription());
					newobjects.add(object);
				}
			}
			if (!exists) {
				op.setPawn(pawnEntity);
				newobjects.add(op);
			}
		}
		ClientPawnEntity cpe = clientPawnsRepository.findOne(pawn.getNif());
		if (cpe == null) {
			cpe = mapper.map(pawn, ClientPawnEntity.class);
		} else {
			mapper.map(pawn, cpe);
		}
		cpe.setCreationclient(new Date());
		pawnEntity.setObjects(newobjects);
		pawnEntity.setClient(cpe);
		pawnsRepository.save(pawnEntity);
	}

	@Override
	public Pawn searchByIdpawn(Long idpawn) {
		return mapper.map(pawnsRepository.findOne(idpawn), Pawn.class);
	}

	@Override
	public NewPawn findByIdpawn(Long idpawn) {
		PawnEntity pawnEntity = pawnsRepository.findOne(idpawn);
		NewPawn pawn = mapper.map(pawnEntity, NewPawn.class);
		mapper.map(pawnEntity.getClient(), pawn);
		return pawn;
	}

	@Override
	public List<Pawn> searchRenewByNumpawn(Pawn pawn) {
		PlaceEntity place = placeUserRepository.findByUsername(pawn.getUser()).get(0).getPlace();
		return mapper(pawnsRepository.findByNumpawnAndPlaceAndRetired(pawn.getNumpawn(), place));
	}

	@Override
	public List<Pawn> searchByNumpawn(Pawn pawn) {
		return mapper(pawnsRepository.findByNumpawnAndPlace(pawn.getNumpawn(),
				mapper.map(pawn.getPlace(), PlaceEntity.class)));
	}

	/**
	 * Mapper.
	 *
	 * @param findByNumpawnAndPlace
	 *            the find by numpawn and place
	 * @return the list
	 */
	private List<Pawn> mapper(List<PawnEntity> entities) {
		List<Pawn> pawns = null;
		if (entities != null) {
			Iterator<PawnEntity> ipawns = entities.iterator();
			pawns = new ArrayList<Pawn>();
			while (ipawns.hasNext()) {
				pawns.add(mapper.map(ipawns.next(), Pawn.class));
			}
		}
		return pawns;
	}

	@Override
	public Daily renew(Pawn pawn) {
		PawnEntity pawnEntity = pawnsRepository.findOne(pawn.getIdpawn());
		if (pawnEntity != null) {
			Date date;
			List<RenovationEntity> renovations = pawnEntity.getRenovations();
			Calendar calendar = Calendar.getInstance();
			if (renovations != null && !renovations.isEmpty()) {
				Date daterenovation;
				date = renovations.get(0).getNextrenovationdate();
				for (int i = 1; i < renovations.size(); i++) {
					daterenovation = renovations.get(i).getNextrenovationdate();
					if (daterenovation.after(date)) {
						date = daterenovation;
					}
				}
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, 1);
			} else {
				date = pawnEntity.getCreationdate();
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, 2);
			}
			RenovationEntity entity = new RenovationEntity();
			entity.setPawn(pawnEntity);
			entity.setCreationdate(new Date());
			entity.setNextrenovationdate(calendar.getTime());
			renovationsRepository.save(entity);
		}
		return dailyService.getDaily(new Date(), pawnEntity.getPlace(), null);
	}

	@Override
	public Daily remove(Pawn pawn) {
		MailService mailService;
		Long idpawn = pawn.getIdpawn();
		PawnEntity pawnEntity = pawnsRepository.findOne(idpawn);
		PlaceEntity place = pawnEntity.getPlace();
		Long idplace = place.getIdplace();
		int months = pawn.getMonths();
		if (months > 0) {
			pawnEntity.setMonths(months);
		}
		pawnEntity.setDateretired(new Date());
		pawnsRepository.save(pawnEntity);
		// una vez retirado vamos a calcular si faltan renovaciones
		List<PawnEntity> pawns;
		if (Constants.STODOMINGO.equals(idplace)) {
			pawns = pawnsRepository.searchMissingMonths(idpawn);
		} else {
			pawns = pawnsRepository.searchMissingRenovations(idpawn);
		}
		if (pawns != null && !pawns.isEmpty()) {
			mailService = new MailService("N&uacute;mero empe&ntilde;o: " + pawnEntity.getNumpawn()
					+ ", fecha creaci&oacute;n: " + pawnEntity.getCreationdate() + ", lugar:" + idplace, null,
					"REVISAR EMPE&Ntilde;O.");
			mailService.start();
		}
		return dailyService.getDaily(new Date(), place, null);
	}

	@Override
	public List<RenovationDates> searchRenovations(Long idpawn) {
		PawnEntity pawnEntity = pawnsRepository.findOne(idpawn);
		List<RenovationDates> renovationdates = new ArrayList<RenovationDates>();
		if (pawnEntity != null) {
			List<RenovationEntity> renovations = pawnEntity.getRenovations();
			if (renovations != null) {
				Iterator<RenovationEntity> irenovations = renovations.iterator();
				while (irenovations.hasNext()) {
					renovationdates.add(mapper.map(irenovations.next(), RenovationDates.class));
				}
			}
		}
		return renovationdates;
	}

	@Override
	public Quarter searchGramsByDates(String sDateFrom, String sDateUntil, PlaceEntity place) {
		PawnEntity pawnEntity = null;
		Date datefrom = DateUtil.getDate(sDateFrom), dateuntil = DateUtil.getDate(sDateUntil);
		List<PawnEntity> pawns = pawnsRepository.findByCreationdateBetweenAndPlace(datefrom, dateuntil,
				mapper.map(place, PlaceEntity.class));
		Quarter quarter = new Quarter();
		List<ObjectPawnEntity> lose;
		Iterator<ObjectPawnEntity> ilose;
		Iterator<PawnEntity> ipawns = pawns.iterator();
		ObjectPawnEntity ose;
		BigDecimal gramsreal = BigDecimal.ZERO, grossgrams = BigDecimal.ZERO, amount = BigDecimal.ZERO;
		double gramsAg = 0, amountag = 0;
		while (ipawns.hasNext()) {
			pawnEntity = ipawns.next();
			// cuidado el importe de la plata también se está sumando aquí
			amount = amount.add(pawnEntity.getAmount());
			lose = pawnEntity.getObjects();
			ilose = lose.iterator();
			while (ilose.hasNext()) {
				ose = ilose.next();
				if (!ose.getMetal().getIdmetal().equals(Constants.PLATA)) {
					if (ose.getRealgrams() != null) {
						gramsreal = gramsreal.add(ose.getRealgrams());
					}
					grossgrams = grossgrams.add(ose.getGrossgrams());
				}
			}
		}
		quarter.setAmount(amount);
		quarter.setGramsreal(gramsreal);
		quarter.setGrossgrams(grossgrams);
		quarter.setGramsAg(gramsAg);
		quarter.setAmountag(amountag);
		if (gramsreal != null && gramsreal.compareTo(BigDecimal.ZERO) > 0) {
			quarter.setAveragegold(amount.divide(gramsreal, 2, RoundingMode.HALF_UP));
		} else {
			quarter.setAveragegold(amount.divide(grossgrams, 2, RoundingMode.HALF_UP));
		}
		return quarter;
	}

	@Override
	public BigDecimal getCommissions(String sDateFrom, String sDateUntil, PlaceEntity place) {
		BigDecimal commissions = BigDecimal.ZERO;
		Date dfrom = DateUtil.getDate(sDateFrom);
		Date duntil = DateUtil.getDate(sDateUntil);
		PlaceEntity placeEntity = mapper.map(place, PlaceEntity.class);
		List<PawnEntity> pawns = pawnsRepository.findByDateretiredBetweenAndPlace(dfrom, duntil, placeEntity);
		List<RenovationEntity> renovations = renovationsRepository.findByCreationdateBetweenAndPlace(dfrom, duntil,
				placeEntity);
		if (pawns != null) {
			Iterator<PawnEntity> ipawns = pawns.iterator();
			PawnEntity pawnEntity;
			BigDecimal amount;
			while (ipawns.hasNext()) {
				pawnEntity = ipawns.next();
				amount = pawnEntity.getAmount();
				BigDecimal percent = pawnEntity.getPercent();
				Integer months = pawnEntity.getMonths();
				if (months != null && months.intValue() > 0
						&& pawnEntity.getPlace().getIdplace().equals(Constants.STODOMINGO)) {
					percent = percent.multiply(new BigDecimal(months));
				}
				BigDecimal percentamount = amount.multiply(percent).divide(new BigDecimal(100));
				/**
				 * if (percentamount - ((int) percentamount) != 0) { percentamount += 1; }
				 **/
				commissions = commissions.add(percentamount);
			}
		}
		if (renovations != null) {
			Iterator<RenovationEntity> irenovations = renovations.iterator();
			RenovationEntity renovation;
			Renovation re;
			PawnEntity pawn;
			BigDecimal renovationamount;
			while (irenovations.hasNext()) {
				renovation = irenovations.next();
				re = new Renovation();
				pawn = renovation.getPawn();
				re.setNumpawn(pawn.getNumpawn());
				renovationamount = pawn.getAmount().multiply(pawn.getPercent());
				renovationamount = renovationamount.divide(new BigDecimal(100));
				/**
				 * if ((renovationamount - ((int) renovationamount) != 0)) { renovationamount +=
				 * 1; }
				 **/
				re.setRenovationamount(renovationamount);
				commissions = commissions.add(re.getRenovationamount());
			}
		}
		return commissions;
	}

	@Override
	public NewPawn searchClient(String nif) {
		ClientPawnEntity client = clientPawnsRepository.findOne(nif);
		NewPawn pawn = new NewPawn();
		if (client != null) {
			mapper.map(client, pawn);
		} else {
			pawn.setNif(nif);
		}
		return pawn;
	}

	@Override
	public List<Pawn> pawnsOutofdate(PlaceEntity place) {
		List<Pawn> pawns = null;
		Calendar calendar;
		List<PawnEntity> entities = pawnsRepository.searchByPlaceAndNotRetired(place);
		if (entities != null) {
			pawns = new ArrayList<Pawn>();
			Iterator<PawnEntity> ientities = entities.iterator();
			PawnEntity entity;
			while (ientities.hasNext()) {
				calendar = Calendar.getInstance();
				entity = ientities.next();
				if (place.getIdplace().equals(Constants.STODOMINGO)) {
					calendar.add(Calendar.MONTH, -6);
					if (entity.getCreationdate().before(calendar.getTime())) {
						pawns.add(mapper.map(entity, Pawn.class));
					}
				} else {
					List<RenovationEntity> renovations = renovationsRepository.searchPawnsExpired(new Date(), entity);
					calendar.add(Calendar.MONTH, -1);
					if (renovations == null || renovations.isEmpty()) {
						if (entity.getCreationdate().before(calendar.getTime())) {
							pawns.add(mapper.map(entity, Pawn.class));
						}
					}
				}
			}
		}
		return pawns;
	}

	@Override
	public Double sumPawnsActiveByPlace(PlaceEntity place) {
		return pawnsRepository.sumPawnsActive(mapper.map(place, PlaceEntity.class));
	}

	@Override
	public boolean isRepeatNumber(String num, String user, int year) {
		boolean repeat = true;
		PlaceEntity placeEntity = placeUserRepository.findByUsername(user).get(0).getPlace();
		ShoppingEntity searchShopping = null;
		if (Util.isNumeric(num)) {
			searchShopping = shoppingsRepository.findByNumshopAndPlaceAndYear(Long.valueOf(num), placeEntity, year);
		}
		// miramos también si existe ya como empeño
		PawnEntity pawn = pawnsRepository.findByNumpawnAndPlaceAndYear(num, placeEntity, year);
		if (searchShopping == null && pawn == null) {
			repeat = false;
		}
		return repeat;
	}

	@Override
	/**
	 * elimino temporalmente este método ya que si ordenamos los empeños por fecha
	 * puede que no nos los de bien y si los ordenamos por id y hemos metido un
	 * empeño de otra fecha ya tenemos el lío otra vez
	 **/
	public boolean isCorrectNumber(String num, String user, int i) {
		boolean isCorrectNumber = false;
		if (Util.isNumeric(num)) {
			Long number = Long.valueOf(num);
			if (number >= 3000) {
				isCorrectNumber = true;
			} else {
				PlaceEntity placeEntity = placeUserRepository.findByUsername(user).get(0).getPlace();
				List<ShoppingEntity> shoppings = shoppingsRepository.findByPlaceAndYearOrderByNumshopDesc(placeEntity,
						i);
				List<PawnEntity> pawns = pawnsRepository.findByPlaceAndYearOrderByCreationdateDesc(placeEntity, i);
				if ((shoppings == null || shoppings.isEmpty()) && (pawns == null || pawns.isEmpty())) {
					isCorrectNumber = true;
				} else if (shoppings != null) {
					ShoppingEntity searchShopping;
					Iterator<ShoppingEntity> ishoppings = shoppings.iterator();
					Long nshop;
					while (ishoppings.hasNext()) {
						searchShopping = ishoppings.next();
						nshop = searchShopping.getNumshop();
						if (nshop < 3000) {
							if (number == nshop + 1) {
								isCorrectNumber = true;
							} else {
								isCorrectNumber = false;
							}
							break;
						}
					}
				}
				if (pawns != null && !isCorrectNumber) {
					PawnEntity pawn;
					String snpawn;
					Long npawn;
					Iterator<PawnEntity> ipawns = pawns.iterator();
					while (ipawns.hasNext()) {
						pawn = ipawns.next();
						snpawn = pawn.getNumpawn();
						if (Util.isNumeric(snpawn)) {
							npawn = Long.valueOf(snpawn);
							if (npawn < 3000) {
								if (number == npawn + 1) {
									isCorrectNumber = true;
								} else {
									isCorrectNumber = false;
								}
								break;
							}
						}
					}
				}
			}
		} else {
			isCorrectNumber = true;
		}
		return isCorrectNumber;
	}
}
