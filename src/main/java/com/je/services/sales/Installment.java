package com.je.services.sales;

import java.math.BigDecimal;

import com.je.dbaccess.entities.PaymentEntity;

public class Installment {
	private PaymentEntity pay;
	private BigDecimal amount;
	private Long idsalepostponed;
	public PaymentEntity getPay() {
		return pay;
	}
	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getIdsalepostponed() {
		return idsalepostponed;
	}
	public void setIdsalepostponed(Long idsalepostponed) {
		this.idsalepostponed = idsalepostponed;
	}

}
