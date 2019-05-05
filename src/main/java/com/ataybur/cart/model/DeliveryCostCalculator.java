package com.ataybur.cart.model;

import com.ataybur.cart.utils.FormulaBuilder;
import com.ataybur.cart.utils.Operation;
import com.ataybur.cart.utils.calculator.DoubleCalculator;
import com.ataybur.cart.utils.calculator.ICalculator;
import com.ataybur.cart.utils.calculator.NumberOfDeliveryCalculator;
import com.ataybur.cart.utils.calculator.NumberOfProductCalculator;

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
		ICalculator nuberOfDeliveryCalc = new NumberOfDeliveryCalculator(cart);
		ICalculator operationForDelivery = new Operation().multiply(costPerDelivery, nuberOfDeliveryCalc);
		ICalculator nuberOfProductCalc = new NumberOfProductCalculator(cart);
		ICalculator operationForProduct = new Operation().multiply(costPerProduct, nuberOfProductCalc);
		ICalculator result = new FormulaBuilder().startFormula().sum(operationForDelivery, operationForProduct,
				fixedCost);
		return result.calculate().doubleValue();
	}

}
