package com.ataybur.cart.utils;

import java.math.BigDecimal;

public class NumberUtils {
	public static BigDecimal dividePercent(double amount, double percent) {
		BigDecimal amountBD = new BigDecimal(amount);
		BigDecimal percentBD = new BigDecimal(percent);
		BigDecimal hundred = new BigDecimal(100);
		return amountBD.divide(hundred).multiply(percentBD);
	}
	
	public static BigDecimal subtract(double first, double second) {
		BigDecimal firstBD = new BigDecimal(first);
		BigDecimal secondBD = new BigDecimal(second);
		return firstBD.subtract(secondBD);
	}
	
	public static boolean isFirstOneBiggerOrEqual(BigDecimal first, double second) {
		BigDecimal secondBD = new BigDecimal(second);
		int value = first.compareTo(secondBD);
		return value == 1;
	}
}
