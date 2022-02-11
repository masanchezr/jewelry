package com.je.jsboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.forms.SearchForm;
import com.je.jsboot.services.incidents.Incident;
import com.je.jsboot.services.incidents.IncidentService;

@SpringBootTest
public class IncidentServiceTest {

	@Autowired
	private IncidentService incidentService;

	@Test
	void searchByUserAndDatesTest() {
		SearchForm form = new SearchForm();
		form.setDatefrom("01/01/2014");
		form.setDateuntil("31/01/2022");
		incidentService.searchByUserAndDates("SONIAMARCOS", form);
	}

	@Test
	void saveTest() {
		Incident incident = new Incident();
		incident.setUser("24002");
		incident.setDescription("pruebas");
		incidentService.save(incident);
	}
}
