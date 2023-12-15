package com.atmj.jsboot.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.NationEntity;
import com.atmj.jsboot.dbaccess.entities.ObjectPawnEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.TrackEntity;
import com.atmj.jsboot.services.pawns.NewPawn;
import com.atmj.jsboot.services.pawns.Pawn;
import com.atmj.jsboot.services.pawns.PawnService;

/**
 * The Class PawnServiceTest.
 */
@SpringBootTest
class PawnServiceTest {

	/** The pawn service. */
	@Autowired
	private PawnService pawnService;

	/**
	 * Search by numpawn.
	 */
	@Test
	void searchByNumpawn() {
		Pawn pawn = new Pawn();
		PlaceEntity place = new PlaceEntity();
		List<Pawn> pawns;
		pawn.setNumpawn("327");
		place.setIdplace(9004L);
		pawn.setPlace(place);
		pawns = pawnService.searchByNumpawn(pawn);
		Assertions.assertNotNull(pawns);
	}

	/**
	 * Search renew by numpawn test.
	 */
	@Test
	void searchRenewByNumpawnTest() {
		Pawn pawn = new Pawn();
		pawn.setNumpawn("136");
		pawn.setUser("24002");
		Assertions.assertNotNull(pawnService.searchRenewByNumpawn(pawn));
	}

	@Test
	void getCommissionsTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		Assertions.assertNotNull(pawnService.getCommissions("01-02-2015", "02-02-2015", place));
	}

	@Test
	void pawnsOutofdateTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24003L);
		Assertions.assertNotNull(pawnService.pawnsOutofdate(place));
	}

	@Test
	void updatePawnTest() {
		NewPawn newpawn = new NewPawn();
		NationEntity nation = new NationEntity();
		TrackEntity track = new TrackEntity();
		List<ObjectPawnEntity> objects = new ArrayList<ObjectPawnEntity>();
		ObjectPawnEntity o = new ObjectPawnEntity();
		MetalEntity metal = new MetalEntity();
		metal.setIdmetal(1L);
		o.setDescription("dos pendientes estilo versace");
		o.setIdobjectpawn(10295L);
		o.setRealgrams(new BigDecimal(2.8));
		o.setGrossgrams(new BigDecimal(2.8));
		o.setMetal(metal);
		objects.add(o);
		track.setIdtrack(13L);
		nation.setIdnation(34L);
		newpawn.setNumpawn("286");
		newpawn.setAddress("Timanfaya 10");
		newpawn.setAmount("70");
		newpawn.setCreationdate("2021-01-05");
		newpawn.setDatebirth("1989-10-28");
		newpawn.setDescription("dos pendientes estilo versace");
		newpawn.setId(9820L);
		newpawn.setName("Basilio");
		newpawn.setSurname("Suárez Silva");
		newpawn.setNation(nation);
		newpawn.setTown("Madrid");
		newpawn.setNif("50977535J");
		newpawn.setPercent("20");
		newpawn.setTrack(track);
		newpawn.setObjects(objects);
		pawnService.update(newpawn);
	}
}
