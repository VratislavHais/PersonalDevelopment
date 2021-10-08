package com.vhais.ducks;

import com.vhais.behaviors.fly.FlyNoWay;
import com.vhais.behaviors.quack.Quack;

public class ModelDuck extends Duck {
	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("I'm a model duck");
	}
}
