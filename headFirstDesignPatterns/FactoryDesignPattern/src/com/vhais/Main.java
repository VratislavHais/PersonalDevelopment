package com.vhais;

import com.vhais.pizza.Pizza;
import com.vhais.store.ChicagoStylePizzaStore;
import com.vhais.store.NYStylePizzaStore;
import com.vhais.store.PizzaStore;

public class Main {

	public static void main(String[] args) {
		PizzaStore nyStore = new NYStylePizzaStore();
		PizzaStore chicagoStore = new ChicagoStylePizzaStore();

		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");

		Pizza pizza1 = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza1.getName() + "\n");
	}
}
