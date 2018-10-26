package com.je.services.connections;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ConnectionEntity;
import com.je.dbaccess.entities.IPEntity;
import com.je.dbaccess.repositories.ConnectionsRespository;
import com.je.dbaccess.repositories.IpRepository;

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
