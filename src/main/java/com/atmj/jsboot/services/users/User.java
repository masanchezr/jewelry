package com.atmj.jsboot.services.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

public class User {

	@NotEmpty(message = "{selectuser}")
	@NotNull(message = "{selectuser}")
	private String username;

	private String password;

	private boolean enabled;

	private PlaceEntity place;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}
}
