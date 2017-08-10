package com.je.services.dailies;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.AdjustmentEntity;
import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.CancelSaleEntity;
import com.je.dbaccess.entities.CancelSalePaymentEntity;
import com.je.dbaccess.entities.DailyEntity;
import com.je.dbaccess.entities.DiscountEntity;
import com.je.dbaccess.entities.EntryMoneyEntity;
import com.je.dbaccess.entities.OtherConceptEntity;
import com.je.dbaccess.entities.PawnEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PaymentShopEntity;
import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.RecordingEntity;
import com.je.dbaccess.entities.RenovationEntity;
import com.je.dbaccess.entities.RentalEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.entities.SalesPayments;
import com.je.dbaccess.entities.ShoppingEntity;
import com.je.dbaccess.entities.StrapEntity;
import com.je.dbaccess.managers.HolidaysManager;
import com.je.dbaccess.managers.SaleManager;
import com.je.dbaccess.repositories.AdjustmentRepository;
import com.je.dbaccess.repositories.BatteriesRepository;
import com.je.dbaccess.repositories.CancelSaleRepository;
import com.je.dbaccess.repositories.DailyRepository;
import com.je.dbaccess.repositories.DiscountsRepository;
import com.je.dbaccess.repositories.EntryMoneyRepository;
import com.je.dbaccess.repositories.OtherConceptsRepository;
import com.je.dbaccess.repositories.PawnsRepository;
import com.je.dbaccess.repositories.PayrollRepository;
import com.je.dbaccess.repositories.RecordingRepository;
import com.je.dbaccess.repositories.RenovationsRepository;
import com.je.dbaccess.repositories.RentalsRepository;
import com.je.dbaccess.repositories.ShoppingsRepository;
import com.je.dbaccess.repositories.StrapsRepository;
import com.je.services.adjustments.Adjustment;
import com.je.services.discounts.Discount;
import com.je.services.otherconcepts.OtherConcept;
import com.je.services.pawns.Pawn;
import com.je.services.pawns.Renovation;
import com.je.services.payroll.Payroll;
import com.je.services.rentals.Rental;
import com.je.services.sales.CancelSale;
import com.je.services.sales.Sale;
import com.je.services.shoppings.Shopping;
import com.je.utils.constants.Constants;

/**
 * The Class DailyServiceImpl.
 */
public class DailyServiceImpl implements DailyService {

	/** The daily repository. */
	@Autowired
	private DailyRepository dailyRepository;

	/** The adjustment repository. */
	@Autowired
	private AdjustmentRepository adjustmentRepository;

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;

	/** The sale manager. */
	@Autowired
	private SaleManager saleManager;

	/** The otherconceptsrepository. */
	@Autowired
	private OtherConceptsRepository otherconceptsrepository;

	/** The shoppings repository. */
	@Autowired
	private ShoppingsRepository shoppingsRepository;

	/** The renovations repository. */
	@Autowired
	private RenovationsRepository renovationsRepository;

	/** The cancel sale repository. */
	@Autowired
	private CancelSaleRepository cancelSaleRepository;

	@Autowired
	private PayrollRepository payrollRepository;

	@Autowired
	private EntryMoneyRepository entryMoneyRepository;

	@Autowired
	private BatteriesRepository batteriesRepository;

	@Autowired
	private StrapsRepository strapsRepository;

	@Autowired
	private RentalsRepository rentalsRepository;

	/** The holidays manager. */
	@Autowired
	private HolidaysManager holidaysManager;

	@Autowired
	private DiscountsRepository discountsRepository;

	@Autowired
	private RecordingRepository recordingRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public Daily getDaily(Date date, PlaceEntity place, String ipaddress) {
		Daily daily = new Daily();
		// lo primero que voy hacer es mirar si el día es festivo, con lo cual
		// no calculo nada
		if (!holidaysManager.isHoliday(date, place)) {
			// busco el parte de hoy si ya está calculado
			DailyEntity dEntity = dailyRepository.findByPlaceAndDailydate(place, date);
			List<AdjustmentEntity> adjustments = adjustmentRepository.findByCarrydateAndPlace(date, place);
			List<AdjustmentEntity> adjustmentswork = adjustmentRepository
					.findByCreationdateAndPlaceAndAmountworkNotNullAndWorkFalse(date, place);
			List<PawnEntity> newpawns = pawnsRepository.findByCreationdateAndPlace(date, place);
			List<PawnEntity> retiredpawns = pawnsRepository.findByDateretiredAndPlace(date, place);
			List<SaleEntity> sales = saleManager.searchByCreationDateAndPlace(date, place);
			List<OtherConceptEntity> otherconcepts = otherconceptsrepository.findByCreationdateAndPlace(date, place);
			List<ShoppingEntity> shoppings = shoppingsRepository.findByCreationdateAndPlace(date, place);
			List<RenovationEntity> renovations = renovationsRepository.findByCreationdateAndPlace(date, place);
			List<CancelSaleEntity> cancels = cancelSaleRepository.findByCreationdateAndPlace(date, place);
			List<PayrollEntity> payrolls = payrollRepository.findByCreationdateAndPlace(date, place);
			List<EntryMoneyEntity> entriesMoney = entryMoneyRepository.findByCreationdateAndPlace(date, place);
			List<BatteryEntity> batteriesEntity = batteriesRepository.findByCreationdateAndPlace(date, place);
			List<StrapEntity> strapsEntity = strapsRepository.findByCreationdateAndPlace(date, place);
			List<RentalEntity> rentalsEntity = rentalsRepository.findByCreationdateAndPlace(date, place);
			List<DiscountEntity> discounts = discountsRepository.findByCreationdateAndPlace(date, place);
			List<RecordingEntity> recordings = recordingRepository.findByCreationdateAndPlace(date, place);
			int numoperations = 0;
			BigDecimal discountsamount = BigDecimal.ZERO, finalamount = BigDecimal.ZERO,
					adjustmentsworkamount = BigDecimal.ZERO, renovationsamount = BigDecimal.ZERO,
					otherconceptsamount = BigDecimal.ZERO, newpawnsamount = BigDecimal.ZERO,
					retiredpawnsamount = BigDecimal.ZERO, shoppingsamount = BigDecimal.ZERO,
					recordingsAmount = BigDecimal.ZERO, adjusmentsamount = BigDecimal.ZERO,
					rentalsamount = BigDecimal.ZERO, cancelsamount = BigDecimal.ZERO, batteriesamount = BigDecimal.ZERO,
					strapsamount = BigDecimal.ZERO, entriesmoneyamount = BigDecimal.ZERO;
			double salesamount = 0, payrollamount = 0;
			if (adjustments != null && !adjustments.isEmpty()) {
				List<Adjustment> ladjustments = new ArrayList<Adjustment>();
				Iterator<AdjustmentEntity> iadjustments = adjustments.iterator();
				AdjustmentEntity adjustment;
				while (iadjustments.hasNext()) {
					adjustment = iadjustments.next();
					ladjustments.add(mapper.map(adjustment, Adjustment.class));
					numoperations = numoperations + 1;
					if (adjustment.getPayment().getIdpayment().equals(Constants.EFECTIVO)) {
						adjusmentsamount = adjusmentsamount.add(adjustment.getAmount());
					}
				}
				daily.setAdjustments(ladjustments);
			}
			if (adjustmentswork != null && !adjustmentswork.isEmpty()) {
				Iterator<AdjustmentEntity> iadjustments = adjustmentswork.iterator();
				AdjustmentEntity adjustment;
				BigDecimal amountwork;
				List<Adjustment> ladjustmentsw = new ArrayList<Adjustment>();
				while (iadjustments.hasNext()) {
					adjustment = iadjustments.next();
					amountwork = adjustment.getAmountwork();
					ladjustmentsw.add(mapper.map(adjustment, Adjustment.class));
					if (adjustment.getPaymentwork().getIdpayment().equals(Constants.EFECTIVO)) {
						adjustmentsworkamount = adjustmentsworkamount.subtract(amountwork);
					}
					numoperations = numoperations + 1;
				}
				daily.setAdjustmentswork(ladjustmentsw);
			}
			if (newpawns != null && !newpawns.isEmpty()) {
				Iterator<PawnEntity> ipawns = newpawns.iterator();
				List<Pawn> lpawns = new ArrayList<Pawn>();
				PawnEntity pawnEntity;
				Pawn pawn;
				while (ipawns.hasNext()) {
					pawnEntity = ipawns.next();
					pawn = mapper.map(pawnEntity, Pawn.class);
					newpawnsamount = newpawnsamount.subtract(pawnEntity.getAmount());
					lpawns.add(pawn);
					numoperations = numoperations + 1;
				}
				daily.setNewpawns(lpawns);
			}
			if (retiredpawns != null && !retiredpawns.isEmpty()) {
				Iterator<PawnEntity> ipawns = retiredpawns.iterator();
				List<Pawn> lpawns = new ArrayList<Pawn>();
				PawnEntity pawnEntity;
				BigDecimal amount;
				Pawn pawn;
				while (ipawns.hasNext()) {
					pawnEntity = ipawns.next();
					pawn = mapper.map(pawnEntity, Pawn.class);
					amount = pawnEntity.getAmount();
					BigDecimal percent = pawnEntity.getPercent();
					Integer months = pawnEntity.getMonths();
					if (months != null && months.intValue() > 0
							&& pawnEntity.getPlace().getIdplace().equals(Constants.STODOMINGO)) {
						percent = percent.multiply(new BigDecimal(months));
					}
					BigDecimal percentamount = amount.multiply(percent).divide(new BigDecimal(100));
					amount = amount.add(percentamount);
					retiredpawnsamount = retiredpawnsamount.add(amount);
					pawn.setAmount(amount);
					lpawns.add(pawn);
					numoperations = numoperations + 1;
				}
				daily.setRetiredpawns(lpawns);
			}
			if (sales != null && !sales.isEmpty()) {
				Iterator<SaleEntity> isales = sales.iterator();
				List<Sale> lsales = new ArrayList<Sale>();
				SaleEntity sale;
				List<SalesPayments> spayments = new ArrayList<SalesPayments>();
				Iterator<SalesPayments> ipayments;
				PaymentEntity payment;
				SalesPayments sp;
				Sale saleView;
				String payments;
				while (isales.hasNext()) {
					sale = isales.next();
					spayments = sale.getSpayments();
					ipayments = spayments.iterator();
					payments = "";
					while (ipayments.hasNext()) {
						sp = ipayments.next();
						payment = sp.getPay();
						if (payment.getIdpayment().equals(Constants.EFECTIVO)) {
							salesamount += sp.getAmount().doubleValue();
						}
						payments = payments.concat(payment.getName()).concat(" ");
					}
					saleView = mapper.map(sale, Sale.class);
					saleView.setPayments(payments);
					lsales.add(saleView);
					numoperations = numoperations + 1;
				}
				daily.setSales(lsales);
			}
			if (otherconcepts != null && !otherconcepts.isEmpty()) {
				Iterator<OtherConceptEntity> iotherconcepts = otherconcepts.iterator();
				List<OtherConcept> lotherconcepts = new ArrayList<OtherConcept>();
				OtherConceptEntity otherconcept;
				while (iotherconcepts.hasNext()) {
					otherconcept = iotherconcepts.next();
					lotherconcepts.add(mapper.map(otherconcept, OtherConcept.class));
					otherconceptsamount = otherconceptsamount.add(otherconcept.getAmount());
					numoperations = numoperations + 1;
				}
				daily.setOthersconcepts(lotherconcepts);
			}
			if (shoppings != null && !shoppings.isEmpty()) {
				Iterator<ShoppingEntity> ishoppings = shoppings.iterator();
				List<Shopping> lshoppings = new ArrayList<Shopping>();
				ShoppingEntity shopping;
				List<PaymentShopEntity> paymentshop;
				Iterator<PaymentShopEntity> ipaymentshop;
				Shopping shopView;
				String payments;
				while (ishoppings.hasNext()) {
					shopping = ishoppings.next();
					paymentshop = shopping.getSpayments();
					ipaymentshop = paymentshop.iterator();
					payments = "";
					while (ipaymentshop.hasNext()) {
						PaymentShopEntity paymentShopEntity = ipaymentshop.next();
						if (paymentShopEntity.getPayment().getIdpayment().equals(Constants.EFECTIVO)) {
							shoppingsamount = shoppingsamount.subtract(paymentShopEntity.getAmount());
						}
						payments = payments.concat(paymentShopEntity.getPayment().getName()).concat(" ");
					}
					shopView = mapper.map(shopping, Shopping.class);
					shopView.setPayments(payments);
					lshoppings.add(shopView);
					numoperations = numoperations + 1;
				}
				daily.setShoppings(lshoppings);
			}
			if (renovations != null && !renovations.isEmpty()) {
				Iterator<RenovationEntity> irenovations = renovations.iterator();
				List<Renovation> lrenovations = new ArrayList<Renovation>();
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
					 * if ((renovationamount - ((int) renovationamount) != 0)) {
					 * renovationamount += 1; }
					 **/
					re.setRenovationamount(renovationamount);
					lrenovations.add(re);
					renovationsamount = renovationsamount.add(re.getRenovationamount());
					numoperations = numoperations + 1;
				}
				daily.setRenovations(lrenovations);
			}
			if (cancels != null && !cancels.isEmpty()) {
				Iterator<CancelSaleEntity> icancels = cancels.iterator();
				List<CancelSale> lcancels = new ArrayList<CancelSale>();
				List<CancelSalePaymentEntity> lcancelpayments = new ArrayList<CancelSalePaymentEntity>();
				CancelSaleEntity cse;
				CancelSale cs;
				Iterator<CancelSalePaymentEntity> ilcancelpayments;
				CancelSalePaymentEntity csp;
				while (icancels.hasNext()) {
					cse = icancels.next();
					cs = mapper.map(cse, CancelSale.class);
					lcancelpayments = cse.getSpayments();
					ilcancelpayments = lcancelpayments.iterator();
					while (ilcancelpayments.hasNext()) {
						csp = ilcancelpayments.next();
						if (csp.getPay().getIdpayment().equals(Constants.EFECTIVO)) {
							cancelsamount = cancelsamount.subtract(csp.getAmount());
						}
					}
					lcancels.add(cs);
					numoperations = numoperations + 1;
				}
				daily.setCancelSales(lcancels);
			}
			if (payrolls != null && !payrolls.isEmpty()) {
				Iterator<PayrollEntity> ipayroll = payrolls.iterator();
				PayrollEntity pre;
				Payroll pr;
				while (ipayroll.hasNext()) {
					pre = ipayroll.next();
					pr = mapper.map(pre, Payroll.class);
					payrollamount -= pr.getAmount();
					daily.setPayroll(pr);
					numoperations = numoperations + 1;
				}
			}
			if (entriesMoney != null && !entriesMoney.isEmpty()) {
				Iterator<EntryMoneyEntity> ientriesmoney = entriesMoney.iterator();
				while (ientriesmoney.hasNext()) {
					entriesmoneyamount = entriesmoneyamount.add(ientriesmoney.next().getAmount());
					numoperations = numoperations + 1;
				}
				daily.setEntriesmoney(entriesMoney);
			}
			if (batteriesEntity != null && !batteriesEntity.isEmpty()) {
				Iterator<BatteryEntity> ibatteries = batteriesEntity.iterator();
				BatteryEntity be;
				List<BatteryEntity> batteries = new ArrayList<BatteryEntity>();
				while (ibatteries.hasNext()) {
					be = ibatteries.next();
					if (be.getPayment().getIdpayment().equals(Constants.EFECTIVO)) {
						batteriesamount = batteriesamount.add(be.getAmount());
					}
					batteries.add(be);
					numoperations = numoperations + 1;
				}
				daily.setBatteries(batteries);
			}
			if (strapsEntity != null && !strapsEntity.isEmpty()) {
				Iterator<StrapEntity> ibatteries = strapsEntity.iterator();
				StrapEntity se;
				while (ibatteries.hasNext()) {
					se = ibatteries.next();
					if (se.getPayment().getIdpayment().equals(Constants.EFECTIVO)) {
						strapsamount = strapsamount.add(se.getAmount());
					}
					numoperations = numoperations + 1;
				}
				daily.setStraps(strapsEntity);
			}
			if (rentalsEntity != null && !rentalsEntity.isEmpty()) {
				Iterator<RentalEntity> irentals = rentalsEntity.iterator();
				RentalEntity re;
				Rental r;
				List<Rental> rentals = new ArrayList<Rental>();
				while (irentals.hasNext()) {
					re = irentals.next();
					r = mapper.map(re, Rental.class);
					r.setRentaldate(String.valueOf(re.getIdrental()).substring(0, 5));
					rentalsamount = rentalsamount.subtract(r.getAmount());
					rentals.add(r);
					numoperations = numoperations + 1;
				}
				daily.setRentals(rentals);
			}
			if (discounts != null && !discounts.isEmpty()) {
				Iterator<DiscountEntity> idiscounts = discounts.iterator();
				DiscountEntity discount;
				List<Discount> ldiscounts = new ArrayList<Discount>();
				while (idiscounts.hasNext()) {
					discount = idiscounts.next();
					numoperations = numoperations + 1;
					ldiscounts.add(mapper.map(discount, Discount.class));
					if (discount.getNumsalecancel() != null && discount.getNumsalechange() != null) {
						discountsamount = discountsamount.add(discount.getDiscount());
					}
				}
				daily.setDiscounts(ldiscounts);
			}
			if (recordings != null && !recordings.isEmpty()) {
				Iterator<RecordingEntity> irecordings = recordings.iterator();
				RecordingEntity recording;
				while (irecordings.hasNext()) {
					recording = irecordings.next();
					numoperations = numoperations + 1;
					if (recording.getPay().getIdpayment().equals(Constants.EFECTIVO)) {
						recordingsAmount = recordingsAmount.add(recording.getAmount());
					}
				}
				daily.setRecordings(recordings);
			}
			BigDecimal previousamount = BigDecimal.ZERO;
			if (dEntity == null) {
				// tengo que sacar el importe del día anterior para calcularlo
				DailyEntity previousdaily = dailyRepository.findFirstByPlaceOrderByIddailyDesc(place);
				previousamount = previousdaily.getFinalamount();
				dEntity = new DailyEntity();
				dEntity.setDailydate(date);
				dEntity.setPlace(place);
			} else {
				Date previousday = holidaysManager.getPreviousDay(date, place);
				DailyEntity dailyprevious = dailyRepository.findByPlaceAndDailydate(place, previousday);
				if (dailyprevious == null) {
					return daily;
				} else {
					previousamount = dailyprevious.getFinalamount();
				}
			}
			finalamount = previousamount.add(adjusmentsamount).add(adjustmentsworkamount).add(renovationsamount)
					.add(new BigDecimal(salesamount)).add(shoppingsamount).add(retiredpawnsamount)
					.add(otherconceptsamount).add(newpawnsamount).add(cancelsamount).add(new BigDecimal(payrollamount))
					.add(entriesmoneyamount).add(batteriesamount).add(strapsamount).add(rentalsamount)
					.add(discountsamount).add(recordingsAmount);
			dEntity.setFinalamount(finalamount);
			dEntity.setIpaddress(ipaddress);
			daily.setFinalamount(finalamount);
			daily.setNumoperations(numoperations);
			dailyRepository.save(dEntity);
		}
		return daily;
	}

	public void calculateDailies(Date date, PlaceEntity place) {
		Calendar calendar = Calendar.getInstance();
		while (date.compareTo(new Date()) < 0) {
			getDaily(date, place, "");
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			date = calendar.getTime();
		}
	}
}
