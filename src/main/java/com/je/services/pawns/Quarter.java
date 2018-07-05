package com.je.services.pawns;

import java.math.BigDecimal;

import com.je.dbaccess.entities.MetalEntity;

public class Quarter {

	private BigDecimal gramsreal;

	private BigDecimal grossgrams;

	private BigDecimal amount;

	private MetalEntity metal;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public MetalEntity getMetal() {
		return metal;
	}

	public void setMetal(MetalEntity metal) {
		this.metal = metal;
	}
}
