package com.je.services.sales;

import java.util.Date;
import java.util.List;

import com.je.dbaccess.entities.InstallmentEntity;

public class SalePostPoned extends SaleParent {

	private List<InstallmentEntity> spayments;

	private Date dateretired;

	public List<InstallmentEntity> getSpayments() {
		return spayments;
	}

	public void setSpayments(List<InstallmentEntity> spayments) {
		this.spayments = spayments;
	}

	public Date getDateretired() {
		return dateretired;
	}

	public void setDateretired(Date dateretired) {
		this.dateretired = dateretired;
	}

}
