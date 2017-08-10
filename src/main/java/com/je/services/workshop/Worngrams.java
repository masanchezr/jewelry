package com.je.services.workshop;

import java.math.BigDecimal;

import com.je.dbaccess.entities.MetalEntity;

public class Worngrams {

	private BigDecimal grams;
	private MetalEntity metal;

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public MetalEntity getMetal() {
		return metal;
	}

	public void setMetal(MetalEntity metal) {
		this.metal = metal;
	}

}
