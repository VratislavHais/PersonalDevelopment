package com.vhais.demo.dao;

import com.vhais.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
	int insertPerson(UUID id, Person person);

	default int insertPerson(Person person) {
		return insertPerson(UUID.randomUUID(), person);
	}

	List<Person> getPeople();

	Optional<Person> getPerson(UUID id);

	void deletePerson(UUID id);

	int updatePerson(UUID id, Person newPerson);
}
