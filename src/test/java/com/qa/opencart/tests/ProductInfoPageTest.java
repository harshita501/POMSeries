package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void AccSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void productInfoDataTest() {
		searchResultPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultPage.selectProductsFromResults("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductinformation();
		softAssert.assertTrue(actProductInfoMap.get("name").equals("MacBook Pro"));
		softAssert.assertTrue(actProductInfoMap.get("Brand").equals("Apple"));
		softAssert.assertTrue(actProductInfoMap.get("price").equals("$2,000.00"));
		softAssert.assertAll();
		
		
	}

}
