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

import org.springframework.data.annotation.CreatedDate;

import com.je.utils.constants.Constants;

@Entity
@Table(name = "messages")
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idmessage")
	private Long idmessage;

	@Column(name = Constants.MESSAGE)
	private String message;

	@Column(name = Constants.ACTIVE)
	private Boolean active;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	@Column(name = Constants.DATEFROM)
	private Date datefrom;

	@Column(name = Constants.DATEUNTIL)
	private Date dateuntil;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
	private UserEntity user;

	public Long getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(Long idmessage) {
		this.idmessage = idmessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}

	public Date getDateuntil() {
		return dateuntil;
	}

	public void setDateuntil(Date dateuntil) {
		this.dateuntil = dateuntil;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
