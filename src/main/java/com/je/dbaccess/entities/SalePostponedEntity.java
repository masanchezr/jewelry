package com.je.dbaccess.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salespostponed")
public class SalePostponedEntity {

	@Id
	@Column(name = "idsalepostponed")
	private Long idsalepostponed;

	@Column(name = "amount")
	private BigDecimal amount;

	public Long getIdsalepostponed() {
		return idsalepostponed;
	}

	public void setIdsalepostponed(Long idsalepostponed) {
		this.idsalepostponed = idsalepostponed;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
