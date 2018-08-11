package com.je.dbaccess.test;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.managers.JewelsManager;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class JewelsManagerTest {

	@Autowired
	private JewelsManager jewelsManager;

	@Test
	public void searchByReferenceTest() {
		Iterable<JewelEntity> jewels = jewelsManager.searchByReference("co1350");
		if (jewels != null) {
			Iterator<JewelEntity> ijewels = jewels.iterator();
			while (ijewels.hasNext()) {
				System.out.println("name:" + ijewels.next().getName());
			}
		}
	}
}
