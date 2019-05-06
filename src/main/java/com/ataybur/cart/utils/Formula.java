package com.ataybur.cart.utils;

import java.math.BigDecimal;

import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.utils.calculator.ICalculator;
import com.ataybur.cart.utils.calculator.NumberOfDeliveryCalculator;
import com.ataybur.cart.utils.calculator.NumberOfProductCalculator;

public class Formula implements IFormula {
	
	private final ICalculator costPerDelivery;
	private final ICalculator costPerProduct;
	private final ICalculator fixedCost;
	private final ShoppingCart instanse;
	

	public Formula(ICalculator costPerDelivery, ICalculator costPerProduct, ICalculator fixedCost,ShoppingCart instanse) {
		super();
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;
		this.instanse = instanse;
	}


	@Override
	public BigDecimal calculate() {
		ICalculator nuberOfDeliveryCalc = new NumberOfDeliveryCalculator(instanse);
		BigDecimal n1 = nuberOfDeliveryCalc.calculate();
		ICalculator operationForDelivery = new Operation().multiply(costPerDelivery, nuberOfDeliveryCalc);
		BigDecimal n2 = operationForDelivery.calculate();
		ICalculator nuberOfProductCalc = new NumberOfProductCalculator(instanse);
		BigDecimal n3 = nuberOfProductCalc.calculate();
		ICalculator operationForProduct = new Operation().multiply(costPerProduct, nuberOfProductCalc);
		BigDecimal n4 = operationForProduct.calculate();
		ICalculator result = new FormulaBuilder().startFormula().sum(operationForDelivery, operationForProduct,
				fixedCost);
		BigDecimal n5 = result.calculate();
		return result.calculate();
	}


}
