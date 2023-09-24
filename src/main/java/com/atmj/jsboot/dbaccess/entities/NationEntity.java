package com.atmj.jsboot.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
