package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The Class ShoppingEntity.
 */
@Entity
@Table(name = "shoppings")
public class ShoppingEntity {

	/** The idshop. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@CreatedDate
	private Date creationdate;

	/** The creationdate. */
	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "MELTDATE")
	private Date meltdate;

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

	@ElementCollection
	@JoinColumn(name = "IDSHOP")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
	 * @param idshop the new idshop
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
	 * @param numshop the new numshop
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
	 * @param totalamount the new totalamount
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
	 * @param creationdate the new creationdate
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
	 * @param place the new place
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
	 * @param objects the new objects
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

	public Date getMeltdate() {
		return meltdate;
	}

	public void setMeltdate(Date meltdate) {
		this.meltdate = meltdate;
	}

}
