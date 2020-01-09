package com.je.services.pawns;

import java.math.BigDecimal;
import java.util.List;

import com.je.dbaccess.entities.PawnEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.dailies.Daily;

/**
 * The Interface PawnService.
 */
public interface PawnService {

	/**
	 * Save.
	 *
	 * @param pawn the pawn
	 */
	public Daily save(NewPawn pawn);

	/**
	 * Search by idpawn.
	 *
	 * @param idpawn the idpawn
	 * @return the pawn
	 */
	public Pawn searchByIdpawn(Long idpawn);

	/**
	 * Search renew by numpawn.
	 *
	 * @param pawn the pawn
	 * @return the list
	 */
	public List<Pawn> searchRenewByNumpawn(Pawn pawn);

	/**
	 * Renew.
	 *
	 * @param pawn the pawn
	 * @return
	 */
	public Daily renew(Pawn pawn);

	/**
	 * Removes the.
	 *
	 * @param pawn the pawn
	 */
	public Daily remove(Pawn pawn);

	/**
	 * Search by numpawn.
	 *
	 * @param pawn the pawn
	 * @return the list
	 */
	public List<Pawn> searchByNumpawn(Pawn pawn);

	/**
	 * Find by idpawn.
	 *
	 * @param l the l
	 * @return the new pawn
	 */
	public NewPawn findByIdpawn(Long l);

	/**
	 * Update.
	 *
	 * @param pawn the pawn
	 */
	public void update(NewPawn pawn);

	public List<RenovationDates> searchRenovations(Long idpawn);

	public Quarter searchGramsByDates(String sDateFrom, String sDateUntil);

	public BigDecimal getCommissions(String sDateFrom, String sDateUntil, PlaceEntity place);

	public NewPawn searchClient(String nif);

	public List<Pawn> pawnsOutofdate(PlaceEntity place);

	public Double sumPawnsActiveByPlace(PlaceEntity place);

	public List<PawnEntity> getByNIF(String nif);

	public List<PawnEntity> getByNIFAndUserAndRetiredAndReturn(String dni, String user);

	public Daily saveReturnPawn(NewPawn pawn);
}
