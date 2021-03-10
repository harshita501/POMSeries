package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private ElementUtil elementUtil;
	private WebDriver driver;
	
//	Page Objects - By locators - Object Repository
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By passwordconfirm = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By successMessage = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
//	Constructor:
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
		
// Page actions
		public boolean accountRegistration(String firstname, String lastname, String email, String telephone, String password, String subscribe) {
			elementUtil.doSendKeys(this.firstname, firstname);
			elementUtil.doSendKeys(this.lastname, lastname);
			elementUtil.doSendKeys(this.email, email);
			elementUtil.doSendKeys(this.telephone, telephone);
			elementUtil.doSendKeys(this.password, password);
			elementUtil.doSendKeys(this.passwordconfirm, password);
			
			if(subscribe.equals("yes")) {
				elementUtil.doClick(subscribeYes);
			}
			else {
				elementUtil.doClick(subscribeNo);
			}
			elementUtil.doClick(agreeCheckBox);
			elementUtil.doClick(continueButton);
			
			elementUtil.waitForVisibilityOfElements(this.successMessage, 5);
			
			String mesg = elementUtil.doGetElementText(this.successMessage);
			System.out.println("account creation :"+ mesg);
			if(mesg.contains(Constants.Account_Creation_Success_Message)) {
				elementUtil.doClick(logoutLink);
				elementUtil.doClick(registerLink);
				return true;
			}
			else {
				return false;
			}
		
		}
		
	}
	


