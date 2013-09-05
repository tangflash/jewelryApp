package com.flash.jewelry.service;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.model.Client;

public interface ClientService {
	Collection<Client> selectClient(List list);
	Client selectClientById(long id);
	void updateClient(Client client); 
	void deleteClient(long id);
	void insertClient(Client client);
	boolean isRepeat(Client client);
	Client selectClientByNum(String num);
	Client getClientByNumOrName(String clientName);
}
