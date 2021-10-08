package com.vratislav.hais.learningspring.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vratislav.hais.learningspring.data.entity.Guest;
import com.vratislav.hais.learningspring.data.repository.GuestRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GuestListService {
	private final GuestRepository guestRepository;

	@Autowired
	public GuestListService(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	public List<Guest> getGuestList() {
		Iterable<Guest> guests = this.guestRepository.findAll();
		List<Guest> result = new ArrayList<>();
		guests.forEach(result::add);
		result.sort(Comparator.comparing(Guest::getLastName));
		return result;
	}
}
