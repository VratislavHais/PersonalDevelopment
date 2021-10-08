package com.vhais.practice.tacocloud.tacos;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
@Table
@RestResource(rel = "tacos", path = "tacos")
public class Taco {
	@Id
	private Long id;
	private Date createdAt = new Date();
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
//	@ManyToMany
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<IngredientRef> ingredients;
}
