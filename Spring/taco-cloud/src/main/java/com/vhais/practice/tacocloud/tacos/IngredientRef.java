package com.vhais.practice.tacocloud.tacos;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table
public class IngredientRef {
	private final String ingredient;
}
