package com.vhais;

import com.vhais.beverages.Beverage;
import com.vhais.beverages.DarkRoast;
import com.vhais.beverages.Espresso;
import com.vhais.beverages.HouseBlend;
import com.vhais.condiments.Mocha;
import com.vhais.condiments.Soy;
import com.vhais.condiments.Whip;

public class Main {
	public static void log(Beverage beverage) {
		System.out.println(beverage.getDescription() + " $" + beverage.cost());
	}

	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		log(beverage);

		Beverage beverage1 = new DarkRoast();
		beverage1 = new Mocha(beverage1);
		beverage1 = new Mocha(beverage1);
		beverage1 = new Whip(beverage1);
		log(beverage1);

		Beverage beverage2 = new HouseBlend();
		beverage2 = new Soy(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		log(beverage2);
	}
}
