package com.ataybur.cart.model;

public interface Cart {
	public void addItem(Product product, int quantity);
	public void applyDiscounts(Campaign... campaigns);
	public void applyCoupons(Coupon... coupons);
	public double getTotalAmountAfterDiscounts();
	public double getCouponDiscount();
	public double getCampaignDiscount();
	public double getDeliveryCost();
	public void print();
}
