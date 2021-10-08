package com.vhais.ducks;

import com.vhais.behaviors.fly.FlyWithWings;
import com.vhais.behaviors.quack.Quack;

public class MallardDuck extends Duck {

	public MallardDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("I'm a mallard duck");
	}
}
