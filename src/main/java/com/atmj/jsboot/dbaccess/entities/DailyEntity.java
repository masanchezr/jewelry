package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
