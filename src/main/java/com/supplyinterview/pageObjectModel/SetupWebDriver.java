package com.supplyinterview.pageObjectModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetupWebDriver {
	private static final String PROPS_FILE = "config.properties";
	private WebDriver wdriver;
	public WebDriver getDriver() {
		if (wdriver == null) {
			ConfigureDriver();
		}
		return wdriver;
	}
	private void ConfigureDriver() {
		String drivertarget;
		String browser;
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
		drivertarget = config.getProperty("driver");
		browser = config.getProperty("browser");
		
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", drivertarget);
			wdriver = new ChromeDriver();
		}
	}
}