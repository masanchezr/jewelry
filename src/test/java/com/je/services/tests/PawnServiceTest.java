package com.je.services.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectPawnEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.pawns.NewPawn;
import com.je.services.pawns.Pawn;
import com.je.services.pawns.PawnService;

/**
 * The Class PawnServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class PawnServiceTest {

	/** The pawn service. */
	@Autowired
	private PawnService pawnService;

	/**
	 * New pawn test.
	 */
	@Test
	public void newPawnTest() {
		NewPawn pawn = new NewPawn();
		List<ObjectPawnEntity> objects = new ArrayList<ObjectPawnEntity>();
		ObjectPawnEntity object = new ObjectPawnEntity();
		MetalEntity metal = new MetalEntity();
		metal.setIdmetal(1L);
		object.setGrossgrams(new BigDecimal(6));
		object.setMetal(metal);
		pawn.setNumpawn("177");
		pawn.setNif("YB569035");
		pawn.setUser("13700");
		pawn.setAddress("Leon");
		pawn.setName("Ainhoa");
		pawn.setSurname("Gonzalez Gonzalez");
		pawn.setDatebirth("21-01-1973");
		pawn.setAmount(130);
		pawn.setNationality("Española");
		pawn.setCreationdate("14-01-2017");
		pawn.setPercent(20);
		pawn.setObjects(objects);
		// pawnService.save(pawn);
	}

	/**
	 * Search by numpawn.
	 */
	@Test
	public void searchByNumpawn() {
		Pawn pawn = new Pawn();
		pawn.setNumpawn("327");
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(9004L);
		pawn.setPlace(place);
		List<Pawn> pawns = pawnService.searchByNumpawn(pawn);
		if (pawns != null) {
			Iterator<Pawn> ipawns = pawns.iterator();
			Pawn auxpawn;
			while (ipawns.hasNext()) {
				auxpawn = ipawns.next();
				System.out.println("importe:" + auxpawn.getAmount() + " fecha creacion:" + auxpawn.getCreationdate());
			}
		}
	}

	/**
	 * Search renew by numpawn test.
	 */
	@Test
	public void searchRenewByNumpawnTest() {
		Pawn pawn = new Pawn();
		pawn.setNumpawn("136");
		pawn.setUser("24002");
		List<Pawn> pawns = pawnService.searchRenewByNumpawn(pawn);
		if (pawns != null) {
			Iterator<Pawn> ipawns = pawns.iterator();
			Pawn auxpawn;
			while (ipawns.hasNext()) {
				auxpawn = ipawns.next();
				System.out.println("importe:" + auxpawn.getAmount() + " fecha creacion:" + auxpawn.getCreationdate());
			}
		}
	}

	@Test
	public void getCommissionsTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		System.out.println(pawnService.getCommissions("01-02-2015", "02-02-2015", place));
	}

	@Test
	public void pawnsOutofdateTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24003L);
		List<Pawn> pawns = pawnService.pawnsOutofdate(place);
		if (pawns != null) {
			System.out.println(pawns.size());
			Iterator<Pawn> ipawns = pawns.iterator();
			Pawn auxpawn;
			while (ipawns.hasNext()) {
				auxpawn = ipawns.next();
				System.out.println("num:" + auxpawn.getNumpawn() + "importe:" + auxpawn.getAmount() + " fecha creacion:"
						+ auxpawn.getCreationdate());
			}
		}
	}
}