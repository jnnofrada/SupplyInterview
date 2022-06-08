@Search
Feature: Search

	@SearchSuggestions
	Scenario: Asserting search suggestions
		Given user lands on the Supply House main page
		When user enters search terms from "<file>" file
		Then search suggestions follow the acceptance criteria
	Examples:
		|	file	|
		|	terms.csv	|
		
	@SearchCategories
	Scenario: Asserting search categories
		Given user lands on the Supply House main page
		When user enters search terms from "<file>" file
		Then search categories follow the acceptance criteria
	Examples:
		|	file	|
		|	terms.csv	|
		
	@ProductSuggestions
	Scenario: Asserting product suggestions
		Given user lands on the Supply House main page
		When user enters search terms from "<file>" file
		Then product suggestions follow acceptance criteria
	Examples:
		|	file	|
		|	terms.csv	|
		
		