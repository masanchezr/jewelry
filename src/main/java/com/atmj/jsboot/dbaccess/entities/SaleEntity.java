package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.utils.constants.Constants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The Class SaleEntity.
 */
@Entity
@Table(name = Constants.SALES)
public class SaleEntity {

	/** The idsale. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDSALE")
	private Long idsale;

	@Column(name = "NUMSALE")
	private Long numsale;

	/** The creationdate. */
	@Column(name = "CREATIONDATE")
	@CreatedDate
	private Date creationdate;

	@Column(name = "YEAR")
	private Integer year;

	/**
	 * The client_nif. Quito el manytoOne porque voy a guardar antes al cliente, si
	 * quisieramos guardar el cliente a la vez que la compra el cascadetype sería
	 * MERGE
	 */

	// @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "IDCLIENT")
	@ManyToOne
	private ClientEntity client;

	/**
	 * The sjewels.
	 */
	@JoinColumn(name = "IDSALE")
	@OneToMany(cascade = CascadeType.ALL)
	private List<SalesJewels> sjewels;

	@JoinColumn(name = "IDSALE")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SalesPayments> spayments;

	/** The total. */
	@Column(name = "TOTAL")
	private BigDecimal total;

	/** The addressmailing. */
	@JoinColumn(name = "ADDRESSMAILING")
	@ManyToOne(cascade = CascadeType.ALL)
	private AddressEntity addressmailing;

	/** The addressinvoice. */
	@JoinColumn(name = "ADDRESSINVOICE")
	@ManyToOne(cascade = CascadeType.ALL)
	private AddressEntity addressinvoice;

	/** The discount. */
	@Column(name = "DISCOUNT")
	private BigDecimal discount;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

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
	 * Gets the idsale.
	 *
	 * @return the idsale
	 */
	public Long getIdsale() {
		return idsale;
	}

	/**
	 * Sets the idsale.
	 *
	 * @param idsale the idsale to set
	 */
	public void setIdsale(Long idsale) {
		this.idsale = idsale;
	}

	/**
	 * Gets the client.
	 *
	 * @return the nif
	 */
	public ClientEntity getClient() {
		return client;
	}

	/**
	 * Sets the client.
	 *
	 * @param nif the nif to set
	 */
	public void setClient(ClientEntity nif) {
		this.client = nif;
	}

	/**
	 * Gets the sjewels.
	 *
	 * @return the sjewels
	 */
	public List<SalesJewels> getSjewels() {
		return sjewels;
	}

	/**
	 * Sets the sjewels.
	 *
	 * @param sjewels the sjewels to set
	 */
	public void setSjewels(List<SalesJewels> sjewels) {
		this.sjewels = sjewels;
	}

	/**
	 * Sets the total.
	 *
	 * @param importeTotal the new total
	 */
	public void setTotal(BigDecimal importeTotal) {
		this.total = importeTotal;

	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * Gets the addressmailing.
	 *
	 * @return the addressmailing
	 */
	public AddressEntity getAddressmailing() {
		return addressmailing;
	}

	/**
	 * Sets the addressmailing.
	 *
	 * @param addressmailing the new addressmailing
	 */
	public void setAddressmailing(AddressEntity addressmailing) {
		this.addressmailing = addressmailing;
	}

	/**
	 * Gets the addressinvoice.
	 *
	 * @return the addressinvoice
	 */
	public AddressEntity getAddressinvoice() {
		return addressinvoice;
	}

	/**
	 * Sets the addressinvoice.
	 *
	 * @param addressinvoice the new addressinvoice
	 */
	public void setAddressinvoice(AddressEntity addressinvoice) {
		this.addressinvoice = addressinvoice;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * Gets the place.
	 *
	 * @return the place
	 */
	public PlaceEntity getPlace() {
		return place;
	}

	/**
	 * Sets the place.
	 *
	 * @param place the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public Long getNumsale() {
		return numsale;
	}

	public void setNumsale(Long numsale) {
		this.numsale = numsale;
	}

	public List<SalesPayments> getSpayments() {
		return spayments;
	}

	public void setSpayments(List<SalesPayments> spayments) {
		this.spayments = spayments;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}