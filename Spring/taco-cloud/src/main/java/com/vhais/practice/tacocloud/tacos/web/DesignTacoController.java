package com.vhais.practice.tacocloud.tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vhais.practice.tacocloud.tacos.Ingredient;
import com.vhais.practice.tacocloud.tacos.Ingredient.Type;
import com.vhais.practice.tacocloud.tacos.Taco;
import com.vhais.practice.tacocloud.tacos.data.IngredientRepository;

import javax.validation.Valid;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	private final IngredientRepository ingredientRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> ingredients = ingredientRepository.findAll();
		Type[] types = Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("taco", new Taco());
		return "design";
	}

	@PostMapping
	public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
		if (errors.hasErrors()) {
			return "design";
		}
		taco.getIngredients().stream().map(ingredientRef -> ingredientRepository.findById(ingredientRef.getIngredient()).orElse(null))
				.filter(Objects::nonNull)
				.forEach(ingredientRepository::save);
		log.info("Processing taco: " + taco);
		return "redirect:/orders/current";
	}

	private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
		return StreamSupport.stream(ingredients.spliterator(), false).filter(ingredient -> ingredient.getType().equals(type))
				.collect(Collectors.toList());
	}
}
