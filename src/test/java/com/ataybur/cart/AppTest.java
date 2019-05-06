package com.ataybur.cart;

import java.io.IOException;

import com.ataybur.cart.model.Campaign;
import com.ataybur.cart.model.Cart;
import com.ataybur.cart.model.Category;
import com.ataybur.cart.model.Coupon;
import com.ataybur.cart.model.DiscountType;
import com.ataybur.cart.model.Product;
import com.ataybur.cart.model.ShoppingCart;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest 
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * @throws IOException 
     */
    public void testSuccessGettingCampaignDiscountWhenDiscountTypeIsRate() throws IOException
    {
		Category category = new Category("food");
		Product apple = new Product("Apple", 100.0, category);
		Product almonds = new Product("Almonds", 150.0, category);
		Cart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Rate);
		shoppingCart.applyDiscounts(campaign1);
		double campaignDiscount = shoppingCart.getCampaignDiscount();
		double totalAmountAfterDiscounts = shoppingCart.getTotalAmountAfterDiscounts();
        assertEquals(90.0, campaignDiscount);
        assertEquals(360.0, totalAmountAfterDiscounts);
    }
    
    /**
     * @throws IOException 
     */
    public void testSuccessGettingCampaignDiscountWhenDiscountTypeIsAmount() throws IOException
    {
		Category category = new Category("food");
		Product apple = new Product("Apple", 100.0, category);
		Product almonds = new Product("Almonds", 150.0, category);
		Cart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Amount);
		shoppingCart.applyDiscounts(campaign1);
		double campaignDiscount = shoppingCart.getCampaignDiscount();
		double totalAmountAfterDiscounts = shoppingCart.getTotalAmountAfterDiscounts();
        assertEquals(20.0, campaignDiscount);
        assertEquals(430.0, totalAmountAfterDiscounts);
    }
    
    /**
     * @throws IOException 
     */
    public void testSuccessGettingCouponDiscountWhenDiscountTypeIsRate() throws IOException
    {
		Category category = new Category("food");
		Product apple = new Product("Apple", 100.0, category);
		Product almonds = new Product("Almonds", 150.0, category);
		Cart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
		shoppingCart.applyCoupons(coupon);
		double couponDiscount = shoppingCart.getCouponDiscount();
		double totalAmountAfterDiscounts = shoppingCart.getTotalAmountAfterDiscounts();
        assertEquals(45.0, couponDiscount);
        assertEquals(405.0, totalAmountAfterDiscounts);
    }
    
    /**
     * @throws IOException 
     */
    public void testSuccessGettingCouponDiscountWhenDiscountTypeIsAmount() throws IOException
    {
		Category category = new Category("food");
		Product apple = new Product("Apple", 100.0, category);
		Product almonds = new Product("Almonds", 150.0, category);
		Cart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		Coupon coupon = new Coupon(100, 10, DiscountType.Amount);
		shoppingCart.applyCoupons(coupon);
		double couponDiscount = shoppingCart.getCouponDiscount();
		double totalAmountAfterDiscounts = shoppingCart.getTotalAmountAfterDiscounts();
        assertEquals(10.0, couponDiscount);
        assertEquals(440.0, totalAmountAfterDiscounts);
    }
    
    /**
     * @throws IOException 
     */
    public void testSuccessGettingDeliveryCost() throws IOException
    {
		Category category = new Category("food");
		Product apple = new Product("Apple", 100.0, category);
		Product almonds = new Product("Almonds", 150.0, category);
		Cart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
		shoppingCart.applyCoupons(coupon);
		double deliveryCost = shoppingCart.getDeliveryCost();
        assertEquals(11.96, deliveryCost);
    }
    
    /**
     * @throws IOException 
     */
    public void testSuccessGettingTotalAmountAfterDiscounts() throws IOException
    {
		Category category = new Category("food");
		Product apple = new Product("Apple", 100.0, category);
		Product almonds = new Product("Almonds", 150.0, category);
		Cart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(apple, 3);
		shoppingCart.addItem(almonds, 1);
		Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Rate);
		shoppingCart.applyDiscounts(campaign1);
		Coupon coupon = new Coupon(100, 10, DiscountType.Rate);
		shoppingCart.applyCoupons(coupon);
		double totalAmountAfterDiscounts = shoppingCart.getTotalAmountAfterDiscounts();
        assertEquals(324.00, totalAmountAfterDiscounts);
    }
    
}
