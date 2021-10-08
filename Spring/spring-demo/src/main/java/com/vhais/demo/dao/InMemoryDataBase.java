package com.vhais.demo.dao;

import org.springframework.stereotype.Repository;

import com.vhais.demo.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("inmemory")
public class InMemoryDataBase implements PersonDao {
	private List<Person> DB;

	public InMemoryDataBase() {
		this.DB = new ArrayList<>();
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		if (DB.add(new Person(id, person.getName()))) return 1;
		return 0;
	}

	@Override
	public List<Person> getPeople() {
		return DB;
	}

	@Override
	public Optional<Person> getPerson(UUID id) {
		return DB.stream().filter(p -> p.getId().equals(id)).findFirst();
	}

	@Override
	public void deletePerson(UUID id) {
		DB.remove(getPerson(id).orElse(null));
	}

	@Override
	public int updatePerson(UUID id, Person newPerson) {
		return getPerson(id).map(person -> {
			int indexOf = DB.indexOf(person);
			if (indexOf >= 0) {
				DB.set(indexOf, new Person(person.getId(), newPerson.getName()));
				return 1;
			}
			return 0;
		}).orElse(0);
	}
}
