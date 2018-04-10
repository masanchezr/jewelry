package com.je.services.shoppings;

import java.io.IOException;
import java.math.BigDecimal;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ClientPawnEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PaymentShopEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.ShoppingEntity;
import com.je.dbaccess.repositories.ClientPawnsRepository;
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
			ClientPawnEntity cpe = clientPawnsRepository.findById(nif).orElse(null);
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
		List<ObjectShopEntity> newobjects = new ArrayList<>();
		Iterator<ObjectShopEntity> iobjects = objects.iterator();
		ObjectShopEntity ose;
		List<PaymentShopEntity> paymentshop = new ArrayList<>();
		BigDecimal wiretransfer = shopping.getWiretransfer();
		BigDecimal cashamount = shopping.getCashamount();
		BigDecimal totalamount = BigDecimal.ZERO;
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
			shoppings = new ArrayList<>();
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
			Date datefrom = DateUtil.getDate(sDateFrom);
			Date dateuntil = DateUtil.getDate(sDateUntil);
			shoppings = mapper(shoppingsRepository.findByNumshopAndPlaceAndCreationdateBetween(numshop, place, datefrom,
					dateuntil));
		} else if (numshop != null && !Util.isEmpty(sDateFrom) && Util.isEmpty(sDateUntil)) {
			Date datefrom = DateUtil.getDate(sDateFrom);
			shoppings = mapper(shoppingsRepository.findByNumshopAndPlaceAndCreationdateBetween(numshop, place, datefrom,
					new Date()));
		} else if (numshop != null && Util.isEmpty(sDateFrom) && Util.isEmpty(sDateUntil)) {
			shoppings = mapper(shoppingsRepository.findByNumshopAndPlace(numshop, place));
		} else if (sDateFrom != null && !Util.isEmpty(sDateUntil)) {
			Date datefrom = DateUtil.getDate(sDateFrom);
			Date dateuntil = DateUtil.getDate(sDateUntil);
			shoppings = mapper(shoppingsRepository.findByCreationdateBetweenAndPlace(datefrom, dateuntil, place));
		} else {
			Date datefrom = DateUtil.getDate(sDateFrom);
			shoppings = mapper(shoppingsRepository.findByCreationdateBetweenAndPlace(datefrom, new Date(), place));
		}
		return shoppings;
	}

	@Override
	public Shopping findShopByPK(Long idshop) {
		ShoppingEntity shopping = shoppingsRepository.findById(idshop).orElse(null);
		Shopping shop = null;
		if (shopping != null) {
			shop = mapper.map(shopping, Shopping.class);
		}
		return shop;
	}

	@Override
	public void update(Shopping shopping) {
		ShoppingEntity shoppingEntity = shoppingsRepository.findById(shopping.getId()).orElse(null);
		if (shoppingEntity != null) {
			List<ObjectShopEntity> objects = shoppingEntity.getObjects();
			Iterator<ObjectShopEntity> iobjects;
			List<ObjectShopEntity> newobjects = new ArrayList<>();
			List<ObjectShopEntity> los = shopping.getObjects();
			Iterator<ObjectShopEntity> ilos = los.iterator();
			ObjectShopEntity object;
			ObjectShopEntity os;
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
			shoppingEntity.setMeltdate(new Date());
			shoppingsRepository.save(shoppingEntity);
		}
	}

	@Override
	public List<QuarterMetal> searchGramsByMetal(String sDateFrom, String sDateUntil, PlaceEntity place) {
		Date datefrom = DateUtil.getDate(sDateFrom);
		Date dateuntil = DateUtil.getDate(sDateUntil);
		List<QuarterMetal> lquartermaterial = new ArrayList<>();
		List<Object[]> shoppings = shoppingsRepository.findGramsByMetal(datefrom, dateuntil, place);
		Object[] lose;
		Iterator<Object[]> ishoppings = shoppings.iterator();
		while (ishoppings.hasNext()) {
			lose = ishoppings.next();
			QuarterMetal qm = new QuarterMetal();
			if (lose[0] != null) {
				qm.setRealgrams((BigDecimal) lose[0]);
			}
			qm.setGrossgrams((BigDecimal) lose[1]);
			qm.setNetgrams((BigDecimal) lose[2]);
			qm.setAmount((BigDecimal) lose[3]);
			qm.setMetal((MetalEntity) lose[4]);
			lquartermaterial.add(qm);
		}
		return lquartermaterial;
	}

	@Override
	public List<Long> searchGramsNull(String sDateFrom, String sDateUntil, PlaceEntity place) {
		Date datefrom = DateUtil.getDate(sDateFrom);
		Date dateuntil = DateUtil.getDate(sDateUntil);
		return shoppingsRepository.findGramsNull(place, datefrom, dateuntil);
	}

	@Override
	public Shopping searchClient(String nif) {
		ClientPawnEntity client = clientPawnsRepository.findById(nif).orElse(null);
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
		Logger logger = LoggerFactory.getLogger(ShoppingServiceImpl.class);
		try (XSSFWorkbook myWorkBook = new XSSFWorkbook()) {
			XSSFSheet spreadsheet = myWorkBook.createSheet("Hoja1");
			XSSFFont font = myWorkBook.createFont();
			XSSFCellStyle style = myWorkBook.createCellStyle();
			Shopping shop;
			XSSFRow row = spreadsheet.createRow(0);
			Cell numshop = row.createCell(0);
			Cell date = row.createCell(1);
			Cell name = row.createCell(2);
			Cell nif = row.createCell(3);
			Cell address = row.createCell(4);
			Cell town = row.createCell(5);
			Cell country = row.createCell(6);
			Cell objects = row.createCell(7);
			Cell material = row.createCell(8);
			Cell record = row.createCell(9);
			Cell rock = row.createCell(10);
			List<Shopping> shoppings = searchShoppings(datefrom, dateuntil, place, null);
			List<ObjectShopEntity> lobjects;
			Iterator<ObjectShopEntity> ilobjects;
			Iterator<Shopping> ishoppings = shoppings.iterator();
			ObjectShopEntity os;
			String description;
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
			material.setCellValue("METAL");
			material.setCellStyle(style);
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
				material = row.createCell(8);
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
					material.setCellValue(os.getMetal().getDescription());
					description = os.getDescription();
					descriptions = description.split(";");
					setCells(descriptions, spreadsheet, objects, record, rock, i);
					i = i + descriptions.length;
					if (ilobjects.hasNext()) {
						row = spreadsheet.createRow(i);
						objects = row.createCell(7);
						material = row.createCell(8);
						record = row.createCell(9);
						rock = row.createCell(10);
					}
				}
			} // fin recorrido compras
			for (i = 0; i < 10; i++) {
				spreadsheet.autoSizeColumn(i);
			}
			return myWorkBook;
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
			return null;
		}
	}

	private void setCells(String[] descriptions, XSSFSheet spreadsheet, Cell objects, Cell record, Cell rock, int i) {
		String descrip;
		XSSFRow row;
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
