package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.atmj.jsboot.utils.constants.Constants;

@Entity
@Table(name = Constants.NATIONS)
public class NationEntity {

	@Id
	@Column(name = "IDNATION")
	private Long idnation;

	@Column(name = "NATION")
	private String nation;

	public Long getIdnation() {
		return idnation;
	}

	public void setIdnation(Long idnation) {
		this.idnation = idnation;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
}
