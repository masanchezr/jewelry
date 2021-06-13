package com.je.jsboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.managers.JewelsManager;

@SpringBootTest
class JewelsManagerTest {

	@Autowired
	private JewelsManager jewelsManager;

	@Test
	void searchByReferenceTest() {
		Assertions.assertNotNull(jewelsManager.searchByReference("co1350"));
	}
}
