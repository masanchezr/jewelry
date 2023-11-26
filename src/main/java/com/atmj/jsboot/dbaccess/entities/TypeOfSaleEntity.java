package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "typesofsales")
public class TypeOfSaleEntity {

	@Id
	@Column(name = "idtypesofsales")
	private Long idtypeofsale;

	@Column(name = "name")
	private String name;

	/**
	 * @return the idtypeofsale
	 */
	public Long getIdtypeofsale() {
		return idtypeofsale;
	}

	/**
	 * @param idtypeofsale the idtypeofsale to set
	 */
	public void setIdtypeofsale(Long idtypeofsale) {
		this.idtypeofsale = idtypeofsale;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
