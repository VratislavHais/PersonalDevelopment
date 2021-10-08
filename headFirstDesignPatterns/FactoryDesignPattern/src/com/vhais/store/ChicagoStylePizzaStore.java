package com.vhais.store;

import com.vhais.ingredients.PizzaIngredientFactory;
import com.vhais.pizza.CheesePizza;
import com.vhais.pizza.ChicagoPizzaIngredientFactory;
import com.vhais.pizza.ClamPizza;
import com.vhais.pizza.Pizza;

public class ChicagoStylePizzaStore extends PizzaStore {
	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
		if ("cheese".equals(type)) {
			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("Chicago Style Cheese Pizza");
		} else if ("clam".equals(type)) {
			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("Chicago Style Clam Pizza");
		}
		return pizza;
	}
}
