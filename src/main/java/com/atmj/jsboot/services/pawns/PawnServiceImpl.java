package com.atmj.jsboot.services.pawns;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.ClientPawnEntity;
import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.ObjectPawnEntity;
import com.atmj.jsboot.dbaccess.entities.PawnEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.RenovationEntity;
import com.atmj.jsboot.dbaccess.repositories.ClientPawnsRepository;
import com.atmj.jsboot.dbaccess.repositories.PawnsRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.RenovationsRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;
import com.atmj.jsboot.services.converters.ClientPawnEntityConverter;
import com.atmj.jsboot.services.converters.PawnEntityConverter;
import com.atmj.jsboot.services.dailies.Daily;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;
import com.atmj.services.EmailService;

/**
 * The Class PawnServiceImpl.
 */
@Service
public class PawnServiceImpl implements PawnService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private EmailService emailService;

	/** The client pawns repository. */
	@Autowired
	private ClientPawnsRepository clientPawnsRepository;

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	/** The renovations repository. */
	@Autowired
	private RenovationsRepository renovationsRepository;

	@Autowired
	private UsersRepository usersRepository;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ClientPawnEntityConverter clientPawnConverter;

	@Autowired
	private PawnEntityConverter pawnconverter;

	private static Logger log = LoggerFactory.getLogger(PawnServiceImpl.class);

	@Override
	public Daily save(NewPawn pawn) {
		PawnEntity pawnEntity = pawnconverter.convertToDTO(pawn);
		ClientPawnEntity cpe = clientPawnsRepository.findById(pawn.getNif()).orElse(null);
		PlaceEntity place = placeUserRepository.findByUser(usersRepository.findByUsername(pawn.getUser())).get(0)
				.getPlace();
		Date creationdate = pawnEntity.getCreationdate();
		Calendar calendar = Calendar.getInstance();
		List<ObjectPawnEntity> newobjects = new ArrayList<>();
		List<ObjectPawnEntity> object = pawnEntity.getObjects();
		Iterator<ObjectPawnEntity> iobjects = object.iterator();
		if (creationdate != null) {
			log.warn("Creation date: ".concat(creationdate.toString()));
		} else {
			log.warn("Creation date is null");
		}
		calendar.setTime(creationdate);
		int year = calendar.get(Calendar.YEAR);
		if (cpe == null) {
			cpe = mapper.map(pawn, ClientPawnEntity.class);
			cpe.setCreationclient(new Date());
			clientPawnsRepository.save(cpe);
		}
		pawnEntity.setPlace(place);
		pawnEntity.setClient(cpe);
		pawnEntity.setYear(year);
		pawnEntity.setReturnpawn(Boolean.FALSE);
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
		log.warn("Antes del return del parte");
		return dailyService.getDaily(DateUtil.getDateFormated(creationdate), place, null);
	}

	@Override
	public Daily saveReturnPawn(NewPawn pawn) {
		PawnEntity pawnEnt = mapper.map(pawn, PawnEntity.class);
		PawnEntity pawnEntity = pawnsRepository.findById(pawn.getId()).orElse(null);
		PlaceEntity place = placeUserRepository.findByUser(usersRepository.findByUsername(pawn.getUser())).get(0)
				.getPlace();
		Date datedaily = pawnEnt.getCreationdate();
		if (datedaily == null) {
			datedaily = DateUtil.getDateFormated(new Date());
		}
		if (pawnEntity != null) {
			pawnEntity.setReturnpawn(Boolean.TRUE);
			pawnsRepository.save(pawnEntity);
			PawnEntity newpawn = new PawnEntity();
			newpawn.setAmount(pawnEnt.getAmount());
			newpawn.setPercent(pawnEnt.getPercent());
			newpawn.setCreationdate(new Date());
			newpawn.setReturnpawn(Boolean.FALSE);
			newpawn.setIdreturnpawn(pawn.getId());
			newpawn.setClient(pawnEntity.getClient());
			newpawn.setNumpawn(pawnEntity.getNumpawn());
			newpawn.setPlace(pawnEntity.getPlace());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pawnEntity.getCreationdate());
			int year = calendar.get(Calendar.YEAR);
			List<ObjectPawnEntity> newobjects = new ArrayList<>();
			List<ObjectPawnEntity> object = pawnEnt.getObjects();
			Iterator<ObjectPawnEntity> iobjects = object.iterator();
			newpawn.setYear(year);
			ObjectPawnEntity ope;
			while (iobjects.hasNext()) {
				ope = iobjects.next();
				if (ope.getGrossgrams() != null) {
					ope.setPawn(newpawn);
					newobjects.add(ope);
				}
			}
			newpawn.setObjects(newobjects);
			pawnsRepository.save(newpawn);
		}
		return dailyService.getDaily(datedaily, place, null);
	}

	@Override
	/**
	 * Updating the pawn from the administrator
	 */
	public void update(NewPawn pawn) {
		PawnEntity pawnEntity = pawnsRepository.findById(pawn.getId()).orElse(null);
		Iterator<ObjectPawnEntity> iobjects;
		ObjectPawnEntity object;
		ObjectPawnEntity op;
		Iterator<ObjectPawnEntity> iop = pawn.getObjects().iterator();
		boolean exists;
		if (pawnEntity != null) {
			List<ObjectPawnEntity> objects = pawnEntity.getObjects();
			while (iop.hasNext()) {
				op = iop.next();
				iobjects = objects.iterator();
				exists = false;
				while (iobjects.hasNext() && !exists) {
					object = iobjects.next();
					if (object.getIdobjectpawn().equals(op.getIdobjectpawn())) {
						exists = true;
						object.setRealgrams(op.getRealgrams());
						object.setDescription(op.getDescription());
						object.setMetal(op.getMetal());
					}
				}
				if (!exists) {
					op.setPawn(pawnEntity);
					pawnEntity.getObjects().add(op);
				}
			}
			ClientPawnEntity cpe = clientPawnsRepository.findById(pawn.getNif()).orElse(new ClientPawnEntity());
			clientPawnConverter.convertToDTO(pawn, cpe);
			cpe.setCreationclient(new Date());
			clientPawnsRepository.save(cpe);
			pawnEntity.setMeltdate(new Date());
			pawnEntity.setPercent(Util.getNumber(pawn.getPercent()));
			pawnEntity.setAmount(Util.getNumber(pawn.getAmount()));
			pawnsRepository.save(pawnEntity);
		}
	}

	@Override
	public Pawn searchByIdpawn(Long idpawn) {
		return mapper.map(pawnsRepository.findById(idpawn), Pawn.class);
	}

	@Override
	public NewPawn findByIdpawn(Long idpawn) {
		PawnEntity pawnEntity = pawnsRepository.findById(idpawn).orElse(null);
		NewPawn pawn = null;
		if (pawnEntity != null) {
			pawn = pawnconverter.convertToNewPawn(pawnEntity);
			mapper.map(pawnEntity.getClient(), pawn);
		}
		return pawn;
	}

	@Override
	public List<Pawn> searchRenewByNumpawn(Pawn pawn) {
		PlaceEntity place = placeUserRepository.findByUser(usersRepository.findByUsername(pawn.getUser())).get(0)
				.getPlace();
		return pawnconverter.entitiesToPawns(pawnsRepository.findByNumpawnAndPlaceAndRetired(pawn.getNumpawn(), place));
	}

	@Override
	public List<Pawn> searchByNumpawn(Pawn pawn) {
		return pawnconverter.entitiesToPawns(pawnsRepository.findByNumpawnAndPlace(pawn.getNumpawn(),
				mapper.map(pawn.getPlace(), PlaceEntity.class)));
	}

	@Override
	public Daily renew(Pawn pawn) {
		Daily daily = null;
		PawnEntity pawnEntity = pawnsRepository.findById(pawn.getId()).orElse(null);
		if (pawnEntity != null) {
			List<RenovationEntity> renovations = pawnEntity.getRenovations();
			for (int i = 0; i < pawn.getNumrenovations() && renovations != null; i++) {
				Calendar calendar = Calendar.getInstance();
				Date date;
				if (!renovations.isEmpty()) {
					date = renovations.get(renovations.size() - 1).getNextrenovationdate();
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
				renovations.add(entity);
			}
			pawnEntity.setRenovations(renovations);
			pawnsRepository.save(pawnEntity);
		}
		daily = dailyService.getDaily(DateUtil.getDateFormated(new Date()),
				placeUserRepository.findByUser(usersRepository.findByUsername(pawn.getUser())).get(0).getPlace(), null);
		return daily;
	}

	@Override
	public Daily remove(Pawn pawn) {
		Long idpawn = pawn.getId();
		PawnEntity pawnEntity = pawnsRepository.findById(idpawn).orElse(null);
		PlaceEntity place = null;
		if (pawnEntity != null) {
			place = pawnEntity.getPlace();
			Long idplace = place.getIdplace();
			int months = pawn.getMonths();
			if (months > 0) {
				pawnEntity.setMonths(months);
			}
			pawnEntity.setDateretired(new Date());
			pawnsRepository.save(pawnEntity);
			// una vez retirado vamos a calcular si faltan renovaciones
			if (Constants.STODOMINGO.equals(idplace)) {
				pawnEntity = pawnsRepository.searchMissingMonths(idpawn);
			} else {
				pawnEntity = pawnsRepository.searchMissingRenovations(idpawn);
			}
			if (pawnEntity != null) {
				emailService.sendSimpleMessage("mangeles.sanchez0807@gmail.com", "REVISAR EMPEÑO",
						"Número empeño: " + pawnEntity.getNumpawn() + ", fecha creación: "
								+ pawnEntity.getCreationdate() + ", lugar:" + idplace);
			}
		}
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}

	@Override
	public List<RenovationDates> searchRenovations(Long idpawn) {
		PawnEntity pawnEntity = pawnsRepository.findById(idpawn).orElse(null);
		List<RenovationDates> renovationdates = new ArrayList<>();
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
	public Quarter searchGramsByDates(String sDateFrom, String sDateUntil) {
		Object[] pawnEntity = null;
		Date datefrom = DateUtil.getDate(sDateFrom);
		Date dateuntil = DateUtil.getDate(sDateUntil);
		List<Object[]> pawns = pawnsRepository.findGrossGramsByMetal(datefrom, dateuntil);
		Quarter quarter = new Quarter();
		Iterator<Object[]> ipawns = pawns.iterator();
		pawnEntity = ipawns.next();
		quarter.setGramsreal((BigDecimal) pawnEntity[0]);
		quarter.setGrossgrams((BigDecimal) pawnEntity[1]);
		quarter.setAmount((BigDecimal) pawnEntity[2]);
		quarter.setMetal((MetalEntity) pawnEntity[3]);
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
					percent = percent.multiply(BigDecimal.valueOf(months));
				}
				BigDecimal percentamount = amount.multiply(percent).divide(BigDecimal.valueOf(100));
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
				renovationamount = renovationamount.divide(BigDecimal.valueOf(100));
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
		ClientPawnEntity client = clientPawnsRepository.findById(nif).orElse(null);
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
			pawns = new ArrayList<>();
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
					pawns.addAll(searchRenovations(entity, calendar));
				}
			}
		}
		return pawns;
	}

	private Collection<? extends Pawn> searchRenovations(PawnEntity entity, Calendar calendar) {
		List<Pawn> pawns = new ArrayList<>();
		List<RenovationEntity> renovations = renovationsRepository
				.searchPawnsExpired(DateUtil.getDateFormated(new Date()), entity);
		calendar.add(Calendar.MONTH, -1);
		if ((renovations == null || renovations.isEmpty()) && entity.getCreationdate().before(calendar.getTime())) {
			pawns.add(mapper.map(entity, Pawn.class));
		}
		return pawns;
	}

	@Override
	public Double sumPawnsActiveByPlace(PlaceEntity place) {
		return pawnsRepository.sumPawnsActive(mapper.map(place, PlaceEntity.class));
	}

	@Override
	public List<PawnEntity> getByNIF(String nif) {
		ClientPawnEntity client = new ClientPawnEntity();
		client.setNif(nif);
		return pawnsRepository.findByClient(client);
	}

	@Override
	public List<PawnEntity> getByNIFAndUserAndRetiredAndReturn(String nif, String user) {
		ClientPawnEntity client = new ClientPawnEntity();
		PlaceEntity place = placeUserRepository.findByUser(usersRepository.findByUsername(user)).get(0).getPlace();
		client.setNif(nif);
		return pawnsRepository.findByClientAndPlaceAndDateretiredIsNotNullAndReturnpawnFalse(client, place);
	}
}
