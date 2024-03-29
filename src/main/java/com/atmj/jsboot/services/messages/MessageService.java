package com.atmj.jsboot.services.messages;

import java.util.List;

import com.atmj.jsboot.dbaccess.entities.MessageEntity;

public interface MessageService {

	public List<MessageEntity> getMessagesActiveNow(String user);

	public Iterable<MessageEntity> getAllMessages();

	public void save(Message message);

	public MessageEntity findById(Long idmessage);

}
