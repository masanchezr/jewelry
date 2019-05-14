package com.je.dbaccess.entities;

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
 * The Class HolidayEntity.
 */
@Entity
@Table(name = "holidays")
public class HolidayEntity {

	/** The idholiday. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHOLIDAY")
	private Long idholiday;

	/** The holiday. */
	@Temporal(TemporalType.DATE)
	@Column(name = "HOLIDAY")
	private Date holiday;

	@Column(name = "DESCRIPTION")
	private String description;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/**
	 * Gets the idholiday.
	 *
	 * @return the idholiday
	 */
	public Long getIdholiday() {
		return idholiday;
	}

	/**
	 * Sets the idholiday.
	 *
	 * @param idholiday the new idholiday
	 */
	public void setIdholiday(Long idholiday) {
		this.idholiday = idholiday;
	}

	/**
	 * Gets the holiday.
	 *
	 * @return the holiday
	 */
	public Date getHoliday() {
		return holiday;
	}

	/**
	 * Sets the holiday.
	 *
	 * @param holiday the new holiday
	 */
	public void setHoliday(Date holiday) {
		this.holiday = holiday;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
