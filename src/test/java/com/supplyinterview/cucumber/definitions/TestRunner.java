package com.supplyinterview.cucumber.definitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


//com.fdmgroup.cucumber.definitions

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features",
		glue= {"com.supplyinterview.cucumber.definitions"},
//		tags = "@SearchSuggestions",
		stepNotifications = true, //?
		plugin = {"summary"}
)
public class TestRunner {
	
}
