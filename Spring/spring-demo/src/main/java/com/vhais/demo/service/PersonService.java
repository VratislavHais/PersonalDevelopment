package com.vhais.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vhais.demo.dao.PersonDao;
import com.vhais.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
	private final PersonDao personDao;

	@Autowired
	public PersonService(@Qualifier("inmemory") PersonDao personDao) {
		this.personDao = personDao;
	}

	public int insertPerson(Person person) {
		return personDao.insertPerson(person);
	}

	public List<Person> getPeople() {
		return personDao.getPeople();
	}

	public Optional<Person> getPerson(UUID id) {
		return this.personDao.getPerson(id);
	}

	public void deletePerson(UUID id) {
		this.personDao.deletePerson(id);
	}

	public int updatePerson(UUID id, Person newPerson) {
		return this.personDao.updatePerson(id, newPerson);
	}
}
