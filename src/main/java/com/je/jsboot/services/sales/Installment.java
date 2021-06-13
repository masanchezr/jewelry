package com.je.jsboot.services.sales;

import com.je.jsboot.dbaccess.entities.PaymentEntity;

public class Installment {
	private PaymentEntity pay;
	private String amount;
	private Long idsalepostponed;

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getIdsalepostponed() {
		return idsalepostponed;
	}

	public void setIdsalepostponed(Long idsalepostponed) {
		this.idsalepostponed = idsalepostponed;
	}

}
