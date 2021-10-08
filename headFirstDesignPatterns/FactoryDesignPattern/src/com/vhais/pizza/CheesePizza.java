package com.vhais.pizza;

import com.vhais.ingredients.PizzaIngredientFactory;
import com.vhais.pizza.Pizza;

public class CheesePizza extends Pizza {
	private PizzaIngredientFactory ingredientFactory;

	public CheesePizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}

	@Override
	public void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
	}
}
