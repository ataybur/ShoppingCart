package com.ataybur.cart.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ataybur.cart.utils.CartProperties;
import com.ataybur.cart.utils.calculator.CampaignDiscountsCalculator;
import com.ataybur.cart.utils.calculator.CouponDiscountCalculator;
import com.ataybur.cart.utils.calculator.TotalAmountAfterDiscountsCalculator;
import com.ataybur.cart.utils.printable.IPrintable;
import com.ataybur.cart.utils.printable.ShoppingCartPrintable;

public class ShoppingCart implements Cart {
	private List<ProductInCart> itemList;
	private List<Campaign> campaignList;
	private List<Coupon> couponList;
	private CartProperties properties;

	public ShoppingCart() throws IOException {
		try {
			properties = new CartProperties("application.properties");
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public void addItem(Product product, int quantity) {
		if (itemList == null) {
			itemList = new ArrayList<>();
		}
		itemList.add(new ProductInCart(product, quantity));
	}

	@Override
	public void applyDiscounts(Campaign... campaigns) {
		if (campaignList == null) {
			campaignList = new ArrayList<>();
		}
		for (Campaign campaign : campaigns) {
			campaignList.add(campaign);
		}
	}

	@Override
	public void applyCoupons(Coupon... coupons) {
		if (couponList == null) {
			couponList = new ArrayList<>();
		}
		for (Coupon coupon : coupons) {
			couponList.add(coupon);
		}
	}

	@Override
	public double getTotalAmountAfterDiscounts() {
		return new TotalAmountAfterDiscountsCalculator(this).calculate().doubleValue();
	}

	@Override
	public double getCouponDiscount() {
		return new CouponDiscountCalculator(this).calculate().doubleValue();
	}

	@Override
	public double getCampaignDiscount() {
		return new CampaignDiscountsCalculator(this).calculate().doubleValue();
	}

	@Override
	public double getDeliveryCost() {
		double costPerDelivery = properties.getDouble("costPerDelivery");
		double costPerProduct = properties.getDouble("costPerProduct");
		double fixedCost = properties.getDouble("fixedCost");
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(costPerDelivery, costPerProduct,
				fixedCost);
		return deliveryCostCalculator.calculateForCart(this);
	}

	public List<ProductInCart> getItemList() {
		return itemList;
	}

	public List<Campaign> getCampaignList() {
		return campaignList;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	@Override
	public void print() {
		IPrintable printable = new ShoppingCartPrintable(this);
		System.out.println(printable.print());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaignList == null) ? 0 : campaignList.hashCode());
		result = prime * result + ((couponList == null) ? 0 : couponList.hashCode());
		result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
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
		ShoppingCart other = (ShoppingCart) obj;
		if (campaignList == null) {
			if (other.campaignList != null)
				return false;
		} else if (!campaignList.equals(other.campaignList))
			return false;
		if (couponList == null) {
			if (other.couponList != null)
				return false;
		} else if (!couponList.equals(other.couponList))
			return false;
		if (itemList == null) {
			if (other.itemList != null)
				return false;
		} else if (!itemList.equals(other.itemList))
			return false;
		return true;
	}
}
