package com.ataybur.cart.utils.printable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.ProductInCart;
import com.ataybur.cart.model.ShoppingCart;
import com.ataybur.cart.service.ShoppingCartService;
import com.ataybur.cart.service.ShoppingCartServiceImpl;
import com.ataybur.cart.utils.calculator.CouponDiscountCalculator;
import com.ataybur.cart.utils.calculator.ICalculator;

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
			ICalculator discountCalculatorForCoupon = new CouponDiscountCalculator(instance, amountPerCategory.subtract(discountPerCategory));
			BigDecimal discountForCoupon = discountCalculatorForCoupon.calculate();
			String amountLine = String.format("Total Price: %.2f, Total Discount: (%.2f, %.2f)", amountPerCategory.doubleValue(),discountPerCategory.doubleValue(),discountForCoupon.doubleValue());
			for (ProductInCart productInCart : entry.getValue()) {
				ProductInCartPrintable productInCartPrintable = new ProductInCartPrintable(productInCart);
				String line = productInCartPrintable.print() + amountLine + System.lineSeparator();
				sb.append(line);
			}
		}
		double totalAmountAfterDiscounts = this.instance.getTotalAmountAfterDiscounts();
		double deliveryCost = this.instance.getDeliveryCost();
		String totalAmountLine = String.format("Total Amount: %.2f, Delivery Cost: %.2f", totalAmountAfterDiscounts,deliveryCost);
		sb.append(totalAmountLine);
		return sb.toString();

	}

}
