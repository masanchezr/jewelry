package com.atmj.jsboot.services.dailies;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.AdjustmentEntity;
import com.atmj.jsboot.dbaccess.entities.CancelSaleEntity;
import com.atmj.jsboot.dbaccess.entities.CancelSalePaymentEntity;
import com.atmj.jsboot.dbaccess.entities.DailyEntity;
import com.atmj.jsboot.dbaccess.entities.DiscountEntity;
import com.atmj.jsboot.dbaccess.entities.EntryMoneyEntity;
import com.atmj.jsboot.dbaccess.entities.InstallmentEntity;
import com.atmj.jsboot.dbaccess.entities.OtherConceptEntity;
import com.atmj.jsboot.dbaccess.entities.OtherSaleEntity;
import com.atmj.jsboot.dbaccess.entities.PawnEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentShopEntity;
import com.atmj.jsboot.dbaccess.entities.PayrollEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceUserEntity;
import com.atmj.jsboot.dbaccess.entities.RenovationEntity;
import com.atmj.jsboot.dbaccess.entities.RentalEntity;
import com.atmj.jsboot.dbaccess.entities.SaleEntity;
import com.atmj.jsboot.dbaccess.entities.SalePostponedEntity;
import com.atmj.jsboot.dbaccess.entities.SalesPayments;
import com.atmj.jsboot.dbaccess.entities.ShoppingEntity;
import com.atmj.jsboot.dbaccess.entities.WorkEntity;
import com.atmj.jsboot.dbaccess.managers.HolidaysManager;
import com.atmj.jsboot.dbaccess.managers.SaleManager;
import com.atmj.jsboot.dbaccess.repositories.AdjustmentRepository;
import com.atmj.jsboot.dbaccess.repositories.CancelSaleRepository;
import com.atmj.jsboot.dbaccess.repositories.DailyRepository;
import com.atmj.jsboot.dbaccess.repositories.DiscountsRepository;
import com.atmj.jsboot.dbaccess.repositories.EntryMoneyRepository;
import com.atmj.jsboot.dbaccess.repositories.OtherConceptsRepository;
import com.atmj.jsboot.dbaccess.repositories.OtherSaleRepository;
import com.atmj.jsboot.dbaccess.repositories.PawnsRepository;
import com.atmj.jsboot.dbaccess.repositories.PayrollRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.RenovationsRepository;
import com.atmj.jsboot.dbaccess.repositories.RentalsRepository;
import com.atmj.jsboot.dbaccess.repositories.SalesPostponedRepository;
import com.atmj.jsboot.dbaccess.repositories.ShoppingsRepository;
import com.atmj.jsboot.dbaccess.repositories.WorksRepository;
import com.atmj.jsboot.forms.Sale;
import com.atmj.jsboot.forms.SalePostPoned;
import com.atmj.jsboot.services.adjustments.Adjustment;
import com.atmj.jsboot.services.converters.DiscountEntityConverter;
import com.atmj.jsboot.services.converters.PawnEntityConverter;
import com.atmj.jsboot.services.converters.SalePostPonedEntityConverter;
import com.atmj.jsboot.services.converters.ShoppingEntityConverter;
import com.atmj.jsboot.services.discounts.Discount;
import com.atmj.jsboot.services.otherconcepts.OtherConcept;
import com.atmj.jsboot.services.pawns.Pawn;
import com.atmj.jsboot.services.pawns.Renovation;
import com.atmj.jsboot.services.rentals.Rental;
import com.atmj.jsboot.services.sales.CancelSale;
import com.atmj.jsboot.services.shoppings.Shopping;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.date.DateUtil;

/**
 * The Class DailyServiceImpl.
 */
@Service
public class DailyServiceImpl implements DailyService {
	private final DailyRepository dailyRepository;
	private final AdjustmentRepository adjustmentRepository;
	private final PawnsRepository pawnsRepository;
	private final SaleManager saleManager;
	private final OtherConceptsRepository otherconceptsrepository;
	private final ShoppingsRepository shoppingsRepository;
	private final RenovationsRepository renovationsRepository;
	private final CancelSaleRepository cancelSaleRepository;
	private final PayrollRepository payrollRepository;
	private final EntryMoneyRepository entryMoneyRepository;
	private final OtherSaleRepository otherSaleRepository;
	private final RentalsRepository rentalsRepository;
	private final HolidaysManager holidaysManager;
	private final DiscountsRepository discountsRepository;
	private final SalesPostponedRepository salespostponedrepository;
	private final PlaceUserRepository placeUserRepository;
	private final WorksRepository worksRepository;
	private final ModelMapper mapper;
	private final DiscountEntityConverter converterDiscount;
	private final PawnEntityConverter pawnConverter;
	private final SalePostPonedEntityConverter salePostPonedConverter;
	private final ShoppingEntityConverter shopConverter;

	public DailyServiceImpl(DailyRepository dailyRepository, AdjustmentRepository adjustmentRepository,
			PawnsRepository pawnsRepository, SaleManager saleManager, OtherConceptsRepository otherconceptsrepository,
			ShoppingsRepository shoppingsRepository, RenovationsRepository renovationsRepository,
			CancelSaleRepository cancelSaleRepository, PayrollRepository payrollRepository,
			EntryMoneyRepository entryMoneyRepository, OtherSaleRepository otherSaleRepository,
			RentalsRepository rentalsRepository, HolidaysManager holidaysManager,
			DiscountsRepository discountsRepository, SalesPostponedRepository salespostponedrepository,
			PlaceUserRepository placeUserRepository, WorksRepository worksRepository, ModelMapper mapper,
			DiscountEntityConverter converterDiscount, PawnEntityConverter pawnConverter,
			SalePostPonedEntityConverter salePostPonedConverter, ShoppingEntityConverter shopConverter) {
		this.dailyRepository = dailyRepository;
		this.adjustmentRepository = adjustmentRepository;
		this.pawnsRepository = pawnsRepository;
		this.saleManager = saleManager;
		this.otherconceptsrepository = otherconceptsrepository;
		this.shoppingsRepository = shoppingsRepository;
		this.renovationsRepository = renovationsRepository;
		this.cancelSaleRepository = cancelSaleRepository;
		this.payrollRepository = payrollRepository;
		this.entryMoneyRepository = entryMoneyRepository;
		this.otherSaleRepository = otherSaleRepository;
		this.rentalsRepository = rentalsRepository;
		this.holidaysManager = holidaysManager;
		this.discountsRepository = discountsRepository;
		this.salespostponedrepository = salespostponedrepository;
		this.placeUserRepository = placeUserRepository;
		this.worksRepository = worksRepository;
		this.mapper = mapper;
		this.converterDiscount = converterDiscount;
		this.pawnConverter = pawnConverter;
		this.salePostPonedConverter = salePostPonedConverter;
		this.shopConverter = shopConverter;

	}

	@Override
	public Daily getDaily(Date date, PlaceEntity place, String ipaddress) {
		Daily daily = new Daily();
		// lo primero que voy hacer es mirar si el día es festivo, con lo cual
		// no calculo nada
		if (!holidaysManager.isHoliday(date, place)) {
			daily.setSdate(DateUtil.getStringDateddMMyyyy(date));
			// busco el parte de hoy si ya está calculado
			DailyEntity dEntity = dailyRepository.findByPlaceAndDailydate(place, date);
			BigDecimal finalamount;
			BigDecimal previousamount;
			BigDecimal discountsamount = getDiscountsAmount(date, place, daily);
			BigDecimal adjustmentsworkamount = getAdjustmentsWorkAmount(date, place, daily);
			BigDecimal renovationsamount = getRenovationsAmount(date, place, daily);
			BigDecimal otherconceptsamount = getOthersConceptsAmount(date, place, daily);
			BigDecimal newpawnsamount = getNewPawnsAmount(date, place, daily);
			BigDecimal retiredpawnsamount = getRetiredPawnsAmount(date, place, daily);
			BigDecimal shoppingsamount = getShoppingsAmount(date, place, daily);
			BigDecimal adjusmentsamount = getAdjustmentsAmount(date, place, daily);
			BigDecimal rentalsamount = getRentalsAmount(date, place, daily);
			BigDecimal cancelsamount = getCancelsAmount(date, place, daily);
			BigDecimal entriesmoneyamount = getEntriesMoneyAmount(date, place, daily);
			BigDecimal othersalesamount = getOtherSalesAmount(date, place, daily);
			BigDecimal salesamount = getSalesAmount(date, place, daily);
			BigDecimal payrollamount = getPayrollAmount(date, place, daily);
			BigDecimal salespostamount = getSalesPostAmount(date, place, daily);
			if (dEntity == null) {
				DailyEntity previousdaily = null;
				Date previousday = date;
				while (previousdaily == null) {
					previousday = DateUtil.addDays(previousday, -1);
					previousdaily = dailyRepository.findByPlaceAndDailydate(place, previousday);
				}
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
					.add(salesamount).add(shoppingsamount).add(retiredpawnsamount).add(otherconceptsamount)
					.add(newpawnsamount).add(cancelsamount).add(payrollamount).add(entriesmoneyamount)
					.add(rentalsamount).add(discountsamount).add(salespostamount.add(othersalesamount));
			dEntity.setFinalamount(finalamount);
			dEntity.setIpaddress(ipaddress);
			daily.setFinalamount(finalamount);
			dailyRepository.save(dEntity);
		}
		return daily;
	}

	private BigDecimal getOtherSalesAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal othersalesamount = BigDecimal.ZERO;
		List<OtherSaleEntity> othersales = otherSaleRepository.findByCreationdateAndPlace(date, place);
		if (othersales != null && !othersales.isEmpty()) {
			Iterator<OtherSaleEntity> iothersales = othersales.iterator();
			OtherSaleEntity othersale;
			while (iothersales.hasNext()) {
				othersale = iothersales.next();
				if (othersale.getPay().getIdpayment().equals(Constants.EFECTIVO)) {
					othersalesamount = othersalesamount.add(othersale.getAmount());
				}
			}
			daily.setOthersales(othersales);
			daily.setNumoperations(daily.getNumoperations() + othersales.size());
		}
		return othersalesamount;
	}

	private BigDecimal getDiscountsAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal discountsamount = BigDecimal.ZERO;
		List<DiscountEntity> discounts = discountsRepository.findByCreationdateAndPlace(date, place);
		if (discounts != null && !discounts.isEmpty()) {
			Iterator<DiscountEntity> idiscounts = discounts.iterator();
			DiscountEntity discount;
			List<Discount> ldiscounts = new ArrayList<>();
			while (idiscounts.hasNext()) {
				discount = idiscounts.next();
				ldiscounts.add(converterDiscount.convertToEntity(discount));
				if (discount.getNumsalecancel() != null && discount.getNumsalechange() != null) {
					discountsamount = discountsamount.add(discount.getDiscount());
				}
			}
			daily.setDiscounts(ldiscounts);
			daily.setNumoperations(daily.getNumoperations() + discounts.size());
		}
		return discountsamount;
	}

	private BigDecimal getRentalsAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal rentalsamount = BigDecimal.ZERO;
		List<RentalEntity> rentalsEntity = rentalsRepository.findByCreationdateAndPlace(date, place);
		if (rentalsEntity != null && !rentalsEntity.isEmpty()) {
			Iterator<RentalEntity> irentals = rentalsEntity.iterator();
			RentalEntity re;
			Rental r;
			List<Rental> rentals = new ArrayList<>();
			while (irentals.hasNext()) {
				re = irentals.next();
				r = mapper.map(re, Rental.class);
				r.setRentaldate(String.valueOf(re.getIdrental()).substring(0, 5));
				rentalsamount = rentalsamount.subtract(re.getAmount());
				rentals.add(r);
			}
			daily.setRentals(rentals);
			daily.setNumoperations(daily.getNumoperations() + rentalsEntity.size());
		}
		return rentalsamount;
	}

	private BigDecimal getEntriesMoneyAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal entriesmoneyamount = BigDecimal.ZERO;
		List<EntryMoneyEntity> entriesMoney = entryMoneyRepository.findByCreationdateAndPlace(date, place);
		if (entriesMoney != null && !entriesMoney.isEmpty()) {
			Iterator<EntryMoneyEntity> ientriesmoney = entriesMoney.iterator();
			while (ientriesmoney.hasNext()) {
				entriesmoneyamount = entriesmoneyamount.add(ientriesmoney.next().getAmount());
			}
			daily.setEntriesmoney(entriesMoney);
			daily.setNumoperations(daily.getNumoperations() + entriesMoney.size());
		}
		return entriesmoneyamount;
	}

	private BigDecimal getPayrollAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal payrollamount = BigDecimal.ZERO;
		List<PlaceUserEntity> placeUsers = placeUserRepository.findByPlace(place);
		List<PayrollEntity> payrollsview = new ArrayList<>();
		if (placeUsers != null) {
			Iterator<PlaceUserEntity> iplaceUsers = placeUsers.iterator();
			while (iplaceUsers.hasNext()) {
				List<PayrollEntity> payrolls = payrollRepository.findByCreationdateAndUser(date,
						iplaceUsers.next().getUser());
				if (payrolls != null && !payrolls.isEmpty()) {
					Iterator<PayrollEntity> ipayroll = payrolls.iterator();
					PayrollEntity pre;
					while (ipayroll.hasNext()) {
						pre = ipayroll.next();
						payrollamount = payrollamount.subtract(pre.getAmount());
						payrollsview.add(pre);
					}
					daily.setNumoperations(daily.getNumoperations() + payrolls.size());
				}
			}
			daily.setPayroll(payrollsview);
		}
		return payrollamount;
	}

	private BigDecimal getCancelsAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal cancelsamount = BigDecimal.ZERO;
		List<CancelSaleEntity> cancels = cancelSaleRepository.findByCreationdateAndPlace(date, place);
		if (cancels != null && !cancels.isEmpty()) {
			Iterator<CancelSaleEntity> icancels = cancels.iterator();
			List<CancelSale> lcancels = new ArrayList<>();
			List<CancelSalePaymentEntity> lcancelpayments;
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
			}
			daily.setCancelsales(lcancels);
			daily.setNumoperations(daily.getNumoperations() + lcancels.size());
		}
		return cancelsamount;
	}

	private BigDecimal getRenovationsAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal renovationsamount = BigDecimal.ZERO;
		List<RenovationEntity> renovations = renovationsRepository.findByCreationdateAndPlace(date, place);
		if (renovations != null && !renovations.isEmpty()) {
			Iterator<RenovationEntity> irenovations = renovations.iterator();
			List<Renovation> lrenovations = new ArrayList<>();
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
				re.setRenovationamount(renovationamount);
				lrenovations.add(re);
				renovationsamount = renovationsamount.add(re.getRenovationamount());
			}
			daily.setRenovations(lrenovations);
			daily.setNumoperations(daily.getNumoperations() + renovations.size());
		}
		return renovationsamount;
	}

	private BigDecimal getShoppingsAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal shoppingsamount = BigDecimal.ZERO;
		List<ShoppingEntity> shoppings = shoppingsRepository.findByCreationdateAndPlace(date, place);
		if (shoppings != null && !shoppings.isEmpty()) {
			Iterator<ShoppingEntity> ishoppings = shoppings.iterator();
			List<Shopping> lshoppings = new ArrayList<>();
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
					String spayment = paymentShopEntity.getPayment().getName();
					if (paymentShopEntity.getPayment().getIdpayment().equals(Constants.EFECTIVO)) {
						shoppingsamount = shoppingsamount.subtract(paymentShopEntity.getAmount());
					}
					if (spayment != null) {
						payments = payments.concat(paymentShopEntity.getPayment().getName()).concat(" ");
					}
				}
				shopView = shopConverter.convertToShopping(shopping);
				shopView.setPayments(payments);
				lshoppings.add(shopView);
			}
			daily.setShoppings(lshoppings);
			daily.setNumoperations(daily.getNumoperations() + shoppings.size());
		}
		return shoppingsamount;
	}

	private BigDecimal getOthersConceptsAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal otherconceptsamount = BigDecimal.ZERO;
		List<OtherConceptEntity> otherconcepts = otherconceptsrepository.findByCreationdateAndPlace(date, place);
		if (otherconcepts != null && !otherconcepts.isEmpty()) {
			Iterator<OtherConceptEntity> iotherconcepts = otherconcepts.iterator();
			List<OtherConcept> lotherconcepts = new ArrayList<>();
			OtherConceptEntity otherconcept;
			while (iotherconcepts.hasNext()) {
				otherconcept = iotherconcepts.next();
				lotherconcepts.add(mapper.map(otherconcept, OtherConcept.class));
				otherconceptsamount = otherconceptsamount.add(otherconcept.getAmount());
			}
			daily.setOthersconcepts(lotherconcepts);
			daily.setNumoperations(daily.getNumoperations() + otherconcepts.size());
		}
		return otherconceptsamount;
	}

	private BigDecimal getSalesPostAmount(Date date, PlaceEntity place, Daily daily) {
		List<SalePostponedEntity> salespost = salespostponedrepository.findByDateretiredAndPlaceAndTimeoutFalse(date,
				place);
		BigDecimal salespostamount = BigDecimal.ZERO;
		if (salespost != null && !salespost.isEmpty()) {
			Iterator<SalePostponedEntity> isalespost = salespost.iterator();
			List<SalePostPoned> lsalespost = new ArrayList<>();
			SalePostponedEntity sale;
			List<InstallmentEntity> spayments;
			Iterator<InstallmentEntity> ipayments;
			PaymentEntity payment;
			InstallmentEntity sp;
			SalePostPoned saleView;
			String payments;
			while (isalespost.hasNext()) {
				sale = isalespost.next();
				spayments = sale.getSpayments();
				ipayments = spayments.iterator();
				payments = "";
				while (ipayments.hasNext()) {
					sp = ipayments.next();
					payment = sp.getPay();
					if (payment.getIdpayment().equals(Constants.EFECTIVO)) {
						salespostamount = salespostamount.add(sp.getAmount());
					}
					payments = payments.concat(payment.getName()).concat(" ");
				}
				saleView = salePostPonedConverter.convertToDTO(sale);
				saleView.setPayments(payments);
				lsalespost.add(saleView);
			}
			daily.setLsalespost(lsalespost);
			daily.setNumoperations(daily.getNumoperations() + salespost.size());
		}
		return salespostamount;
	}

	private BigDecimal getSalesAmount(Date date, PlaceEntity place, Daily daily) {
		List<SaleEntity> sales = saleManager.searchByCreationDateAndPlace(date, place);
		BigDecimal salesamount = BigDecimal.ZERO;
		if (sales != null && !sales.isEmpty()) {
			Iterator<SaleEntity> isales = sales.iterator();
			List<Sale> lsales = new ArrayList<>();
			SaleEntity sale;
			List<SalesPayments> spayments;
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
						salesamount = salesamount.add(sp.getAmount());
					}
					payments = payments.concat(payment.getName()).concat(" ");
				}
				saleView = mapper.map(sale, Sale.class);
				saleView.setPayments(payments);
				lsales.add(saleView);
			}
			daily.setSales(lsales);
			daily.setNumoperations(daily.getNumoperations() + sales.size());
		}
		return salesamount;
	}

	private BigDecimal getRetiredPawnsAmount(Date date, PlaceEntity place, Daily daily) {
		List<PawnEntity> retiredpawns = pawnsRepository.findByDateretiredAndPlace(date, place);
		BigDecimal retiredpawnsamount = BigDecimal.ZERO;
		if (retiredpawns != null && !retiredpawns.isEmpty()) {
			Iterator<PawnEntity> ipawns = retiredpawns.iterator();
			List<Pawn> lpawns = new ArrayList<>();
			PawnEntity pawnEntity;
			BigDecimal amount;
			Pawn pawn;
			while (ipawns.hasNext()) {
				pawnEntity = ipawns.next();
				pawn = pawnConverter.convertToPawn(pawnEntity);
				amount = pawnEntity.getAmount();
				BigDecimal percent = pawnEntity.getPercent();
				Integer months = pawnEntity.getMonths();
				if (months != null && pawnEntity.getPlace().getIdplace().equals(Constants.STODOMINGO)) {
					percent = percent.multiply(BigDecimal.valueOf(months));
				}
				if (percent.compareTo(BigDecimal.ZERO) > 0) {
					BigDecimal percentamount = amount.multiply(percent).divide(BigDecimal.valueOf(100));
					amount = amount.add(percentamount);
				}
				retiredpawnsamount = retiredpawnsamount.add(amount);
				pawn.setAmount(amount.toString());
				lpawns.add(pawn);
			}
			daily.setRetiredpawns(lpawns);
			daily.setNumoperations(daily.getNumoperations() + retiredpawns.size());
		}
		return retiredpawnsamount;
	}

	private BigDecimal getNewPawnsAmount(Date date, PlaceEntity place, Daily daily) {
		List<PawnEntity> newpawns = pawnsRepository.findByCreationdateAndPlace(date, place);
		BigDecimal newpawnsamount = BigDecimal.ZERO;
		if (newpawns != null && !newpawns.isEmpty()) {
			Iterator<PawnEntity> ipawns = newpawns.iterator();
			List<Pawn> lpawns = new ArrayList<>();
			PawnEntity pawnEntity;
			Pawn pawn;
			while (ipawns.hasNext()) {
				pawnEntity = ipawns.next();
				pawn = pawnConverter.convertToPawn(pawnEntity);
				newpawnsamount = newpawnsamount.subtract(pawnEntity.getAmount());
				lpawns.add(pawn);
			}
			daily.setNewpawns(lpawns);
			daily.setNumoperations(daily.getNumoperations() + newpawns.size());
		}
		return newpawnsamount;
	}

	private BigDecimal getAdjustmentsWorkAmount(Date date, PlaceEntity place, Daily daily) {
		BigDecimal adjustmentsworkamount = BigDecimal.ZERO;
		List<WorkEntity> adjustmentswork = worksRepository.findByCreationdateAndPlace(date, place);
		if (adjustmentswork != null && !adjustmentswork.isEmpty()) {
			Iterator<WorkEntity> iadjustments = adjustmentswork.iterator();
			WorkEntity adjustment;
			BigDecimal amountwork;
			List<Adjustment> ladjustmentsw = new ArrayList<>();
			Adjustment a;
			while (iadjustments.hasNext()) {
				adjustment = iadjustments.next();
				amountwork = adjustment.getAmount();
				a = mapper.map(adjustment, Adjustment.class);
				a.setIdadjustment(adjustment.getIdwork());
				ladjustmentsw.add(a);
				if (adjustment.getPayment().getIdpayment().equals(Constants.EFECTIVO)) {
					adjustmentsworkamount = adjustmentsworkamount.subtract(amountwork);
				}
			}
			daily.setAdjustmentswork(ladjustmentsw);
			daily.setNumoperations(daily.getNumoperations() + adjustmentswork.size());
		}
		return adjustmentsworkamount;
	}

	private BigDecimal getAdjustmentsAmount(Date date, PlaceEntity place, Daily daily) {
		List<AdjustmentEntity> adjustments = adjustmentRepository.findByCreationdateAndPlace(date, place);
		BigDecimal adjusmentsamount = BigDecimal.ZERO;
		if (adjustments != null && !adjustments.isEmpty()) {
			List<Adjustment> ladjustments = new ArrayList<>();
			Iterator<AdjustmentEntity> iadjustments = adjustments.iterator();
			AdjustmentEntity adjustment;
			while (iadjustments.hasNext()) {
				adjustment = iadjustments.next();
				ladjustments.add(mapper.map(adjustment, Adjustment.class));
				if (adjustment.getPayment().getIdpayment().equals(Constants.EFECTIVO)) {
					adjusmentsamount = adjusmentsamount.add(adjustment.getAmount());
				}
			}
			daily.setAdjustments(ladjustments);
			daily.setNumoperations(daily.getNumoperations() + adjustments.size());
		}
		return adjusmentsamount;
	}

	@Override
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
