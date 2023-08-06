package com.je.jsboot.services.adjustments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.AdjustmentEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.PlaceUserEntity;
import com.je.jsboot.dbaccess.entities.WorkEntity;
import com.je.jsboot.dbaccess.repositories.AdjustmentRepository;
import com.je.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.je.jsboot.dbaccess.repositories.UsersRepository;
import com.je.jsboot.dbaccess.repositories.WorksRepository;
import com.je.jsboot.services.dailies.Daily;
import com.je.jsboot.services.dailies.DailyService;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.date.DateUtil;

/**
 * The Class AdjustmentServiceImpl.
 */
@Service
public class AdjustmentServiceImpl implements AdjustmentService {

	/** The adjustment repository. */
	@Autowired
	private AdjustmentRepository adjustmentRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private WorksRepository worksRepository;

	@Autowired
	private DailyService dailyService;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	@Override
	public Daily save(Adjustment adjustment) {
		// primeramente miramos si existe el arreglo
		Long idadjustment = adjustment.getIdadjustment();
		AdjustmentEntity adjustmentEntity = adjustmentRepository.findById(adjustment.getIdadjustment()).orElse(null);
		List<PlaceUserEntity> placeuser = placeUserRepository
				.findByUser(usersRepository.findByUsername(adjustment.getUser()));
		PlaceEntity place = placeuser.get(0).getPlace();
		if (adjustmentEntity == null) {
			adjustmentEntity = new AdjustmentEntity();
			adjustmentEntity.setIdadjustment(idadjustment);
			adjustmentEntity.setPlace(place);
			adjustmentEntity.setDescription(adjustment.getDescription());
			adjustmentEntity.setPayment(adjustment.getPayment());
			adjustmentEntity.setCreationdate(new Date());
			adjustmentEntity.setAmount(adjustment.getAmount());
			adjustmentRepository.save(adjustmentEntity);
		}
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}

	/**
	 * Guardamos hechura
	 */
	@Override
	public Daily saveWorkshop(Adjustment adjustment) {
		// primeramente miramos si existe el arreglo
		Long idwork = adjustment.getIdadjustment();
		WorkEntity adjustmentEntity = worksRepository.findById(adjustment.getIdadjustment()).orElse(null);
		List<PlaceUserEntity> placeuser = placeUserRepository
				.findByUser(usersRepository.findByUsername(adjustment.getUser()));
		PlaceEntity place = placeuser.get(0).getPlace();
		if (adjustmentEntity == null) {
			adjustmentEntity = new WorkEntity();
			adjustmentEntity.setIdwork(idwork);
			adjustmentEntity.setPlace(place);
			adjustmentEntity.setDescription(adjustment.getDescription());
			adjustmentEntity.setAmount(adjustment.getAmount());
			adjustmentEntity.setCreationdate(new Date());
			adjustmentEntity.setPayment(adjustment.getPayment());
			worksRepository.save(adjustmentEntity);
		}
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);

	}

	@Override
	public Map<String, BigDecimal> sumAdjustmentByDates(Date from, Date until, PlaceEntity place) {
		Map<String, BigDecimal> sums = new HashMap<>();
		sums.put(Constants.AMOUNT, adjustmentRepository.sumAmountByCreationdateAndPlace(from, until,
				mapper.map(place, PlaceEntity.class)));
		sums.put("amountwork",
				worksRepository.sumAmountByCreationdateAndPlace(from, until, mapper.map(place, PlaceEntity.class)));
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
