package com.je.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class ClientPawnEntity.
 */
@Entity
@Table(name = "clientspawns")
public class ClientPawnEntity {

	/** The nif. */
	@Id
	@Column(name = "NIF")
	private String nif;

	/** The name. */
	@Column(name = "NAME")
	private String name;

	/** The surname. */
	@Column(name = "SURNAME")
	private String surname;

	/** The address. */
	@Column(name = "ADDRESS")
	private String address;

	/** The creationclient. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONCLIENT")
	private Date creationclient;

	/** The datebirth. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DATEBIRTH")
	private Date datebirth;

	@Column(name = "TOWN")
	private String town;

	@ManyToOne
	@JoinColumn(name = "IDNATION")
	private NationEntity nation;

	@ManyToOne
	@JoinColumn(name = "IDTRACK")
	private TrackEntity track;

	/**
	 * Gets the nif.
	 *
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Sets the nif.
	 *
	 * @param nif
	 *            the new nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the creationclient.
	 *
	 * @return the creationclient
	 */
	public Date getCreationclient() {
		return creationclient;
	}

	/**
	 * Sets the creationclient.
	 *
	 * @param creationdate
	 *            the new creationclient
	 */
	public void setCreationclient(Date creationdate) {
		this.creationclient = creationdate;
	}

	/**
	 * Gets the datebirth.
	 *
	 * @return the datebirth
	 */
	public Date getDatebirth() {
		return datebirth;
	}

	/**
	 * Sets the datebirth.
	 *
	 * @param datebirth
	 *            the new datebirth
	 */
	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public NationEntity getNation() {
		return nation;
	}

	public void setNation(NationEntity nation) {
		this.nation = nation;
	}

	public TrackEntity getTrack() {
		return track;
	}

	public void setTrack(TrackEntity track) {
		this.track = track;
	}

}
