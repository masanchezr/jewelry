package com.atmj.jsboot.services.connections;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.ConnectionEntity;
import com.atmj.jsboot.dbaccess.entities.IPEntity;
import com.atmj.jsboot.dbaccess.repositories.ConnectionsRespository;
import com.atmj.jsboot.dbaccess.repositories.IpRepository;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private IpRepository iprepository;

	@Autowired
	private ConnectionsRespository connectionsRepository;

	@Override
	public void saveConnection(String ipAddress) {
		IPEntity ip = iprepository.findByIp(ipAddress);
		ConnectionEntity connection = new ConnectionEntity();
		if (ip == null) {
			ip = new IPEntity();
			ip.setIp(ipAddress);
			ip = iprepository.save(ip);
		}
		connection.setCreationdate(new Date());
		connection.setIp(ip);
		connectionsRepository.save(connection);
	}

}
