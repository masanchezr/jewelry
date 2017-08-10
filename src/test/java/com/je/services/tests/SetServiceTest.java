package com.je.services.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.MetalEntity;
import com.je.services.sets.NewSet;
import com.je.services.sets.SetService;

/**
 * The Class SetServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class SetServiceTest {

	/** The set service. */
	@Autowired
	private SetService setService;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		NewSet set = new NewSet();
		MetalEntity metal = new MetalEntity();
		set.setReferenceearrings("p148");
		set.setReferencering("084");
		metal.setIdmetal(1L);
		set.setMetal(metal);
		// setService.saveSet(set);
	}
}
