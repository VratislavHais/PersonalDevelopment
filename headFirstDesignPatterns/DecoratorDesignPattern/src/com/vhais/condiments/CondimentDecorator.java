package com.vhais.condiments;

import com.vhais.beverages.Beverage;

public abstract class CondimentDecorator extends Beverage {
	Beverage beverage;
	public abstract String getDescription();
}
