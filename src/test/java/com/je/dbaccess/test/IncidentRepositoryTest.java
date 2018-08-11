package com.je.dbaccess.test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.IncidentEntity;
import com.je.dbaccess.entities.UserEntity;
import com.je.dbaccess.repositories.IncidentRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class IncidentRepositoryTest {

	@Autowired
	private IncidentRepository incidentsRepository;

	@Test
	public void saveTest() {
		IncidentEntity arg0 = new IncidentEntity();
		UserEntity user = new UserEntity();
		user.setUsername("13700");
		arg0.setCreationdate(new Date());
		arg0.setUsername(user);
		arg0.setDescription("prueba 32€ harta del simbolo euro");
		arg0.setState(Boolean.FALSE);
		// incidentsRepository.save(arg0);
	}

	@Autowired
	public void allincidentsTest() {
		incidentsRepository.findAll();
	}
}
