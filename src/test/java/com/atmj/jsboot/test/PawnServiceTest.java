package com.atmj.jsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
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
	void searchByPlaceYearNumPawnIdreturnpawnTest() {
		NewPawn pawn = new NewPawn();
		pawn.setUser("13700");
		pawn.setNumpawn("4");
		assertNotNull(pawnService.searchByPlaceYearNumPawnIdreturnpawn(pawn));
	}
}
