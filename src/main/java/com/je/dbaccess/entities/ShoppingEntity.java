package com.je.dbaccess.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The Class ShoppingEntity.
 */
@Entity
@Table(name = "shoppings")
public class ShoppingEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idshop. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDSHOP")
	private Long idshop;

	/** The numshop. */
	@Column(name = "NUMSHOP")
	private Long numshop;

	/** The totalamount. */
	@Column(name = "TOTALAMOUNT")
	private BigDecimal totalamount;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The client. */
	@ManyToOne
	@JoinColumn(name = "NIF", referencedColumnName = "NIF")
	private ClientPawnEntity client;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/** The objects. */
	@JoinColumn(name = "IDSHOP")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ObjectShopEntity> objects;

	@Column(name = "YEAR")
	private Integer year;

	@JoinColumn(name = "IDSHOP")
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PaymentShopEntity> spayments;

	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * Gets the idshop.
	 *
	 * @return the idshop
	 */
	public Long getIdshop() {
		return idshop;
	}

	/**
	 * Sets the idshop.
	 *
	 * @param idshop
	 *            the new idshop
	 */
	public void setIdshop(Long idshop) {
		this.idshop = idshop;
	}

	/**
	 * Gets the numshop.
	 *
	 * @return the numshop
	 */
	public Long getNumshop() {
		return numshop;
	}

	/**
	 * Sets the numshop.
	 *
	 * @param numshop
	 *            the new numshop
	 */
	public void setNumshop(Long numshop) {
		this.numshop = numshop;
	}

	/**
	 * Gets the totalamount.
	 *
	 * @return the totalamount
	 */
	public BigDecimal getTotalamount() {
		return totalamount;
	}

	/**
	 * Sets the totalamount.
	 *
	 * @param totalamount
	 *            the new totalamount
	 */
	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
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
	 * Sets the creationdate.
	 *
	 * @param creationdate
	 *            the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
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
	 * @param place
	 *            the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	/**
	 * Gets the objects.
	 *
	 * @return the objects
	 */
	public List<ObjectShopEntity> getObjects() {
		return objects;
	}

	/**
	 * Sets the objects.
	 *
	 * @param objects
	 *            the new objects
	 */
	public void setObjects(List<ObjectShopEntity> objects) {
		this.objects = objects;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<PaymentShopEntity> getSpayments() {
		return spayments;
	}

	public void setSpayments(List<PaymentShopEntity> spayments) {
		this.spayments = spayments;
	}

	public ClientPawnEntity getClient() {
		return client;
	}

	public void setClient(ClientPawnEntity client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
