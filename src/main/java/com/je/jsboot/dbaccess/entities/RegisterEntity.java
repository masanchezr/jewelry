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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "register")
public class RegisterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idregister")
	private Long idregister;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column(name = "timeinmorning")
	private Date timeinmorning;

	@Temporal(TemporalType.TIME)
	@Column(name = "timeoutmorning")
	private Date timeoutmorning;

	@Temporal(TemporalType.TIME)
	@Column(name = "timeinafternoon")
	private Date timeinafternoon;

	@Temporal(TemporalType.TIME)
	@Column(name = "timeoutafternoon")
	private Date timeoutafternoon;

	@Column(name = "ipaddress")
	private String ipaddress;

	@ManyToOne
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
	private UserEntity employee;

	public Long getIdregister() {
		return idregister;
	}

	public void setIdregister(Long idinemployee) {
		this.idregister = idinemployee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Date getTimeinmorning() {
		return timeinmorning;
	}

	public void setTimeinmorning(Date timeinmorning) {
		this.timeinmorning = timeinmorning;
	}

	public Date getTimeoutmorning() {
		return timeoutmorning;
	}

	public void setTimeoutmorning(Date timeoutmorning) {
		this.timeoutmorning = timeoutmorning;
	}

	public Date getTimeinafternoon() {
		return timeinafternoon;
	}

	public void setTimeinafternoon(Date timeinafternoon) {
		this.timeinafternoon = timeinafternoon;
	}

	public Date getTimeoutafternoon() {
		return timeoutafternoon;
	}

	public void setTimeoutafternoon(Date tiemoutafternoon) {
		this.timeoutafternoon = tiemoutafternoon;
	}
}
