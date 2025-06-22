package com.atmj.jsboot.services.messages;

public class Message {
	private Long idmessage;
	private String text;
	private Boolean active;
	private String datefrom;
	private String dateuntil;

	public Long getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(Long idmessage) {
		this.idmessage = idmessage;
	}

	public String getText() {
		return text;
	}

	public void setText(String message) {
		this.text = message;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateuntil() {
		return dateuntil;
	}

	public void setDateuntil(String dateuntil) {
		this.dateuntil = dateuntil;
	}

}
