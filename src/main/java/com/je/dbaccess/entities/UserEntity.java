package com.je.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.je.utils.constants.Constants;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@Column(name = Constants.USERNAME)
	private String username;

	@Column(name = Constants.CONTRASE)
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
