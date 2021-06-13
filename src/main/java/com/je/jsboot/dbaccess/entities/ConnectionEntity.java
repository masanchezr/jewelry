package com.je.jsboot.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "connections")
public class ConnectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCONNECTION")
	private Long idconnection;

	@ManyToOne
	@JoinColumn(name = "IDIP", referencedColumnName = "IDIP")
	private IPEntity ip;

	@CreatedDate
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	public Long getIdconnection() {
		return idconnection;
	}

	public void setIdconnection(Long idconnection) {
		this.idconnection = idconnection;
	}

	public IPEntity getIp() {
		return ip;
	}

	public void setIp(IPEntity ip) {
		this.ip = ip;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

}
