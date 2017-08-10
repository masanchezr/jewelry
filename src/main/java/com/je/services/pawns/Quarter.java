package com.je.services.pawns;

import java.math.BigDecimal;

public class Quarter {

	private BigDecimal gramsreal;

	private BigDecimal grossgrams;

	private BigDecimal amount;

	private double gramsAg;

	private double amountag;

	private BigDecimal averagegold;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setGramsAg(double gramsAg) {
		this.gramsAg = gramsAg;
	}

	public double getGramsAg() {
		return gramsAg;
	}

	public double getAmountag() {
		return amountag;
	}

	public void setAmountag(double amountag) {
		this.amountag = amountag;
	}

	public BigDecimal getGramsreal() {
		return gramsreal;
	}

	public void setGramsreal(BigDecimal gramsreal) {
		this.gramsreal = gramsreal;
	}

	public BigDecimal getGrossgrams() {
		return grossgrams;
	}

	public void setGrossgrams(BigDecimal grossgrams) {
		this.grossgrams = grossgrams;
	}

	public BigDecimal getAveragegold() {
		return averagegold;
	}

	public void setAveragegold(BigDecimal averagegold) {
		this.averagegold = averagegold;
	}
}
