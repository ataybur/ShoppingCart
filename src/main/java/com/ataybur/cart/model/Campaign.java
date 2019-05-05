package com.ataybur.cart.model;

public class Campaign extends Discount {
	private Category category;
	private int itemCount;

	public Campaign(Category category, double discountRatio, int itemCount, DiscountType discountType) {
		super(discountRatio, discountType);
		this.category = category;
		this.itemCount = itemCount;
	}

	public Category getCategory() {
		return category;
	}

	public int getItemCount() {
		return itemCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + itemCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campaign other = (Campaign) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (itemCount != other.itemCount)
			return false;
		return true;
	}

}
