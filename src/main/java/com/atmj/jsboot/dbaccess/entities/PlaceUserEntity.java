package com.atmj.jsboot.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "placemembers")
public class PlaceUserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPLACEMEMBERS")
	private Long idplacerole;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "ID")
	private UserEntity user;

	public Long getIdplacerole() {
		return idplacerole;
	}

	public void setIdplacerole(Long idplacerole) {
		this.idplacerole = idplacerole;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
