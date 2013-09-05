package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.Client;

public interface ClientMapper {
	Collection<Client> selectClient();
	void updateClient(Client client); 
	void deleteClient(long id);
	void insertClient(Client client);	
	Client selectClientByNumber(String number);
	Client selectClientById(long id);
	int repeatRowByNumber(Client client);
	Client getClientByNumOrName(String clientName);
}
