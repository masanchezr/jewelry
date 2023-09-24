package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class DailyEntity.
 */
@Entity
@Table(name = "dailies")
public class DailyEntity {

	/** The iddaily. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDDAILY")
	private Long iddaily;

	/** The finalamount. */
	@Column(name = "FINALAMOUNT")
	private BigDecimal finalamount;

	/** The dailydate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "DAILYDATE")
	private Date dailydate;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	@Column(name = "IPADDRESS")
	private String ipaddress;

	/**
	 * Gets the iddaily.
	 *
	 * @return the iddaily
	 */
	public Long getIddaily() {
		return iddaily;
	}

	/**
	 * Sets the iddaily.
	 *
	 * @param iddaily the new iddaily
	 */
	public void setIddaily(Long iddaily) {
		this.iddaily = iddaily;
	}

	/**
	 * Gets the finalamount.
	 *
	 * @return the finalamount
	 */
	public BigDecimal getFinalamount() {
		return finalamount;
	}

	/**
	 * Sets the finalamount.
	 *
	 * @param finalamount the new finalamount
	 */
	public void setFinalamount(BigDecimal finalamount) {
		this.finalamount = finalamount;
	}

	/**
	 * Gets the dailydate.
	 *
	 * @return the dailydate
	 */
	public Date getDailydate() {
		return dailydate;
	}

	/**
	 * Sets the dailydate.
	 *
	 * @param dailydate the new dailydate
	 */
	public void setDailydate(Date dailydate) {
		this.dailydate = dailydate;
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

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
}
