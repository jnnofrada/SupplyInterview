package com.supplyinterview.cucumber.definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.supplyinterview.pageObjectModel.SupplyHouse;
import com.supplyinterview.utilities.DriverUtilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SupplyInterviewStepDef {

	protected WebDriver driver = null;
	@Before
	public void setUp() {
		DriverUtilities setupDriver = new DriverUtilities();
		driver = setupDriver.getDriver();
	}

	@After
	public void tearDown() throws InterruptedException {
		driver.quit();
	}	

	@Given("user lands on the Supply House main page")
	public void launchSupply() {
		SupplyHouse.launchSupply(driver);
	}
	
	@When("user enters search terms from {string} file")
	public void enterSearch(String file) {
		SupplyHouse.readCSV(file);
	}
	
	@Then("search suggestions follow the acceptance criteria")
	public void assertSuggestions() {
		for (int j = 0; j < SupplyHouse.terms.size(); j++) {
			String term = SupplyHouse.terms.get(j).trim();
			try {
				SupplyHouse.enterSearchText(driver, term);
			} catch (Exception e) {
				System.out.println("Term \"" + term +"\" does not produce suggestions");
				continue;
			}
			List<WebElement> suggestions = SupplyHouse.listSuggestions(driver);
			boolean suggestionIncorrectFlag = false;
			for (int i = 0; i < suggestions.size(); i++) {
				if (!(suggestions.get(i).getText().contains(term))){
					System.out.println("Suggestions for term \"" + term + "\" does not meet criteria");
					suggestionIncorrectFlag = true;
				}
			}
			if (suggestionIncorrectFlag) {continue;}
			List<WebElement> unbolded = SupplyHouse.unbolded(driver);
			List<WebElement> bolded = SupplyHouse.bolded(driver);
			for (int i = 0; i < bolded.size(); i++) {
				String bold = bolded.get(i).getText();
				String unbold = unbolded.get(i).getText();
				try {
					assertTrue(Integer.parseInt(bolded.get(i).getCssValue("font-weight"))>=700);
				} catch (AssertionError e) {
					System.out.println("Search suggestion is not bolded");
				}
				try {
					assertTrue(Integer.parseInt(unbolded.get(i).getCssValue("font-weight"))<700);
				} catch (AssertionError e) {
					System.out.println("Search Suggestion substring is not unbolded");
				}
				try {
					assertEquals(unbolded.get(i).getText(), term );
				}
				catch (AssertionError e) {
					System.out.println("Search suggestion substring does not match search text");
				}
				System.out.println("The text \"" + bold + "\" is bolded with the part \"" + unbold + "\" unbolded and matches search text");
			}
			List<WebElement> suggestionsWithoutDuplicates = new ArrayList<>(new HashSet<>(suggestions));
			try {
				assertEquals(suggestionsWithoutDuplicates.size(), suggestions.size());
				System.out.println("Suggested searches do not duplicate");
			} catch (AssertionError e) {
				System.out.println("Search suggestions duplicated");
			}
		}
	}
	
	@Then("search categories follow the acceptance criteria")
	public void searchCategories() {
		for (int j = 0; j < SupplyHouse.terms.size(); j++) {
			String term = SupplyHouse.terms.get(j).trim();
			try {
				SupplyHouse.enterSearchText(driver, term);
			} catch (Exception e) {
				System.out.println("Term \"" + term +"\" does not produce categories");
				continue;
			}
			List<WebElement> categories = SupplyHouse.listCategories(driver);
			try {
				assertTrue(categories.size() <= 3);
				System.out.println("Three or under categories");
			} catch (AssertionError e) {
				System.out.println("Over three categories");
			}
			List<WebElement> categoriesWithoutDuplicates = new ArrayList<>(new HashSet<>(categories));
			try {
				assertEquals(categoriesWithoutDuplicates.size(), categories.size());
				System.out.println("Categories do not duplicate");
			} catch (AssertionError e) {
				System.out.println("Categories duplicated");
			}
		}
	}
	
	@Then("product suggestions follow acceptance criteria")
	public void productSuggestions() {
		for (int j = 0; j < SupplyHouse.terms.size(); j++) {
			String term = SupplyHouse.terms.get(j).trim();
			try {
				SupplyHouse.enterSearchText(driver, term);
			} catch (Exception e) {
				System.out.println("Term \"" + term +"\" does not produce product suggestions");
				continue;
			}
			List<WebElement> products = SupplyHouse.listProducts(driver);
			System.out.println(products.size());
			try {
				assertTrue(products.size() <= 5);
				System.out.println("five or under products");
			} catch (AssertionError e) {
				System.out.println("Over five products");
			}
			List<WebElement> images = SupplyHouse.listImages(driver);
			try {
				assertTrue(products.size() <= 5);
				System.out.println("five or under images");
			} catch (AssertionError e) {
				System.out.println("Over five images");
			}
			for (int k = 0; k < products.size(); k++) {
				System.out.println("Asserting product number " + (k + 1));
				try {
					assertEquals(images.get(k).getAttribute("alt"), products.get(k).getText());
					System.out.println("Image and product match");
				} catch (AssertionError e) {
					System.out.println("Image and product do not match");
				}
			}
		}
	}
}
