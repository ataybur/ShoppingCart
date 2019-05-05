package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;

public class DoubleCalculator implements ICalculator {
	private double instance;

	public DoubleCalculator(double instance) {
		this.instance = instance;
	}

	@Override
	public BigDecimal calculate() {
		return new BigDecimal(instance);
	}

}
