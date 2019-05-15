package com.je.services.adjustments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.AdjustmentEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.PlaceUserEntity;
import com.je.dbaccess.repositories.AdjustmentRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.services.mails.MailService;
import com.je.utils.constants.Constants;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class AdjustmentServiceImpl.
 */
public class AdjustmentServiceImpl implements AdjustmentService {

	/** The adjustment repository. */
	@Autowired
	private AdjustmentRepository adjustmentRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private DailyService dailyService;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	@Override
	public Daily save(Adjustment adjustment) {
		MailService mailAdjustmentService;
		// primeramente miramos si existe el arreglo
		Long idadjustment = adjustment.getIdadjustment();
		AdjustmentEntity adjustmentEntity = adjustmentRepository.findById(adjustment.getIdadjustment()).orElse(null);
		BigDecimal amount = Util.getNumber(adjustment.getAmount());
		List<PlaceUserEntity> placeuser = placeUserRepository.findByUsername(adjustment.getUser());
		PlaceEntity place = placeuser.get(0).getPlace();
		if (amount != null) {
			if (adjustmentEntity != null) {
				// miro si se ha cobrado el precio recomendado
				BigDecimal recommendedprice = adjustmentEntity.getRecommendedprice();
				if (recommendedprice != null && recommendedprice.compareTo(BigDecimal.ZERO) > 0
						&& recommendedprice.compareTo(amount) != 0) {
					// envio un mail u otro tipo de alerta
					mailAdjustmentService = new MailService(
							"N&uacute;mero de arreglo: " + idadjustment + ", importe recomendado:" + recommendedprice
									+ " euros, importe cobrado al cliente:" + amount + " euros.",
							null, "Arreglo no coincide con precio recomendado.");
					mailAdjustmentService.start();
				}
			} else {
				adjustmentEntity = new AdjustmentEntity();
				adjustmentEntity.setIdadjustment(idadjustment);
				adjustmentEntity.setWork(Boolean.FALSE);
			}
			adjustmentEntity.setPlace(place);
			adjustmentEntity.setDescription(adjustment.getDescription());
			if (amount.compareTo(BigDecimal.ZERO) < 0) {
				adjustmentEntity.setAmountwork(amount.abs());
				adjustmentEntity.setCreationdate(new Date());
				adjustmentEntity.setPaymentwork(adjustment.getPayment());
			} else {
				adjustmentEntity.setPayment(adjustment.getPayment());
				adjustmentEntity.setCarrydate(new Date());
				adjustmentEntity.setAmount(amount);
			}
			adjustmentRepository.save(adjustmentEntity);
		}
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}

	@Override
	public void saveWorkshop(Adjustment adjustment) {
		AdjustmentEntity adjustmentEntity = mapper.map(adjustment, AdjustmentEntity.class);
		// miramos si ya existía
		AdjustmentEntity adjustmentlast = adjustmentRepository.findById(adjustment.getIdadjustment()).orElse(null);
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(Constants.EFECTIVO);
		if (adjustmentlast != null) {
			adjustmentlast.setCreationdate(new Date());
			adjustmentlast.setWork(Boolean.TRUE);
			adjustmentlast.setPaymentwork(pay);
			adjustmentlast.setAmountwork(adjustmentEntity.getAmountwork());
			adjustmentlast.setRecommendedprice(adjustmentEntity.getRecommendedprice());
			adjustmentlast
					.setDescription(adjustmentlast.getDescription().concat(" ").concat(adjustment.getDescription()));
			adjustmentlast.setGrams(adjustmentEntity.getGrams());
			adjustmentRepository.save(adjustmentlast);
		} else {
			adjustmentEntity.setCreationdate(new Date());
			adjustmentEntity.setWork(Boolean.TRUE);
			adjustmentEntity.setPaymentwork(pay);
			adjustmentRepository.save(adjustmentEntity);
		}
	}

	@Override
	public Map<String, BigDecimal> sumAdjustmentByDates(Date from, Date until, PlaceEntity place) {
		Map<String, BigDecimal> sums = new HashMap<>();
		sums.put(Constants.AMOUNT, adjustmentRepository.sumAmountByCreationdateAndPlace(from, until,
				mapper.map(place, PlaceEntity.class)));
		sums.put("amountwork", adjustmentRepository.sumAmountworkByCreationdateAndPlace(from, until,
				mapper.map(place, PlaceEntity.class)));
		return sums;
	}

	@Override
	public Adjustment findById(Long idadjustment) {
		AdjustmentEntity adjustment = adjustmentRepository.findById(idadjustment).orElse(null);
		if (adjustment != null) {
			return mapper.map(adjustment, Adjustment.class);
		} else {
			return null;
		}
	}
}
