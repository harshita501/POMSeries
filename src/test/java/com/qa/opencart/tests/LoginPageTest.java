package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Story("US-101: design the login page for demo opencart app with login, title and forget pwd link")
@Epic("Epic-100: design login page feature")

public class LoginPageTest extends BaseTest {
	
	@Description("Login page title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is : "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("Forget pwd link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void forgetPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgetPwdLinkExist());
	}
	
	@Description("Login test with correct credential..")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(), Constants.Account_PAGE_TITLE);
	}

}
