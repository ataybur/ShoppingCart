package com.ataybur.cart.utils;

import com.ataybur.cart.utils.calculator.ICalculator;

public interface IOperation {
	public ICalculator sum(ICalculator... calculators);
	public ICalculator multiply(ICalculator... calculators);

}
