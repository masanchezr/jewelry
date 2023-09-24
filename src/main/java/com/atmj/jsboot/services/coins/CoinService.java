package com.atmj.jsboot.services.coins;

import com.atmj.jsboot.dbaccess.entities.CoinEntity;

/**
 * The Interface CoinService.
 */
public interface CoinService {

	/**
	 * Save.
	 *
	 * @param coin
	 *            the coin
	 */
	public void save(CoinEntity coin);

	/**
	 * All coins.
	 *
	 * @return the list
	 */
	public Iterable<CoinEntity> allCoins();

}
