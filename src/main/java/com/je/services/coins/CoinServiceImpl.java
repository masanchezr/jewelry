package com.je.services.coins;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.CoinEntity;
import com.je.dbaccess.repositories.CoinsRepository;

/**
 * The Class CoinServiceImpl.
 */
public class CoinServiceImpl implements CoinService {

	/** The coins repository. */
	@Autowired
	private CoinsRepository coinsRepository;

	public void save(CoinEntity coin) {
		coin.setCreationdate(new Date());
		coinsRepository.save(coin);
	}

	public Iterable<CoinEntity> allCoins() {
		return coinsRepository.findAll();
	}
}
