package com.ataybur.cart.utils;

import java.math.BigDecimal;

import com.ataybur.cart.utils.calculator.BigDecimalCalculator;
import com.ataybur.cart.utils.calculator.ICalculator;

public class Operation implements IOperation {

	@Override
	public ICalculator sum(ICalculator... calculators) {
		BigDecimal result = BigDecimal.ZERO;
		for (ICalculator calculator : calculators) {
			result = result.add(calculator.calculate());
		}
		return new BigDecimalCalculator(result);
	}

	@Override
	public ICalculator multiply(ICalculator... calculators) {
		BigDecimal result = BigDecimal.ZERO;
		for (ICalculator calculator : calculators) {
			result = result.multiply(calculator.calculate());
		}
		return new BigDecimalCalculator(result);
	}

}
