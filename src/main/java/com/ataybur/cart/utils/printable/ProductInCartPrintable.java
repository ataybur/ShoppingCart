package com.ataybur.cart.utils.printable;

import com.ataybur.cart.model.ProductInCart;

public class ProductInCartPrintable implements IPrintable {

	private ProductInCart instance;

	public ProductInCartPrintable(ProductInCart instance) {
		this.instance = instance;
	}

	@Override
	public String print() {
		return String.format("CategoryName: %s, ProductName: %s, Quantity: %d, Unit Price: %.2f ",
				instance.getProduct().getCategory().getTitle(), instance.getProduct().getTitle(),
				instance.getQuantity(), instance.getProduct().getPrice());

	}

}
