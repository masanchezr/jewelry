package com.atmj.jsboot.dbaccess.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "incidents")
public class IncidentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDINCIDENT")
	private Long idincident;

	@Column(name = "DESCRIPTION")
	private String description;

	@CreatedDate
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	@Column(name = "STATE")
	private Boolean state;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private UserEntity username;

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

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public UserEntity getUsername() {
		return username;
	}

	public void setUsername(UserEntity username) {
		this.username = username;
	}
}
