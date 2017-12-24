package com.je.services.shoppings;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ClientPawnEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PaymentShopEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.ShoppingEntity;
import com.je.dbaccess.repositories.ClientPawnsRepository;
import com.je.dbaccess.repositories.MetalRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.dbaccess.repositories.ShoppingsRepository;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.utils.constants.Constants;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class ShoppingServiceImpl.
 */
public class ShoppingServiceImpl implements ShoppingService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/** The shoppings repository. */
	@Autowired
	private ShoppingsRepository shoppingsRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private MetalRepository metalRepository;

	/** The client pawns repository. */
	@Autowired
	private ClientPawnsRepository clientPawnsRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	@Override
	public Daily save(Shopping shopping) {
		ShoppingEntity shoppingEntity = mapper.map(shopping, ShoppingEntity.class);
		String nif = shopping.getNif();
		if (nif != null) {
			ClientPawnEntity cpe = clientPawnsRepository.findById(nif).get();
			if (cpe == null) {
				cpe = mapper.map(shopping, ClientPawnEntity.class);
				cpe.setCreationclient(new Date());
				clientPawnsRepository.save(cpe);
			}
			shoppingEntity.setClient(cpe);
		}
		Calendar calendar = Calendar.getInstance();
		PlaceEntity place = placeUserRepository.findByUsername(shopping.getUser()).get(0).getPlace();
		List<ObjectShopEntity> objects = shoppingEntity.getObjects();
		List<ObjectShopEntity> newobjects = new ArrayList<ObjectShopEntity>();
		Iterator<ObjectShopEntity> iobjects = objects.iterator();
		ObjectShopEntity ose;
		List<PaymentShopEntity> paymentshop = new ArrayList<PaymentShopEntity>();
		BigDecimal wiretransfer = shopping.getWiretransfer(), cashamount = shopping.getCashamount(),
				totalamount = BigDecimal.ZERO;
		while (iobjects.hasNext()) {
			ose = iobjects.next();
			if (ose.getGrossgrams() != null) {
				ose.setShop(shoppingEntity);
				newobjects.add(ose);
			}
		}
		if (wiretransfer != null) {
			PaymentEntity payment = new PaymentEntity();
			PaymentShopEntity payshop = new PaymentShopEntity();
			payment.setIdpayment(Constants.TRANSFER);
			payshop.setPayment(payment);
			payshop.setAmount(wiretransfer);
			payshop.setShop(shoppingEntity);
			paymentshop.add(payshop);
			totalamount = totalamount.add(wiretransfer);
		}
		if (cashamount != null) {
			PaymentEntity payment = new PaymentEntity();
			PaymentShopEntity payshop = new PaymentShopEntity();
			payment.setIdpayment(Constants.EFECTIVO);
			payshop.setPayment(payment);
			payshop.setAmount(cashamount);
			payshop.setShop(shoppingEntity);
			paymentshop.add(payshop);
			totalamount = totalamount.add(cashamount);
		}
		if (shoppingEntity.getCreationdate() == null) {
			shoppingEntity.setCreationdate(new Date());
		}
		shoppingEntity.setPlace(place);
		shoppingEntity.setSpayments(paymentshop);
		shoppingEntity.setObjects(newobjects);
		shoppingEntity.setYear(calendar.get(Calendar.YEAR));
		shoppingEntity.setTotalamount(totalamount);
		shoppingsRepository.save(shoppingEntity);
		return dailyService.getDaily(new Date(), place, null);
	}

	/**
	 * Mapper.
	 *
	 * @param shoppingsEntity
	 *            the shoppings entity
	 * @return the list
	 */
	private List<Shopping> mapper(List<ShoppingEntity> shoppingsEntity) {
		List<Shopping> shoppings = null;
		if (shoppingsEntity != null) {
			Iterator<ShoppingEntity> ishoppingsEntity = shoppingsEntity.iterator();
			Shopping shopping;
			ShoppingEntity shoppingEntity;
			ClientPawnEntity client;
			shoppings = new ArrayList<Shopping>();
			while (ishoppingsEntity.hasNext()) {
				shoppingEntity = ishoppingsEntity.next();
				client = shoppingEntity.getClient();
				shopping = mapper.map(shoppingEntity, Shopping.class);
				if (client != null) {
					mapper.map(shoppingEntity.getClient(), shopping);
				}
				shoppings.add(shopping);
			}
		}
		return shoppings;
	}

	@Override
	public List<Shopping> searchShoppings(String sDateFrom, String sDateUntil, PlaceEntity place, Long numshop) {
		List<Shopping> shoppings = null;
		if (numshop != null && sDateFrom != null && !Util.isEmpty(sDateUntil)) {
			Date datefrom = DateUtil.getDate(sDateFrom), dateuntil = DateUtil.getDate(sDateUntil);
			shoppings = mapper(shoppingsRepository.findByNumshopAndPlaceAndCreationdateBetween(numshop, place, datefrom,
					dateuntil));
		} else if (numshop != null && !Util.isEmpty(sDateFrom) && Util.isEmpty(sDateUntil)) {
			Date datefrom = DateUtil.getDate(sDateFrom);
			shoppings = mapper(shoppingsRepository.findByNumshopAndPlaceAndCreationdateBetween(numshop, place, datefrom,
					new Date()));
		} else if (numshop != null && Util.isEmpty(sDateFrom) && Util.isEmpty(sDateUntil)) {
			shoppings = mapper(shoppingsRepository.findByNumshopAndPlace(numshop, place));
		} else if (sDateFrom != null && !Util.isEmpty(sDateUntil)) {
			Date datefrom = DateUtil.getDate(sDateFrom), dateuntil = DateUtil.getDate(sDateUntil);
			shoppings = mapper(shoppingsRepository.findByCreationdateBetweenAndPlace(datefrom, dateuntil, place));
		} else {
			Date datefrom = DateUtil.getDate(sDateFrom);
			shoppings = mapper(shoppingsRepository.findByCreationdateBetweenAndPlace(datefrom, new Date(), place));
		}
		return shoppings;
	}

	@Override
	public Quarter searchGramsByDates(String sDateFrom, String sDateUntil, PlaceEntity place) {
		ShoppingEntity shoppingEntity = null;
		Date datefrom = DateUtil.getDate(sDateFrom), dateuntil = DateUtil.getDate(sDateUntil);
		List<ShoppingEntity> shoppings = shoppingsRepository.findByCreationdateBetweenAndPlace(datefrom, dateuntil,
				place);
		Quarter quarter = new Quarter();
		List<ObjectShopEntity> lose;
		Iterator<ObjectShopEntity> ilose;
		Iterator<ShoppingEntity> ishoppings = shoppings.iterator();
		ObjectShopEntity ose;
		BigDecimal amount = BigDecimal.ZERO, gramsreal = BigDecimal.ZERO, grossgrams = BigDecimal.ZERO,
				netgrams = BigDecimal.ZERO, gramsAg = BigDecimal.ZERO, amountag = BigDecimal.ZERO;
		while (ishoppings.hasNext()) {
			shoppingEntity = ishoppings.next();
			lose = shoppingEntity.getObjects();
			ilose = lose.iterator();
			while (ilose.hasNext()) {
				ose = ilose.next();
				if (ose.getMetal().getIdmetal().equals(Constants.PLATA)) {
					if (ose.getRealgrams() != null) {
						gramsAg = gramsAg.add(ose.getRealgrams());
					}
					amountag = amountag.add(ose.getAmount());
				} else {
					if (ose.getRealgrams() != null) {
						gramsreal = gramsreal.add(ose.getRealgrams());
					}
					grossgrams = grossgrams.add(ose.getGrossgrams());
					netgrams = netgrams.add(ose.getNetgrams());
					amount = amount.add(ose.getAmount());
				}
			}
		}
		quarter.setAmount(amount);
		quarter.setGramsreal(gramsreal);
		quarter.setGrossgrams(grossgrams);
		quarter.setNetgrams(netgrams);
		quarter.setGramsAg(gramsAg);
		quarter.setAmountag(amountag);
		quarter.setAveragegold(amount.divide(gramsreal, 2, RoundingMode.HALF_UP));
		return quarter;
	}

	@Override
	public Shopping findShopByPK(Long idshop) {
		ShoppingEntity shopping = shoppingsRepository.findById(idshop).get();
		return mapper.map(shopping, Shopping.class);
	}

	@Override
	public void update(Shopping shopping) {
		ShoppingEntity shoppingEntity = shoppingsRepository.findById(shopping.getId()).get();
		List<ObjectShopEntity> objects = shoppingEntity.getObjects();
		Iterator<ObjectShopEntity> iobjects;
		List<ObjectShopEntity> newobjects = new ArrayList<ObjectShopEntity>();
		List<ObjectShopEntity> los = shopping.getObjects();
		Iterator<ObjectShopEntity> ilos = los.iterator();
		ObjectShopEntity object, os;
		while (ilos.hasNext()) {
			os = ilos.next();
			iobjects = objects.iterator();
			while (iobjects.hasNext()) {
				object = iobjects.next();
				if (object.getIdobjectshop().equals(os.getIdobjectshop())) {
					object.setRealgrams(os.getRealgrams());
					object.setGrossgrams(os.getGrossgrams());
					object.setAmount(os.getAmount());
					object.setMetal(os.getMetal());
					newobjects.add(object);
				}
			}
		}
		shoppingEntity.setObjects(newobjects);
		shoppingsRepository.save(shoppingEntity);
	}

	@Override
	public List<QuarterMetal> searchGramsByMetal(String sDateFrom, String sDateUntil, PlaceEntity place) {
		Date datefrom = DateUtil.getDate(sDateFrom), dateuntil = DateUtil.getDate(sDateUntil);
		List<QuarterMetal> lquartermetal = new ArrayList<QuarterMetal>();
		List<ShoppingEntity> shoppings = shoppingsRepository.findByCreationdateBetweenAndPlace(datefrom, dateuntil,
				place);
		List<ObjectShopEntity> lose;
		Iterator<ObjectShopEntity> ilose;
		Iterator<ShoppingEntity> ishoppings = shoppings.iterator();
		ObjectShopEntity ose;
		while (ishoppings.hasNext()) {
			lose = ishoppings.next().getObjects();
			ilose = lose.iterator();
			while (ilose.hasNext()) {
				ose = ilose.next();
				QuarterMetal qm = new QuarterMetal();
				qm.setGrossgrams(ose.getGrossgrams());
				qm.setNetgrams(ose.getNetgrams());
				qm.setRealgrams(ose.getRealgrams());
				qm.setAmount(ose.getAmount());
				qm.setMetal(ose.getMetal());
				lquartermetal.add(qm);
			}
		}
		Iterable<MetalEntity> metals = metalRepository.findAll();
		Iterator<MetalEntity> imetals = metals.iterator();
		List<QuarterMetal> lqm = new ArrayList<QuarterMetal>();
		MetalEntity metal;
		QuarterMetal qm;
		BigDecimal realgrams, grossgrams, netgrams, amount, amountmetal;
		boolean ismetal;
		while (imetals.hasNext()) {
			metal = imetals.next();
			ismetal = false;
			realgrams = BigDecimal.ZERO;
			grossgrams = BigDecimal.ZERO;
			netgrams = BigDecimal.ZERO;
			amount = BigDecimal.ZERO;
			for (QuarterMetal quarterMetal : lquartermetal) {
				if (quarterMetal.getMetal().getIdmetal().equals(metal.getIdmetal())) {
					realgrams = realgrams.add(quarterMetal.getRealgrams());
					netgrams = netgrams.add(quarterMetal.getNetgrams());
					grossgrams = grossgrams.add(quarterMetal.getGrossgrams());
					amountmetal = quarterMetal.getAmount();
					if (amountmetal != null) {
						amount = amount.add(amountmetal);
					}
					ismetal = true;
				}
			}
			if (ismetal) {
				qm = new QuarterMetal();
				qm.setGrossgrams(grossgrams);
				qm.setNetgrams(netgrams);
				qm.setRealgrams(realgrams);
				qm.setAmount(amount);
				qm.setMetal(metal);
				lqm.add(qm);
			}
		}
		return lqm;
	}

	@Override
	public List<Long> searchGramsNull(String sDateFrom, String sDateUntil, PlaceEntity place) {
		Date datefrom = DateUtil.getDate(sDateFrom), dateuntil = DateUtil.getDate(sDateUntil);
		return shoppingsRepository.findGramsNull(place, datefrom, dateuntil);
	}

	@Override
	public Shopping searchClient(String nif) {
		ClientPawnEntity client = clientPawnsRepository.findById(nif).get();
		Shopping pawn = new Shopping();
		if (client != null) {
			mapper.map(client, pawn);
		} else {
			pawn.setNif(nif);
		}
		return pawn;
	}

	@Override
	public XSSFWorkbook generateExcel(String datefrom, String dateuntil, PlaceEntity place) {
		XSSFWorkbook myWorkBook = new XSSFWorkbook();
		XSSFSheet spreadsheet = myWorkBook.createSheet("Hoja1");
		XSSFFont font = myWorkBook.createFont();
		XSSFCellStyle style = myWorkBook.createCellStyle();
		Shopping shop;
		XSSFRow row = spreadsheet.createRow(0);
		Cell numshop = row.createCell(0), date = row.createCell(1), name = row.createCell(2), nif = row.createCell(3),
				address = row.createCell(4), town = row.createCell(5), country = row.createCell(6),
				objects = row.createCell(7), metal = row.createCell(8), record = row.createCell(9),
				rock = row.createCell(10);
		List<Shopping> shoppings = searchShoppings(datefrom, dateuntil, place, null);
		List<ObjectShopEntity> lobjects;
		Iterator<ObjectShopEntity> ilobjects;
		Iterator<Shopping> ishoppings = shoppings.iterator();
		ObjectShopEntity os;
		String description, descrip;
		String[] descriptions;
		int i = 1;
		font.setBold(true);
		style.setFont(font);
		numshop.setCellValue("LOTE");
		numshop.setCellStyle(style);
		date.setCellValue("FECHA");
		date.setCellStyle(style);
		name.setCellValue("APELLIDOS Y NOMBRE");
		name.setCellStyle(style);
		nif.setCellValue("DNI,NIF O PASAPORTE");
		nif.setCellStyle(style);
		address.setCellValue("DOMICILIO");
		address.setCellStyle(style);
		town.setCellValue("LOCALIDAD");
		town.setCellStyle(style);
		country.setCellValue("PROVINCIA O PAIS");
		country.setCellStyle(style);
		objects.setCellValue("OBJETOS");
		objects.setCellStyle(style);
		metal.setCellValue("METAL");
		metal.setCellStyle(style);
		record.setCellValue("GRABACIONES");
		record.setCellStyle(style);
		rock.setCellValue("PIEDRAS");
		rock.setCellStyle(style);
		while (ishoppings.hasNext()) {
			row = spreadsheet.createRow(i);
			numshop = row.createCell(0);
			date = row.createCell(1);
			name = row.createCell(2);
			nif = row.createCell(3);
			address = row.createCell(4);
			town = row.createCell(5);
			country = row.createCell(6);
			objects = row.createCell(7);
			metal = row.createCell(8);
			record = row.createCell(9);
			rock = row.createCell(10);
			shop = ishoppings.next();
			numshop.setCellValue(shop.getNumshop());
			date.setCellValue(shop.getCreationdate());
			name.setCellValue(shop.getSurname().concat(", ").concat(shop.getName()));
			nif.setCellValue(shop.getNif());
			address.setCellValue(shop.getAddress());
			town.setCellValue(shop.getTown());
			country.setCellValue(shop.getNation().getNation());
			lobjects = shop.getObjects();
			ilobjects = lobjects.iterator();
			while (ilobjects.hasNext()) {
				os = ilobjects.next();
				System.out.println(os.getIdobjectshop());
				metal.setCellValue(os.getMetal().getDescription());
				description = os.getDescription();
				descriptions = description.split(";");
				for (int d = 0; d < descriptions.length; d++) {
					descrip = descriptions[d];
					if (d > 0) {
						row = spreadsheet.createRow(i);
						objects = row.createCell(7);
						record = row.createCell(9);
						rock = row.createCell(10);
					}
					if (descrip.contains("p:") && descrip.contains("g:")) {
						rock.setCellValue(descrip.substring(descrip.indexOf("p:"), descrip.indexOf("g:")));
						record.setCellValue(descrip.substring(descrip.indexOf("g:") + 2, descrip.length()));
						objects.setCellValue(descrip.substring(0, descrip.indexOf("p:")));
					} else if (descrip.contains("g:")) {
						record.setCellValue(descrip.substring(descrip.indexOf("g:") + 2, descrip.length()));
						objects.setCellValue(descrip.substring(0, descrip.indexOf("g:")));
					} else if (descrip.contains("p:")) {
						rock.setCellValue(descrip.substring(descrip.indexOf("p:") + 2, descrip.length()));
						objects.setCellValue(descrip.substring(0, descrip.indexOf("p:")));
					} else {
						objects.setCellValue(descriptions[d]);
					}
					i++;
				}
				if (ilobjects.hasNext()) {
					row = spreadsheet.createRow(i);
					objects = row.createCell(7);
					metal = row.createCell(8);
					record = row.createCell(9);
					rock = row.createCell(10);
				}
			}
		} // fin recorrido compras
		for (i = 0; i < 10; i++) {
			spreadsheet.autoSizeColumn(i);
		}
		return myWorkBook;
	}

	@Override
	public boolean isCorrectNumber(Shopping shoppingForm) {
		Calendar c = Calendar.getInstance();
		boolean isCorrectNumber = false;
		Long number = shoppingForm.getNumshop();
		PlaceEntity placeEntity = placeUserRepository.findByUsername(shoppingForm.getUser()).get(0).getPlace();
		ShoppingEntity shopping = shoppingsRepository.findFirstByPlaceAndYearOrderByNumshopDesc(placeEntity,
				c.get(Calendar.YEAR));
		if (shopping == null) {
			isCorrectNumber = true;
		} else {
			if (number == shopping.getNumshop() + 1) {
				isCorrectNumber = true;
			} else {
				isCorrectNumber = false;
			}
		}
		return isCorrectNumber;
	}

	@Override
	public Long getNextNumber(String user) {
		Calendar c = Calendar.getInstance();
		Long number = null;
		PlaceEntity placeEntity = placeUserRepository.findByUsername(user).get(0).getPlace();
		ShoppingEntity shopping = shoppingsRepository.findFirstByPlaceAndYearOrderByNumshopDesc(placeEntity,
				c.get(Calendar.YEAR));
		if (shopping != null) {
			number = shopping.getNumshop() + 1;
		}
		return number;
	}
}
