package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payrolltypes")
public class PayrolltypeEntity {

	@Id
	@Column(name = "IDPAYROLLTYPE")
	private Long idpayrolltype;

	@Column(name = "NAME")
	private String name;

	public Long getIdpayrolltype() {
		return idpayrolltype;
	}

	public void setIdpayrolltype(Long idpayrolltype) {
		this.idpayrolltype = idpayrolltype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
