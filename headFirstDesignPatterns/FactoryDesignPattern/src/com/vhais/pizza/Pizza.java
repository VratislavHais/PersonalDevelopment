package com.vhais.pizza;

import com.vhais.ingredients.Cheese;
import com.vhais.ingredients.Clams;
import com.vhais.ingredients.Dough;
import com.vhais.ingredients.Pepperoni;
import com.vhais.ingredients.Sauce;
import com.vhais.ingredients.Veggies;

public abstract class Pizza {
	String name;
	Dough dough;
	Sauce sauce;
	Veggies[] veggies;
	Cheese cheese;
	Pepperoni pepperoni;
	Clams clam;

	public abstract void prepare();

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
