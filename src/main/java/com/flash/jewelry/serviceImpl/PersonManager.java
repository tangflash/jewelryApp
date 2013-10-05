package com.flash.jewelry.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.flash.jewelry.dao.PersonMapper;
import com.flash.jewelry.model.Person;
import com.flash.jewelry.service.PersonService;

public class PersonManager implements PersonService {
	
	@Autowired
	private PersonMapper personMapper = null;
	
	
	public PersonManager(){		
	}
	public Collection<Person> selectPerson(List list) {		
		return personMapper.selectPerson();
	}

	public void updatePerson(Person goldType) {
		personMapper.updatePerson(goldType);
	}

	public void deletePerson(long id) {
		personMapper.deletePerson(id);
	}

	public void insertPerson(Person person) {
		personMapper.insertPerson(person);

	}

	public boolean isRepeat(Person goldType) {
		//goldType goldType = personMapper.selectgoldTypeByNumber(number);
		int rowCount = personMapper.repeatRowByNumber(goldType);
		if (rowCount > 0)
			return true;
		else
			return false;
	}

	public Person selectPersonById(long id) {
		return personMapper.selectPersonById(id);
	}
	public Person selectPersonByNum(String num) {		
		return personMapper.selectPersonByNumber(num);
	}
	public Person getPersonByNumOrName(String personName) {
		return personMapper.getPersonByNumOrName(personName);
	}	

}
