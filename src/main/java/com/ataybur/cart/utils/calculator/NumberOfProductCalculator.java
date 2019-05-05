package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;
import java.util.Set;

import com.ataybur.cart.model.Product;
import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.service.ShoppingCartService;
import com.ataybur.cart.service.ShoppingCartServiceImpl;

public class NumberOfProductCalculator extends AbstractShoppingCartCalculator {

	
	public NumberOfProductCalculator(ShoppingCart instance) {
		super(instance);
	}

	@Override
	public BigDecimal calculate() {
		ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
		Set<Product> productSet = shoppingCartService.getProductSetFromShoppingCart(getInstance());
		return new BigDecimal(productSet.size());
	}

}
