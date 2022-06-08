package com.supplyinterview.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverUtilities {
	
	private static final String PROPS_FILE = "configure.properties";
	private WebDriver driver;
	
	
	public WebDriver getDriver() {
		
		if(driver == null) {
			configureDriver();
			driver = new ChromeDriver();
		}
		return driver;
	
	}


	private void configureDriver() {
		
		String driverTarget;
		Properties config = new Properties();
		
		try {
			config.load(new FileInputStream(PROPS_FILE));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driverTarget = config.getProperty("driver");
		System.setProperty("selenium.chrome.driver", driverTarget);
		
	}


}