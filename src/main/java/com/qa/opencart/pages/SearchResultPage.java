package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
//	Page Objects - By locators - Object Repository
	By searchItemResult = By.cssSelector("div.product-layout div.product-thumb");
	By resultItems = By.cssSelector("div.product-thumb h4 a");
	
//	Constructor:
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
//	Page Actions:
	public int getProductsResultCounts() {
		return elementUtil.getElements(searchItemResult).size();
	}
	
	public ProductInfoPage selectProductsFromResults(String productName) {
		List<WebElement> resultItemsList = elementUtil.getElements(resultItems);
		System.out.println("total number of item displayed for : "+ productName + ":"+ resultItemsList.size());
		for(WebElement e : resultItemsList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
		
	}

}
