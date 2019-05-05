package com.ataybur.cart.utils.calculator;

import com.ataybur.cart.model.ShoppingCart;

public abstract class AbstractShoppingCartCalculator implements ICalculator {
	private ShoppingCart instance;

	public AbstractShoppingCartCalculator(ShoppingCart instance) {
		this.instance = instance;
	}

	public ShoppingCart getInstance() {
		return instance;
	}

}
