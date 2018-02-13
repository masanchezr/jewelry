package com.je.dbaccess.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.ClientPawnEntity;
import com.je.dbaccess.entities.PawnEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.RenovationEntity;
import com.je.dbaccess.repositories.PawnsRepository;

/**
 * The Class PawnsRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class PawnsRepositoryTest {

	/** The pawns repository. */
	@Autowired
	private PawnsRepository pawnsRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		PawnEntity pawn = new PawnEntity();
		ClientPawnEntity cpe = new ClientPawnEntity();
		PlaceEntity place = new PlaceEntity();
		List<RenovationEntity> renovations = new ArrayList<RenovationEntity>();
		RenovationEntity renovation = new RenovationEntity();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 9, 27);
		// pawn.setIdpawn(1669L);
		pawn.setCreationdate(calendar.getTime());
		cpe.setNif("7098763");
		place.setIdplace(24002L);
		cpe.setAddress("una lista de leon");
		cpe.setCreationclient(new Date());
		cpe.setName("empeños");
		cpe.setSurname("que trajo jose en una lista de leon");
		pawn.setNumpawn("1069");
		pawn.setAmount(BigDecimal.valueOf(80));
		pawn.setPercent(BigDecimal.valueOf(5));
		pawn.setPlace(place);
		pawn.setClient(cpe);
		calendar.set(2015, 3, 27);
		renovation.setNextrenovationdate(calendar.getTime());
		renovation.setPawn(pawn);
		renovations.add(renovation);
		pawn.setRenovations(renovations);
		// pawnsRepository.save(pawn);
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		System.out.println("findAllTest");
		Iterable<PawnEntity> ipawns = pawnsRepository.findAll();
		if (ipawns != null) {
			Iterator<PawnEntity> itpawns = ipawns.iterator();
			if (itpawns != null) {
				while (itpawns.hasNext()) {
					System.out.println("fecha creacion:" + itpawns.next().getCreationdate());
				}
			}
		}
	}

	/**
	 * Find by numpawn and place and creationdate test.
	 */
	@Test
	public void findByNumpawnAndPlaceAndCreationdateTest() {
		Calendar calendar = new GregorianCalendar(2014, 7, 26);
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		List<PawnEntity> lpawns = pawnsRepository.findByNumpawnAndPlaceAndCreationdate("665", place,
				calendar.getTime());
		Iterator<PawnEntity> ipawns = lpawns.iterator();
		while (ipawns.hasNext()) {
			System.out.println("importe entregado:" + ipawns.next().getAmount());
		}
	}

	@Test
	public void sumPawnsActiveTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		Double sum = pawnsRepository.sumPawnsActive(place);
		System.out.println(sum);
	}
}
