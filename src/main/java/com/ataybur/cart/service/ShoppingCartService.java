package com.ataybur.cart.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.Product;
import com.ataybur.cart.model.ProductInCart;
import com.ataybur.cart.model.ShoppingCart;

public interface ShoppingCartService {
	Set<Category> getCategorySetFromShoppingCart(ShoppingCart cart);

	Map<Category, Integer> getCategoryCountMapFromShoppingCart(ShoppingCart cart);

	Set<Product> getProductSetFromShoppingCart(ShoppingCart cart);

	Map<Category, BigDecimal> getCategoryAmountMapFromShoppingCart(ShoppingCart cart);

	Map<Category, BigDecimal> getCategoryDiscountMapForCampaignsFromShoppingCart(ShoppingCart cart);

	Map<Category, List<ProductInCart>> getCategoryProductInCartMapFromShoppingCart(ShoppingCart cart);
}
