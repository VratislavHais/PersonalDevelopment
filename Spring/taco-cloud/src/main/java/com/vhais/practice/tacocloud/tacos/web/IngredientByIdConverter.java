package com.vhais.practice.tacocloud.tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vhais.practice.tacocloud.tacos.Ingredient;
import com.vhais.practice.tacocloud.tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
	private IngredientRepository ingredientRepository;

	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public Ingredient convert(String s) {
		return ingredientRepository.findById(s).orElse(null);
	}
}
