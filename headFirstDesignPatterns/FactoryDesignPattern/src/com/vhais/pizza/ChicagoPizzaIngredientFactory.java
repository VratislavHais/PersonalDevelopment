package com.vhais.pizza;

import com.vhais.ingredients.BlackOlives;
import com.vhais.ingredients.Cheese;
import com.vhais.ingredients.Clams;
import com.vhais.ingredients.Dough;
import com.vhais.ingredients.EggPlant;
import com.vhais.ingredients.FrozenClams;
import com.vhais.ingredients.MozzarellaCheese;
import com.vhais.ingredients.Pepperoni;
import com.vhais.ingredients.PizzaIngredientFactory;
import com.vhais.ingredients.PlumTomatoSauce;
import com.vhais.ingredients.Sauce;
import com.vhais.ingredients.SlicedPepperoni;
import com.vhais.ingredients.Spinach;
import com.vhais.ingredients.ThickCrustDough;
import com.vhais.ingredients.Veggies;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
	@Override
	public Dough createDough() {
		return new ThickCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	@Override
	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		return new Veggies[] {new EggPlant(), new BlackOlives(), new Spinach()};
	}

	@Override
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	@Override
	public Clams createClams() {
		return new FrozenClams();
	}
}
