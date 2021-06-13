package com.je.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.springframework.data.annotation.CreatedDate;

/**
 * The Class PawnEntity.
 */
@Entity
@Table(name = "pawns")
public class PawnEntity {

	/** The idpawn. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPAWN")
	private Long idpawn;

	/** The creationdate. */
	@CreatedDate
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

	/** The amount. */
	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The retired. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DATERETIRED")
	private Date dateretired;

	/** The renovations. */
	@JoinColumn(name = "IDPAWN")
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<RenovationEntity> renovations;

	/** The percent. */
	@Column(name = "PERCENT")
	private BigDecimal percent;

	/** The numpawn. */
	@Column(name = "NUMPAWN")
	private String numpawn;

	/** The objects. */
	@JoinColumn(name = "IDPAWN")
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ObjectPawnEntity> objects;

	/** The months. */
	@Column(name = "MONTHS")
	private Integer months;

	@Column(name = "YEAR")
	private Integer year;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "MELTDATE")
	private Date meltdate;

	/**
	 * Nos informa si ha sido reempeñado
	 */
	@Column(name = "RETURNPAWN")
	private Boolean returnpawn;

	/**
	 * Nos informa el id del empeño del que hemos reempeñado
	 */
	@Column(name = "IDRETURNPAWN")
	private Long idreturnpawn;

	/**
	 * @return the idreturnpawn
	 */
	public Long getIdreturnpawn() {
		return idreturnpawn;
	}

	/**
	 * @param idreturnpawn the idreturnpawn to set
	 */
	public void setIdreturnpawn(Long idreturnpawn) {
		this.idreturnpawn = idreturnpawn;
	}

	/**
	 * @return the returnpawn
	 */
	public Boolean getReturnpawn() {
		return returnpawn;
	}

	/**
	 * @param returnpawn the returnpawn to set
	 */
	public void setReturnpawn(Boolean returnpawn) {
		this.returnpawn = returnpawn;
	}

	/**
	 * Gets the idpawn.
	 *
	 * @return the idpawn
	 */
	public Long getIdpawn() {
		return idpawn;
	}

	/**
	 * Sets the idpawn.
	 *
	 * @param idpawn the new idpawn
	 */
	public void setIdpawn(Long idpawn) {
		this.idpawn = idpawn;
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
	 * Gets the renovations.
	 *
	 * @return the renovations
	 */
	public List<RenovationEntity> getRenovations() {
		return renovations;
	}

	/**
	 * Sets the renovations.
	 *
	 * @param renovations the new renovations
	 */
	public void setRenovations(List<RenovationEntity> renovations) {
		this.renovations = renovations;
	}

	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	public ClientPawnEntity getClient() {
		return client;
	}

	/**
	 * Sets the client.
	 *
	 * @param client the new client
	 */
	public void setClient(ClientPawnEntity client) {
		this.client = client;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the retired.
	 *
	 * @return the retired
	 */
	public Date getDateretired() {
		return dateretired;
	}

	/**
	 * Sets the retired.
	 *
	 * @param retired the new retired
	 */
	public void setDateretired(Date retired) {
		this.dateretired = retired;
	}

	/**
	 * Gets the percent.
	 *
	 * @return the percent
	 */
	public BigDecimal getPercent() {
		return percent;
	}

	/**
	 * Sets the percent.
	 *
	 * @param percent the new percent
	 */
	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	/**
	 * Gets the numpawn.
	 *
	 * @return the numpawn
	 */
	public String getNumpawn() {
		return numpawn;
	}

	/**
	 * Sets the numpawn.
	 *
	 * @param numpawn the new numpawn
	 */
	public void setNumpawn(String numpawn) {
		this.numpawn = numpawn;
	}

	/**
	 * Gets the objects.
	 *
	 * @return the objects
	 */
	public List<ObjectPawnEntity> getObjects() {
		return objects;
	}

	/**
	 * Sets the objects.
	 *
	 * @param objects the new objects
	 */
	public void setObjects(List<ObjectPawnEntity> objects) {
		this.objects = objects;
	}

	/**
	 * Gets the months.
	 *
	 * @return the months
	 */
	public Integer getMonths() {
		return months;
	}

	/**
	 * Sets the months.
	 *
	 * @param months the new months
	 */
	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getMeltdate() {
		return meltdate;
	}

	public void setMeltdate(Date meltdate) {
		this.meltdate = meltdate;
	}

}
