package com.ataybur.cart.utils;

import java.math.BigDecimal;

import com.ataybur.cart.utils.calculator.ICalculator;

public class Formula implements ICalculator {
	
	private ICalculator instance;
	
	public Formula(ICalculator instance) {
		this.instance = instance;
	}

	@Override
	public BigDecimal calculate() {
		return this.instance.calculate();
	}


}
