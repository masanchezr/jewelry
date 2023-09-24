package com.atmj.jsboot.services.coins;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.CoinEntity;
import com.atmj.jsboot.dbaccess.repositories.CoinsRepository;

/**
 * The Class CoinServiceImpl.
 */
@Service
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
