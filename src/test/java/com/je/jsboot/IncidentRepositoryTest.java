package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.IncidentEntity;
import com.je.jsboot.dbaccess.entities.UserEntity;
import com.je.jsboot.dbaccess.repositories.IncidentRepository;
import com.je.jsboot.utils.date.DateUtil;

@SpringBootTest
class IncidentRepositoryTest {

	@Autowired
	private IncidentRepository incidentsRepository;

	@Test
	void saveTest() {
		IncidentEntity arg0 = new IncidentEntity();
		UserEntity user = new UserEntity();
		user.setId(1L);
		arg0.setCreationdate(new Date());
		arg0.setUsername(user);
		arg0.setDescription("prueba 32€ harta del simbolo euro");
		arg0.setState(Boolean.FALSE);
		Assertions.assertNotNull(incidentsRepository.save(arg0));
	}

	@Test
	void allincidentsTest() {
		Assertions.assertNotNull(incidentsRepository.findAll());
	}

	@Test
	void findByUsernameAndCreationdateBetweenTest() {
		UserEntity user = new UserEntity();
		Date dFrom = DateUtil.getDate("01-01-2014");
		user.setId(2L);
		incidentsRepository.findByUsernameAndCreationdateBetween(user, dFrom, new Date());
	}
}
