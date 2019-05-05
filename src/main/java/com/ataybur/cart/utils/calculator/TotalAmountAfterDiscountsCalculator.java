package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;

import com.ataybur.cart.model.ShoppingCart;

public class TotalAmountAfterDiscountsCalculator extends AbstractShoppingCartCalculator {

	public TotalAmountAfterDiscountsCalculator(ShoppingCart instance) {
		super(instance);
	}

	@Override
	public BigDecimal calculate() {
		ICalculator cartTotalAmountCalculator = new TotalAmountBeforeDiscountsCalculator(getInstance());
		BigDecimal totalAmount = cartTotalAmountCalculator.calculate();
		BigDecimal campaignDiscount = new CampaignDiscountsCalculator(getInstance()).calculate();
		totalAmount = totalAmount.subtract(campaignDiscount);
		BigDecimal couponDiscount = new CouponDiscountCalculator(getInstance(), totalAmount).calculate();
		return totalAmount.subtract(couponDiscount);
	}

}
