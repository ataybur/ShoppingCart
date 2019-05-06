package com.ataybur.cart.utils.calculator;

import java.math.BigDecimal;

import com.ataybur.cart.model.Discount;
import com.ataybur.cart.model.DiscountType;
import com.ataybur.cart.utils.NumberUtils;

public class DiscountTypeCalculator implements ICalculator {

	private final DiscountType discountType;
	private final BigDecimal amount;
	private final double discountRatio;

	public <T extends Discount >DiscountTypeCalculator(T discount, BigDecimal amount) {
		super();
		this.discountType = discount.getDiscountType();
		this.amount = amount;
		this.discountRatio = discount.getDiscountRatio();
	}


	@Override
	public BigDecimal calculate() {
		if(amount == null || amount.doubleValue() == 0d) {
			return BigDecimal.ZERO;
		}
		BigDecimal result = BigDecimal.ZERO;
		if (discountType == DiscountType.Rate) {
			result = NumberUtils.dividePercent(amount.doubleValue(), discountRatio);
		} else if (discountType == DiscountType.Rate) {
			result = NumberUtils.subtract(amount.doubleValue(), discountRatio);
		}

		return result;
	}

}
