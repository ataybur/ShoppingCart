package com.ataybur.cart.model;

public class Coupon extends Discount{
	private double minimumPurchaseAmount;

	public Coupon(double minimumPurchaseAmount, double discountRatio, DiscountType discountType) {
		super(discountRatio,discountType);
		this.minimumPurchaseAmount = minimumPurchaseAmount;
	}

	public double getMinimumPurchaseAmount() {
		return minimumPurchaseAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(minimumPurchaseAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (Double.doubleToLongBits(minimumPurchaseAmount) != Double.doubleToLongBits(other.minimumPurchaseAmount))
			return false;
		return true;
	}


}
