package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.Person;

public interface PersonMapper {
	Collection<Person> selectPerson();
	void updatePerson(Person person); 
	void deletePerson(long id);
	void insertPerson(Person person);	
	Person selectPersonByNumber(String number);
	Person selectPersonById(long id);
	int repeatRowByNumber(Person person);
	Person getPersonByNumOrName(String personName);
}
