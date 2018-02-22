package com.je.services.messages;

import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.MessageEntity;
import com.je.dbaccess.entities.UserEntity;
import com.je.dbaccess.repositories.MessagesRepository;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessagesRepository messagesrepository;

	@Autowired
	private Mapper mapper;

	@Override
	public List<MessageEntity> getMessagesActiveNow(String user) {
		Date now = new Date();
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(user);
		return messagesrepository.findByUserAndActiveTrueAndDatefromBeforeAndDateuntilAfter(userEntity, now, now);
	}

	@Override
	public Iterable<MessageEntity> getAllMessages() {
		return messagesrepository.findAll();
	}

	@Override
	public void save(Message message) {
		MessageEntity entity = mapper.map(message, MessageEntity.class);
		entity.setCreationdate(new Date());
		messagesrepository.save(entity);
	}

	@Override
	public MessageEntity findById(Long idmessage) {
		return messagesrepository.findById(idmessage).orElse(null);
	}
}
