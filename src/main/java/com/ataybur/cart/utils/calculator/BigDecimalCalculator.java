package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;

public class BigDecimalCalculator implements ICalculator{
	
	private BigDecimal instance;
	
	public BigDecimalCalculator(BigDecimal instance) {
		this.instance = instance;
	}

	@Override
	public BigDecimal calculate() {
		return this.instance;
	}

}
