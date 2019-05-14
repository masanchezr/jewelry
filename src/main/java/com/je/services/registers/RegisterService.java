package com.je.services.registers;

import java.util.List;

import com.je.dbaccess.entities.RegisterEntity;

public interface RegisterService {

	public void register(String user, String ipaddress);

	public List<RegisterEntity> findByDates(String datefrom, String dateuntil);

}
