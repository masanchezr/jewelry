package com.je.jsboot.services.incidents;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.je.jsboot.utils.constants.ConstantsViews;

public class Incident {

	private Long idincident;

	@NotEmpty(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	@NotNull(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	private String description;

	private boolean state;

	private String user;

	private Date creationdate;

	public Long getIdincident() {
		return idincident;
	}

	public void setIdincident(Long idincident) {
		this.idincident = idincident;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date date) {
		this.creationdate = date;
	}
}
