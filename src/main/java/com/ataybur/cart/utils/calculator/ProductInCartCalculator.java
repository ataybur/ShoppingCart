package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;

import com.ataybur.cart.model.ProductInCart;

public class ProductInCartCalculator implements ICalculator {

	private final ProductInCart instance;

	public ProductInCartCalculator(ProductInCart instance) {
		this.instance = instance;
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal productPrice = new BigDecimal(instance.getProduct().getPrice());
		BigDecimal productQuantity = new BigDecimal(instance.getQuantity());
		return productPrice.multiply(productQuantity);
	}

}
