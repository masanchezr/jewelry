package com.je.services.dailies;

import java.math.BigDecimal;
import java.util.List;

import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.EntryMoneyEntity;
import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.entities.RecordingEntity;
import com.je.dbaccess.entities.StrapEntity;
import com.je.forms.Sale;
import com.je.forms.SalePostPoned;
import com.je.services.adjustments.Adjustment;
import com.je.services.discounts.Discount;
import com.je.services.otherconcepts.OtherConcept;
import com.je.services.pawns.Pawn;
import com.je.services.pawns.Renovation;
import com.je.services.rentals.Rental;
import com.je.services.sales.CancelSale;
import com.je.services.shoppings.Shopping;

/**
 * The Class Daily.
 */
public class Daily {

	/** The finalamount. */
	private BigDecimal finalamount;

	/** The sales. */
	private List<Sale> sales;

	/** The pawns. */
	private List<Pawn> retiredpawns;

	private List<Pawn> newpawns;

	/** The adjustments. */
	private List<Adjustment> adjustments;

	/** The shoppings. */
	private List<Shopping> shoppings;

	/** The othersconcepts. */
	private List<OtherConcept> othersconcepts;

	/** The renovations. */
	private List<Renovation> renovations;

	/** The cancelsales. */
	private List<CancelSale> cancelsales;

	private List<Adjustment> adjustmentswork;

	private List<EntryMoneyEntity> entriesmoney;

	private List<BatteryEntity> batteries;

	private List<StrapEntity> straps;

	private List<Rental> rentals;

	private List<Discount> discounts;
	private List<RecordingEntity> recordings;

	private PayrollEntity payroll;

	private int numoperations;

	private List<SalePostPoned> lsalespost;

	/**
	 * Gets the sales.
	 *
	 * @return the sales
	 */
	public List<Sale> getSales() {
		return sales;
	}

	/**
	 * Sets the sales.
	 *
	 * @param sales the new sales
	 */
	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	/**
	 * Gets the pawns.
	 *
	 * @return the pawns
	 */
	public List<Pawn> getRetiredpawns() {
		return retiredpawns;
	}

	/**
	 * Sets the pawns.
	 *
	 * @param pawns the new pawns
	 */
	public void setRetiredpawns(List<Pawn> retiredpawns) {
		this.retiredpawns = retiredpawns;
	}

	/**
	 * Gets the adjustments.
	 *
	 * @return the adjustments
	 */
	public List<Adjustment> getAdjustments() {
		return adjustments;
	}

	/**
	 * Sets the adjustments.
	 *
	 * @param adjustments the new adjustments
	 */
	public void setAdjustments(List<Adjustment> adjustments) {
		this.adjustments = adjustments;
	}

	/**
	 * Gets the shoppings.
	 *
	 * @return the shoppings
	 */
	public List<Shopping> getShoppings() {
		return shoppings;
	}

	/**
	 * Sets the shoppings.
	 *
	 * @param shoppings the new shoppings
	 */
	public void setShoppings(List<Shopping> shoppings) {
		this.shoppings = shoppings;
	}

	/**
	 * Gets the othersconcepts.
	 *
	 * @return the othersconcepts
	 */
	public List<OtherConcept> getOthersconcepts() {
		return othersconcepts;
	}

	/**
	 * Sets the othersconcepts.
	 *
	 * @param othersconcepts the new othersconcepts
	 */
	public void setOthersconcepts(List<OtherConcept> othersconcepts) {
		this.othersconcepts = othersconcepts;
	}

	/**
	 * Gets the renovations.
	 *
	 * @return the renovations
	 */
	public List<Renovation> getRenovations() {
		return renovations;
	}

	/**
	 * Sets the renovations.
	 *
	 * @param renovations the new renovations
	 */
	public void setRenovations(List<Renovation> renovations) {
		this.renovations = renovations;
	}

	public int getNumoperations() {
		return numoperations;
	}

	public void setNumoperations(int numoperations) {
		this.numoperations = numoperations;
	}

	public List<Adjustment> getAdjustmentswork() {
		return adjustmentswork;
	}

	public void setAdjustmentswork(List<Adjustment> adjustmentswork) {
		this.adjustmentswork = adjustmentswork;
	}

	public List<EntryMoneyEntity> getEntriesmoney() {
		return entriesmoney;
	}

	public void setEntriesmoney(List<EntryMoneyEntity> entriesMoney) {
		this.entriesmoney = entriesMoney;
	}

	public List<BatteryEntity> getBatteries() {
		return batteries;
	}

	public void setBatteries(List<BatteryEntity> batteries) {
		this.batteries = batteries;
	}

	public List<StrapEntity> getStraps() {
		return straps;
	}

	public void setStraps(List<StrapEntity> straps) {
		this.straps = straps;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public BigDecimal getFinalamount() {
		return finalamount;
	}

	public void setFinalamount(BigDecimal finalamount) {
		this.finalamount = finalamount;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public List<RecordingEntity> getRecordings() {
		return recordings;
	}

	public void setRecordings(List<RecordingEntity> recordings) {
		this.recordings = recordings;
	}

	public List<Pawn> getNewpawns() {
		return newpawns;
	}

	public void setNewpawns(List<Pawn> newpawns) {
		this.newpawns = newpawns;
	}

	public List<SalePostPoned> getLsalespost() {
		return lsalespost;
	}

	public void setLsalespost(List<SalePostPoned> lsalespost) {
		this.lsalespost = lsalespost;
	}

	public PayrollEntity getPayroll() {
		return payroll;
	}

	public void setPayroll(PayrollEntity payroll) {
		this.payroll = payroll;
	}

	public List<CancelSale> getCancelsales() {
		return cancelsales;
	}

	public void setCancelsales(List<CancelSale> cancelsales) {
		this.cancelsales = cancelsales;
	}
}
