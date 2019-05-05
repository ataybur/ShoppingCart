package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;
import java.util.Set;

import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.service.ShoppingCartService;
import com.ataybur.cart.service.ShoppingCartServiceImpl;

public class NumberOfDeliveryCalculator extends AbstractShoppingCartCalculator {


	public NumberOfDeliveryCalculator(ShoppingCart instance) {
		super(instance);
	}

	@Override
	public BigDecimal calculate() {
		ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
		Set<Category> categorySet = shoppingCartService.getCategorySetFromShoppingCart(getInstance());
		return new BigDecimal(categorySet.size());
	}

}
