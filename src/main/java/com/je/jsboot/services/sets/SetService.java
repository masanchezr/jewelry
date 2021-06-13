package com.je.jsboot.services.sets;

import java.util.List;

/**
 * The Interface SetService.
 */
public interface SetService {

	/**
	 * Save set.
	 *
	 * @param set
	 *            the set
	 */
	void saveSet(NewSet set);

	/**
	 * All sets.
	 *
	 * @return the list
	 */
	List<Set> allSets();

}
