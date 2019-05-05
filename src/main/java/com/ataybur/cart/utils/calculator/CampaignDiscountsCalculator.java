package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;
import java.util.Map;

import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.service.ShoppingCartService;
import com.ataybur.cart.service.ShoppingCartServiceImpl;

public class CampaignDiscountsCalculator extends AbstractShoppingCartCalculator {

	public CampaignDiscountsCalculator(ShoppingCart instance) {
		super(instance);
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal result = BigDecimal.ZERO;
		ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
		Map<Category, BigDecimal> discountMap = shoppingCartService.getCategoryDiscountMapForCampaignsFromShoppingCart(getInstance());
		for(BigDecimal value : discountMap.values()) {
			result = result.add(value);
		}
		return result;
	}

}
