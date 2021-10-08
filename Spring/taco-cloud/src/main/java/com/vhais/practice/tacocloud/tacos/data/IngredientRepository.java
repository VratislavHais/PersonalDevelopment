package com.vhais.practice.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vhais.practice.tacocloud.tacos.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
