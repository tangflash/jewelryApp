package com.flash.jewelry.service;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.model.Person;

public interface PersonService {
	Collection<Person> selectPerson(List list);
	Person selectPersonById(long id);
	void updatePerson(Person person); 
	void deletePerson(long id);
	void insertPerson(Person person);
	boolean isRepeat(Person person);
	Person selectPersonByNum(String num);
	Person getPersonByNumOrName(String personName);
}
