package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;
import java.util.List;

import com.ataybur.cart.model.Coupon;
import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.utils.NumberUtils;

public class CouponDiscountCalculator extends AbstractShoppingCartCalculator {
	private final BigDecimal discounttedTotalAmount;

	public CouponDiscountCalculator(ShoppingCart instance, BigDecimal discounttedTotalAmount) {
		super(instance);
		this.discounttedTotalAmount = discounttedTotalAmount;
	}

	public CouponDiscountCalculator(ShoppingCart instance) {
		super(instance);
		this.discounttedTotalAmount = null;
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal totalAmount = this.discounttedTotalAmount;
		if (totalAmount == null) {
			ICalculator totalAmountCalc = new TotalAmountBeforeDiscountsCalculator(getInstance());
			ICalculator campaignCalc = new CampaignDiscountsCalculator(getInstance());
			totalAmount = totalAmountCalc.calculate().subtract(campaignCalc.calculate());
		}
		List<Coupon> list = getInstance().getCouponList();
		BigDecimal result = BigDecimal.ZERO;
		for (Coupon coupon : list) {
			boolean shouldBeDiscountApplied = NumberUtils.isFirstOneBiggerOrEqual(totalAmount,
					coupon.getMinimumPurchaseAmount());
			if (shouldBeDiscountApplied) {
				ICalculator discountCalculator = new DiscountTypeCalculator(coupon, totalAmount);
				BigDecimal discounted = discountCalculator.calculate();
				result = result.add(discounted);
				totalAmount = totalAmount.subtract(discounted);
			}
		}
		return result;
	}

}
