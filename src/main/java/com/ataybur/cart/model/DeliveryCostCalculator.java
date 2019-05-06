package com.ataybur.cart.model;

import com.ataybur.cart.utils.Formula;
import com.ataybur.cart.utils.calculator.DoubleCalculator;
import com.ataybur.cart.utils.calculator.ICalculator;

public class DeliveryCostCalculator {
	private final ICalculator costPerDelivery;
	private final ICalculator costPerProduct;
	private final ICalculator fixedCost;

	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
		super();
		this.costPerDelivery = new DoubleCalculator(costPerDelivery);
		this.costPerProduct = new DoubleCalculator(costPerProduct);
		this.fixedCost = new DoubleCalculator(fixedCost);
	}

	public double calculateForCart(ShoppingCart cart) {
		return new Formula(this.costPerDelivery, this.costPerProduct, this.fixedCost,cart).calculate().doubleValue();
	}

}
