package com.je.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

/**
 * The Class AddressEntity.
 */
@Entity
@Table(name = "addresses")
public class AddressEntity {

	/** The idaddress. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDADDRESS")
	private Long idaddress;

	/** The address. */
	@Column(name = "ADDRESS")
	private String address;

	/** The postalcode. */
	@Column(name = "POSTALCODE")
	private Long postalcode;

	/** The city. */
	@Column(name = "CITY")
	private String city;

	/** The country. */
	@Column(name = "COUNTRY")
	private String country;

	/** The invoicename. */
	@Column(name = "INVOICENAME")
	private String invoicename;

	/** The cif. */
	@Column(name = "CIF")
	private String cif;

	/** The creationdate. */
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the idaddress.
	 *
	 * @return the idaddress
	 */
	public Long getIdaddress() {
		return idaddress;
	}

	/**
	 * Sets the idaddress.
	 *
	 * @param idaddress the new idaddress
	 */
	public void setIdaddress(Long idaddress) {
		this.idaddress = idaddress;
	}

	/**
	 * Gets the postalcode.
	 *
	 * @return the postalcode
	 */
	public Long getPostalcode() {
		return postalcode;
	}

	/**
	 * Sets the postalcode.
	 *
	 * @param postalcode the new postalcode
	 */
	public void setPostalcode(Long postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the invoicename.
	 *
	 * @return the invoicename
	 */
	public String getInvoicename() {
		return invoicename;
	}

	/**
	 * Sets the invoicename.
	 *
	 * @param invoicename the new invoicename
	 */
	public void setInvoicename(String invoicename) {
		this.invoicename = invoicename;
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
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the cif.
	 *
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * Sets the cif.
	 *
	 * @param cif the new cif
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

}
