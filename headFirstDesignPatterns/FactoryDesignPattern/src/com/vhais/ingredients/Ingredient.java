package com.vhais.ingredients;

public abstract class Ingredient {
	public Ingredient() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Adding " + getClass().getSimpleName().replaceAll("(.)([A-Z])", "$1 $2");
	}
}
