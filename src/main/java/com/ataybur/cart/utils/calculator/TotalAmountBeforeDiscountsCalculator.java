package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;
import java.util.List;

import com.ataybur.cart.model.ProductInCart;
import com.ataybur.cart.model.ShoppingCart;

public class TotalAmountBeforeDiscountsCalculator extends AbstractShoppingCartCalculator {

	public TotalAmountBeforeDiscountsCalculator(ShoppingCart instance) {
		super(instance);
	}

	@Override
	public BigDecimal calculate() {
		List<ProductInCart> list = getInstance().getItemList();
		BigDecimal result = BigDecimal.ZERO;
		for (ProductInCart productInCart : list) {
			ICalculator calculator = new ProductInCartCalculator(productInCart);
			result.add(calculator.calculate());
		}
		return result;
	}

}
