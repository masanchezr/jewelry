package com.je.jsboot.services.workshop;

import java.math.BigDecimal;

import com.je.jsboot.dbaccess.entities.MetalEntity;

public class Worngrams {

	private BigDecimal grams;
	private MetalEntity material;

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public MetalEntity getMetal() {
		return material;
	}

	public void setMetal(MetalEntity material) {
		this.material = material;
	}

}
