package com.ataybur.cart;

import java.io.IOException;

import com.ataybur.cart.model.Campaign;
import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.Coupon;
import com.ataybur.cart.model.DiscountType;
import com.ataybur.cart.model.Product;
import com.ataybur.cart.model.ShoppingCart;

public class App {
	public static void main(String[] args) throws IOException {
		Category category = new Category("food");
		Product apple = new Product("Apple", 100.0, category);
		Product almonds = new Product("Almonds", 150.0, category);
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Rate);
		Campaign campaign2 = new Campaign(category, 50.0, 5, DiscountType.Rate);
		Campaign campaign3 = new Campaign(category, 5.0, 5, DiscountType.Amount);
		shoppingCart.applyDiscounts(campaign1, campaign2, campaign3);
		Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
		shoppingCart.applyCoupons(coupon);
		shoppingCart.print();
	}
}
