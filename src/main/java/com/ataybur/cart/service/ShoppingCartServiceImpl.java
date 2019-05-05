package com.ataybur.cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ataybur.cart.model.Campaign;
import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.Product;
import com.ataybur.cart.model.ProductInCart;
import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.utils.calculator.DiscountTypeCalculator;
import com.ataybur.cart.utils.calculator.ICalculator;
import com.ataybur.cart.utils.calculator.ProductInCartCalculator;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public Set<Category> getCategorySetFromShoppingCart(ShoppingCart cart) {
		List<ProductInCart> list = cart.getItemList();
		Set<Category> resultSet = new HashSet<>();
		if (list != null) {
			for (ProductInCart productInCart : list) {
				if (productInCart != null && productInCart.getProduct() != null
						&& productInCart.getProduct().getCategory() != null) {
					resultSet.add(productInCart.getProduct().getCategory());
				}
			}
		}
		return resultSet;
	}

	@Override
	public Set<Product> getProductSetFromShoppingCart(ShoppingCart cart) {
		List<ProductInCart> list = cart.getItemList();
		Set<Product> resultSet = new HashSet<>();
		if (list != null) {
			for (ProductInCart productInCart : list) {
				if (productInCart != null && productInCart.getProduct() != null) {
					resultSet.add(productInCart.getProduct());
				}
			}
		}
		return resultSet;
	}

	@Override
	public Map<Category, Integer> getCategoryCountMapFromShoppingCart(ShoppingCart cart) {
		List<ProductInCart> list = cart.getItemList();
		Map<Category, Integer> resultMap = new HashMap<>();
		if (list != null) {
			for (ProductInCart productInCart : list) {
				if (productInCart != null && productInCart.getProduct() != null
						&& productInCart.getProduct().getCategory() != null) {
					Integer count = resultMap.get(productInCart.getProduct().getCategory());
					if (count == null) {
						count = 0;
					}
					resultMap.put(productInCart.getProduct().getCategory(), count + productInCart.getQuantity());
				}
			}
		}
		return resultMap;
	}

	@Override
	public Map<Category, BigDecimal> getCategoryAmountMapFromShoppingCart(ShoppingCart cart) {
		List<ProductInCart> list = cart.getItemList();
		Map<Category, BigDecimal> resultMap = new HashMap<>();
		if (list != null) {
			for (ProductInCart productInCart : list) {
				if (productInCart != null && productInCart.getProduct() != null
						&& productInCart.getProduct().getCategory() != null) {
					Category category = productInCart.getProduct().getCategory();
					BigDecimal amount = resultMap.get(category);
					if (amount == null) {
						amount = BigDecimal.ZERO;
					}
					ICalculator productInCartCalculator = new ProductInCartCalculator(productInCart);
					resultMap.put(category, amount.add(productInCartCalculator.calculate()));
				}
			}
		}
		return resultMap;
	}
	
	@Override
	public Map<Category, List<ProductInCart>> getCategoryProductInCartMapFromShoppingCart(ShoppingCart cart) {
		List<ProductInCart> list = cart.getItemList();
		Map<Category, List<ProductInCart>> resultMap = new HashMap<>();
		if (list != null) {
			for (ProductInCart productInCart : list) {
				if (productInCart != null && productInCart.getProduct() != null
						&& productInCart.getProduct().getCategory() != null) {
					Category category = productInCart.getProduct().getCategory();
					List<ProductInCart> productList = resultMap.get(category);
					if (productList == null) {
						productList = new ArrayList<>();
					}
					productList.add(productInCart);
					resultMap.put(category, productList);
				}
			}
		}
		return resultMap;
	}

	@Override
	public Map<Category, BigDecimal> getCategoryDiscountMapForCampaignsFromShoppingCart(ShoppingCart cart) {
		Map<Category, BigDecimal> discountMap = new HashMap<>();
		List<Campaign> campaignList = cart.getCampaignList();
		Map<Category, Integer> countMap = getCategoryCountMapFromShoppingCart(cart);
		Map<Category, BigDecimal> amountMap = getCategoryAmountMapFromShoppingCart(cart);
		if (campaignList != null) {
			for (Campaign campaign : campaignList) {
				Category categoryInner = campaign.getCategory();
				Integer count = countMap.get(categoryInner);
				if (count != null) {
					if (count.intValue() >= campaign.getItemCount()) {
						BigDecimal amount = amountMap.get(categoryInner);
						BigDecimal discounted = new DiscountTypeCalculator(campaign, amount).calculate();
						BigDecimal discountValue = discountMap.get(categoryInner);
						if(discountValue == null) {
							discountValue = BigDecimal.ZERO;
						}
						discountMap.put(categoryInner, discountValue.add(discounted));
					}
				}
			}
		}
		return discountMap;
	}

}
