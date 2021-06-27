package com.je.jsboot.services.mails;

public interface EmailService {
	public void sendSimpleMessage(String to, String subject, String text);
}
