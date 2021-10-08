package com.vhais;

import com.vhais.behaviors.fly.FlyRocketPowered;
import com.vhais.ducks.Duck;
import com.vhais.ducks.MallardDuck;
import com.vhais.ducks.ModelDuck;

public class Main {

	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		duck.performFly();
		duck.performQuack();

		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
	}
}
