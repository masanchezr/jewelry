package com.je.services.shoppings;

import java.math.BigDecimal;

import com.je.dbaccess.entities.MetalEntity;

public class QuarterMetal {

	private MetalEntity material;

	private BigDecimal grossgrams;

	private BigDecimal netgrams;

	private BigDecimal realgrams;

	private BigDecimal amount;

	public MetalEntity getMetal() {
		return material;
	}

	public void setMetal(MetalEntity material) {
		this.material = material;
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

	public BigDecimal getRealgrams() {
		return realgrams;
	}

	public void setRealgrams(BigDecimal realgrams) {
		this.realgrams = realgrams;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
