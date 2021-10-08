package com.vhais.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vhais.demo.model.Person;
import com.vhais.demo.service.PersonService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	public int insertPerson(@Valid @NotNull @RequestBody Person person) {
		return personService.insertPerson(person);
	}

	@GetMapping
	public List<Person> getPeople() {
		return personService.getPeople();
	}

	@GetMapping(path = "{id}")
	public Optional<Person> getPerson(@PathVariable("id")UUID id) {
		return personService.getPerson(id);
	}

	@DeleteMapping(path = "{id}")
	public void deletePerson(@PathVariable("id")UUID id) {
		personService.deletePerson(id);
	}

	@PutMapping(path = "{id}")
	public int updatePerson(@PathVariable("id")UUID id, @Valid @NotNull @RequestBody Person newPerson) {
		return personService.updatePerson(id, newPerson);
	}
}
