package com.ataybur.cart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CartProperties {
	private Properties instance;

	public CartProperties(String fileName) throws IOException {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			this.instance = new Properties();
			String rootPath = classLoader.getResource(fileName).getPath();
			this.instance.load(new FileInputStream(rootPath));
		} catch (IOException e) {
			throw e;
		}
	}
	
	public double getDouble(String propertyName) {
		double result = 0d;
		String property = this.instance.getProperty(propertyName);
		if(property != null && !property.trim().isEmpty()) {
			result = Double.valueOf(property).doubleValue();
		}
		return result;
	}

}
