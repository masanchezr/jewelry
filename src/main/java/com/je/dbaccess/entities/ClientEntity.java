package com.je.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class ClientEntity.
 */
@Entity
@Table(name = "clients")
public class ClientEntity implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nifclient. */
	@Id
	@Column(name = "NIF")
	private String nifclient;

	/** The email. */
	@Column(name = "EMAIL")
	private String email;

	/**
	 * The username, foreign key with column username of table Users
	 * (spring-security).
	 * 
	 * @Column(name = "USERNAME") private String userlogin;
	 * 
	 *              /** The name.
	 */
	@Column(name = "NAME")
	private String name;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DATECREATION")
	private Date creationdate;

	/** The telephone. */
	@Column(name = "TELEPHONE")
	private Long telephone;

	/**
	 * The objects.
	 *
	 * @return the email
	 * @OneToMany private Set<JewelEntity> objects;
	 */

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @param nameuser
	 *            the new name
	 */
	public void setName(String nameuser) {
		this.name = nameuser;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 * 
	 *         public String getSurname() { return surname; }
	 * 
	 *         /** Sets the surname. the address
	 * 
	 *         public String getAddress() { return address; }
	 * 
	 *         /** Sets the address.
	 */

	/**
	 * Gets the nifclient.
	 * 
	 * @return the nifclient
	 */
	public String getNifclient() {
		return nifclient;
	}

	/**
	 * Sets the nifclient.
	 * 
	 * @param nifclient
	 *            the new nifclient
	 */
	public void setNifclient(String nifclient) {
		this.nifclient = nifclient;
	}

	/**
	 * Sets the creationdate.
	 * 
	 * @param creationdate
	 *            the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the creationdate.
	 * 
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public Long getTelephone() {
		return telephone;
	}

	/**
	 * Sets the telephone.
	 *
	 * @param telephone
	 *            the new telephone
	 */
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	/**
	 * Gets the userlogin.
	 * 
	 * @return the userlogin
	 * 
	 *         public String getUserlogin() { return userlogin; }
	 * 
	 *         /** Sets the userlogin.
	 * 
	 * @param userlogin
	 *            the new userlogin
	 * 
	 *            public void setUserlogin(String userlogin) { this.userlogin =
	 *            userlogin; }
	 */
}
