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
	private MailService mailAdjustmentService;

	@Autowired
	private DailyService dailyService;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public Daily save(Adjustment adjustment) {
		// primeramente miramos si existe el arreglo
		Long idadjustment = adjustment.getIdadjustment();
		AdjustmentEntity adjustmentEntity = adjustmentRepository.findOne(adjustment.getIdadjustment());
		BigDecimal amount = adjustment.getAmount();
		if (adjustmentEntity != null) {
			// miro si se ha cobrado el precio recomendado
			BigDecimal recommendedprice = adjustmentEntity.getRecommendedprice();
			if (recommendedprice != null && recommendedprice.compareTo(BigDecimal.ZERO) > 0
					&& recommendedprice.compareTo(amount) != 0) {
				// envio un mail u otro tipo de alerta
				mailAdjustmentService.sendMail(
						"Numero de arreglo: " + idadjustment + ", importe recomendado:" + recommendedprice
								+ " euros, importe cobrado al cliente:" + amount + " euros.",
						null, "Arreglo no coincide con precio recomendado.");
			}
		} else {
			adjustmentEntity = new AdjustmentEntity();
			adjustmentEntity.setIdadjustment(idadjustment);
			adjustmentEntity.setWork(Boolean.FALSE);
		}
		List<PlaceUserEntity> placeuser = placeUserRepository.findByUsername(adjustment.getUser());
		PlaceEntity place = placeuser.get(0).getPlace();
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
		return dailyService.getDaily(new Date(), place, null);
	}

	public void saveWorkshop(Adjustment adjustment) {
		AdjustmentEntity adjustmentEntity = mapper.map(adjustment, AdjustmentEntity.class);
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(Constants.EFECTIVO);
		adjustmentEntity.setCreationdate(new Date());
		adjustmentEntity.setWork(Boolean.TRUE);
		adjustmentEntity.setPayment(pay);
		adjustmentRepository.save(adjustmentEntity);
	}

	public Map<String, BigDecimal> sumAdjustmentByDates(Date from, Date until, PlaceEntity place) {
		Map<String, BigDecimal> sums = new HashMap<String, BigDecimal>();
		sums.put("amount", adjustmentRepository.sumAmountByCreationdateAndPlace(from, until,
				mapper.map(place, PlaceEntity.class)));
		sums.put("amountwork", adjustmentRepository.sumAmountworkByCreationdateAndPlace(from, until,
				mapper.map(place, PlaceEntity.class)));
		return sums;
	}
}
