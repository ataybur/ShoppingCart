package com.ataybur.cart.utils.printable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.ProductInCart;
import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.service.ShoppingCartService;
import com.ataybur.cart.service.ShoppingCartServiceImpl;

public class ShoppingCartPrintable implements IPrintable {

	private final ShoppingCart instance;

	public ShoppingCartPrintable(ShoppingCart instance) {
		this.instance = instance;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		ShoppingCartService service = new ShoppingCartServiceImpl();
		Map<Category, BigDecimal> amountMap = service.getCategoryAmountMapFromShoppingCart(instance);
		Map<Category, BigDecimal> discountMap = service.getCategoryDiscountMapForCampaignsFromShoppingCart(instance);
		Map<Category, List<ProductInCart>> categoryProductInCartMap = service
				.getCategoryProductInCartMapFromShoppingCart(instance);
		for (Map.Entry<Category, List<ProductInCart>> entry : categoryProductInCartMap.entrySet()) {
			BigDecimal amountPerCategory = amountMap.getOrDefault(entry.getKey(), BigDecimal.ZERO);
			BigDecimal discountPerCategory = discountMap.getOrDefault(entry.getKey(), BigDecimal.ZERO);
			String amountLine = String.format("Total Price: %.2f, Total Campaign Discount: %.2f", amountPerCategory.doubleValue(),discountPerCategory.doubleValue());
			for (ProductInCart productInCart : entry.getValue()) {
				ProductInCartPrintable productInCartPrintable = new ProductInCartPrintable(productInCart);
				String line = productInCartPrintable.print() + amountLine + System.lineSeparator();
				sb.append(line);
			}
		}
		return sb.toString();

	}

}
