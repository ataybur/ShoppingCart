package com.ataybur.cart;

import java.io.IOException;

import com.ataybur.cart.model.Campaign;
import com.ataybur.cart.model.Cart;
import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.Coupon;
import com.ataybur.cart.model.DiscountType;
import com.ataybur.cart.model.Product;
import com.ataybur.cart.model.ShoppingCart;

public class App {
	public static void main(String[] args) throws IOException {
		Category categoryFood = new Category("food");
		Category categoryElectronic = new Category("electronic");
		Category categoryMusic = new Category("music");
		Category categoryBook = new Category("book");
		Product apple = new Product("Elma", 100.0, categoryFood);
		Product almonds = new Product("Badem", 150.0, categoryFood);
		Product headset = new Product("HeadSet", 432.0, categoryElectronic);
		Product cd = new Product("Hayvanlar / Yasemin Mori", 23.0, categoryMusic);
		Product cd2 = new Product("Led Zeppelin III (Deluxe Edition) SERI / Led Zeppelin", 59.0, categoryMusic);
		Product kitap = new Product("Varlık ve Zaman / Martin Heidegger", 49.0, categoryBook);
		Product kitap2 = new Product("Spinoza - Pratik Felsefe / Gilles Deleuze", 28.0, categoryBook);
		Cart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		shoppingCart.addItem(headset, 2);
		shoppingCart.addItem(cd, 1);
		shoppingCart.addItem(kitap, 1);
		shoppingCart.addItem(kitap2, 1);
		shoppingCart.addItem(cd2, 2);
		
		Campaign campaign1 = new Campaign(categoryFood, 20.0, 3, DiscountType.Rate);
		Campaign campaign2 = new Campaign(categoryFood, 50.0, 5, DiscountType.Rate);
		Campaign campaign3 = new Campaign(categoryFood, 5.0, 5, DiscountType.Amount);
		Campaign campaign4 = new Campaign(categoryBook, 50.0, 2, DiscountType.Rate);
		Campaign campaign5 = new Campaign(categoryMusic, 5.0, 2, DiscountType.Amount);
		shoppingCart.applyDiscounts(campaign1, campaign2, campaign3,campaign4,campaign5);
		Coupon enflasyonlaMucadeleIndirimi = new Coupon(100, 10, DiscountType.Rate);
		Coupon budaBendenOlsunDaAyri = new Coupon(50, 5, DiscountType.Amount);
		shoppingCart.applyCoupons(enflasyonlaMucadeleIndirimi,budaBendenOlsunDaAyri);
		shoppingCart.print();
	}
}
