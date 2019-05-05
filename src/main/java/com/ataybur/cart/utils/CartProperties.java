package com.ataybur.cart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CartProperties {
	private Properties instance;

	public CartProperties(String fileName) throws IOException {
		try {
			this.instance = new Properties();
			String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String appConfigPath = rootPath + fileName;
			this.instance.load(new FileInputStream(appConfigPath));
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
