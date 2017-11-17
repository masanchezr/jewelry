package com.je.forms;

import java.math.BigDecimal;

import com.je.dbaccess.entities.NationEntity;
import com.je.dbaccess.entities.TrackEntity;

public class OperationForm {

	/** The description. */
	private String description;

	private String user;

	/** The creationdate. */
	private String creationdate;

	/** The amount. */
	private BigDecimal amount;

	private Long id;

	/** The nationality. */
	private NationEntity nation;

	private TrackEntity track;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
