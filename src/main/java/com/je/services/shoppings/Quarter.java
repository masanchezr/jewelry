package com.je.services.shoppings;

import java.math.BigDecimal;

public class Quarter {

	private BigDecimal gramsreal;

	private BigDecimal grossgrams;

	private BigDecimal netgrams;

	private BigDecimal amount;

	private BigDecimal gramsAg;

	private BigDecimal amountag;

	private BigDecimal averagegold;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setGramsAg(BigDecimal gramsAg) {
		this.gramsAg = gramsAg;
	}

	public BigDecimal getGramsAg() {
		return gramsAg;
	}

	public BigDecimal getAmountag() {
		return amountag;
	}

	public void setAmountag(BigDecimal amountag) {
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

	public BigDecimal getNetgrams() {
		return netgrams;
	}

	public void setNetgrams(BigDecimal netgrams) {
		this.netgrams = netgrams;
	}

	public BigDecimal getAveragegold() {
		return averagegold;
	}

	public void setAveragegold(BigDecimal averagegold) {
		this.averagegold = averagegold;
	}
}
