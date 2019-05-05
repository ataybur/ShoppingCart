package com.ataybur.cart.model;

public abstract class Discount {
	private double discountRatio;
	private DiscountType discountType;

	public Discount(double discountRatio, DiscountType discountType) {
		super();
		this.discountRatio = discountRatio;
		this.discountType = discountType;
	}

	public double getDiscountRatio() {
		return discountRatio;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(discountRatio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((discountType == null) ? 0 : discountType.hashCode());
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
		Discount other = (Discount) obj;
		if (Double.doubleToLongBits(discountRatio) != Double.doubleToLongBits(other.discountRatio))
			return false;
		if (discountType != other.discountType)
			return false;
		return true;
	}

}
