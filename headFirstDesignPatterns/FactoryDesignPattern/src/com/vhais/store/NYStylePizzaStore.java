package com.vhais.store;

import com.vhais.ingredients.PizzaIngredientFactory;
import com.vhais.pizza.CheesePizza;
import com.vhais.pizza.ClamPizza;
import com.vhais.pizza.NYPizzaIngredientFactory;
import com.vhais.pizza.Pizza;

public class NYStylePizzaStore extends PizzaStore {
	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
		if ("cheese".equals(type)) {
			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("New York Style Cheese Pizza");
		} else if ("clam".equals(type)) {
			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("New York Style Clam Pizza");
		}
		return pizza;
	}
}
