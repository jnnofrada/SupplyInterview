package com.supplyinterview.pageObjectModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.supplyinterview.TestData.SupplyData;

public class SupplyHouse {
	
	public static List<String> terms = null;
	
	public static void enterSearchText(WebDriver driver, String text) throws Exception {
		driver.findElement(By.id("react-header-search-input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("react-header-search-input")).sendKeys(text, Keys.TAB);
		driver.findElement(By.id("react-header-search-input")).click();
		Thread.sleep(3000);
		try{
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//*[@class='HeaderSearchSuggestionsTitle-sc-1ddc3bz-0 iJCIVx sc-bdVaJa gtVQmG'])[1]")));
		} catch (TimeoutException n) {
			throw new Exception();
		}
	}
	
	public static List<WebElement> listSuggestions(WebDriver driver){
		return driver.findElements(By.xpath("(//*[@class='sc-bdVaJa HeaderSearchSuggestionText-h71b5o-0 emXVVU'])"));
	}
	
	public static WebElement firstSuggestion(WebDriver driver) {
		return driver.findElement(By.xpath("(//*[@class='sc-bdVaJa HeaderSearchSuggestionText-h71b5o-0 emXVVU'])[1]"));
	}

	public static List<WebElement> bolded(WebDriver driver){
		return driver.findElements(By.xpath("(//*[@class='sc-bdVaJa HeaderSearchSuggestionText-h71b5o-0 emXVVU']/b)"));
	}
	public static List<WebElement> unbolded(WebDriver driver) {
		return driver.findElements(By.xpath("(//b/span[@class='unbold'])"));
	}
	
	public static List<WebElement> listCategories(WebDriver driver){
		return driver.findElements(By.xpath("(//div[@class='sc-bdVaJa HeaderSearchSuggestionText-h71b5o-0 fRTZGa'])"));
	}
	
	public static List<String> readData(String file) throws IOException { 
	    int count = 0;
	    List<String> content = new ArrayList<>();
	    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String line = "";
	        while ((line = br.readLine()) != null) {
	        	String[] lines = line.split(",");
	            for (String term : lines) {
	            	content.add(term);
	            }
	        }
	    } catch (FileNotFoundException e) {
	      System.out.println("File does not exist");
	    }
	    return content;
	}
	
	public static void launchSupply(WebDriver wdriver) {
		wdriver.get(SupplyData.SUPPLY_URL);
	}
	
	public static void readCSV(String file) {
		try {
			terms = readData("terms.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<WebElement> listProducts(WebDriver driver) {
		return driver.findElements(By.xpath("(//div[@class='product-name HeaderSearchProductSuggestion__HeaderSearchProductSuggestionText-sc-1atmdgx-0 kFXIiX sc-bdVaJa hufUer'])"));
	}
	
	public static List<WebElement> listImages(WebDriver driver) {
		return driver.findElements(By.xpath("(//img[@class='HeaderSearchProductSuggestion__HeaderSearchProductImage-sc-1atmdgx-2 hZWqTF'])"));
	}
}
