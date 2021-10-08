package com.vhais.pizza;

import com.vhais.ingredients.Cheese;
import com.vhais.ingredients.Clams;
import com.vhais.ingredients.Dough;
import com.vhais.ingredients.FreshClams;
import com.vhais.ingredients.Garlic;
import com.vhais.ingredients.MarinaraSauce;
import com.vhais.ingredients.Mushroom;
import com.vhais.ingredients.Onion;
import com.vhais.ingredients.Pepperoni;
import com.vhais.ingredients.PizzaIngredientFactory;
import com.vhais.ingredients.RedPepper;
import com.vhais.ingredients.ReggianoCheese;
import com.vhais.ingredients.Sauce;
import com.vhais.ingredients.SlicedPepperoni;
import com.vhais.ingredients.ThinCrustDough;
import com.vhais.ingredients.Veggies;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
	@Override
	public Dough createDough() {
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new MarinaraSauce();
	}

	@Override
	public Cheese createCheese() {
		return new ReggianoCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		return new Veggies[] {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
	}

	@Override
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	@Override
	public Clams createClams() {
		return new FreshClams();
	}
}
