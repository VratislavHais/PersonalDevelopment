package com.vratislav.hais.learningspring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vratislav.hais.learningspring.business.service.GuestListService;
import com.vratislav.hais.learningspring.data.entity.Guest;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class ListAllGuestsWebController {
	private final GuestListService guestListService;

	@Autowired
	public ListAllGuestsWebController(GuestListService guestListService) {
		this.guestListService = guestListService;
	}

	@GetMapping
	public String getListOfGuests(Model model) {
		List<Guest> result = guestListService.getGuestList();
		model.addAttribute("guestsList", result);
		return "guests";
	}
}
