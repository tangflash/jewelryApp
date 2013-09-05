package com.flash.jewelry.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.ClientMapper;
import com.flash.jewelry.model.Client;
import com.flash.jewelry.service.ClientService;

public class ClientManager implements ClientService {
	
	@Autowired
	private ClientMapper clientMapper = null;
	
	
	public ClientManager(){		
	}
	public Collection<Client> selectClient(List list) {		
		return clientMapper.selectClient();
	}

	public void updateClient(Client goldType) {
		clientMapper.updateClient(goldType);
	}

	public void deleteClient(long id) {
		clientMapper.deleteClient(id);
	}

	public void insertClient(Client goldType) {
		clientMapper.insertClient(goldType);

	}

	public boolean isRepeat(Client goldType) {
		//goldType goldType = ClientMapper.selectgoldTypeByNumber(number);
		int rowCount = clientMapper.repeatRowByNumber(goldType);
		if (rowCount > 0)
			return true;
		else
			return false;
	}

	public Client selectClientById(long id) {
		return clientMapper.selectClientById(id);
	}
	public Client selectClientByNum(String num) {		
		return clientMapper.selectClientByNumber(num);
	}
	public Client getClientByNumOrName(String clientName) {
		return clientMapper.getClientByNumOrName(clientName);
	}	

}
