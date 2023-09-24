package com.atmj.jsboot.services.sales;

import com.atmj.jsboot.dbaccess.entities.PaymentEntity;

public class SearchSale {

	private String sfrom;
	private String suntil;
	private PaymentEntity pay;

	public String getSfrom() {
		return sfrom;
	}

	public void setSfrom(String sfrom) {
		this.sfrom = sfrom;
	}

	public String getSuntil() {
		return suntil;
	}

	public void setSuntil(String suntil) {
		this.suntil = suntil;
	}

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}
}
