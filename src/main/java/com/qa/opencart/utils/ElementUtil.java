package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil JsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;	
		act = new Actions(this.driver);
		JsUtil = new JavaScriptUtil(this.driver);
	}
	
	public  WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			JsUtil.flash(ele);
		}
		return ele;
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public  void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public  void doClick(By locator) {
		getElement(locator).click();
	}
	
	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public void dolinkclick(By locator,String value) {
		List<WebElement> linkList = getElements(locator);
		System.out.println("total links : "+linkList.size());
        for(WebElement e : linkList) {
		String text = e.getText();
        System.out.println(text);
		if(text.equals(value)) {
			e.click();
			break;
		}
	}
		
	}
	
//*****************************************************************	Drop Down Utils*************************************************************************
	
	
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public List<String> getDropDownOptionList(By locator) {
		Select select = new Select(getElement(locator));
		List<String> optionTextList = new ArrayList<String>();
		List<WebElement> optionsList = select.getOptions();
		//System.out.println(optionsList.size());
		for(WebElement e : optionsList) {
			optionTextList.add(e.getText());
		}
		return optionTextList;
	}
	
	public void doSelecyByTextOption(By locator, String text) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for(WebElement e : optionsList ) {
			if(e.getText().equals(text)) {
			e.click();
			break;
		}
		
	}
	

}
/**
 * this method is used to select the value from drop down without Select class
 * @param locator
 * @param value
 */
	
	public  void doSelectDropDownHandleWithoutSelectClass(By locator,String value) {
		List<WebElement> optionsList = getElements(locator);
	       for(WebElement e : optionsList) {
	    	   if(e.getText().equals(value)) {
	    		   e.click();
	    		   break;
	    	   }
	       }
	}
	
//********************************************************************Action class Utils**************************************************************************
	
//	2 level menu & sub menu
	public void doMoveToElement(By locator) {
		act.moveToElement(getElement(locator)).perform();
	}

//	3 level
	public void doMoveToElement(By locator1, By locator2) {
		act.moveToElement(getElement(locator1)).perform();
		act.moveToElement(getElement(locator2)).perform();
	}
	
//	3 level with click
	public void doMoveToElement(By locator1, By locator2, By locator3) {
		act.moveToElement(getElement(locator1)).perform();
		act.moveToElement(getElement(locator2)).perform();
		doClick(locator3);
	}
	
	public void doActionsClick(By locator) {
		act.click(getElement(locator)).perform();
		
	}
	
	public void doActionsSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}
	
//	************************************************************Calendar Util***************************************************************************
	
	public  void selectDate(String day, String tagName) {
		String xpath = "//" + tagName + "[text()='" + day + "']";
		doClick(By.xpath(xpath));
	}
	
	public void selectDate(By locator, String day) {
		boolean flag = false;
		 List<WebElement> dayList = getElements(locator); 
		 for(WebElement e : dayList) {
			 if(e.getText().equals(day)) {
				 System.out.println("right date..."+day);
				 e.click();
				 flag = true;
				 break;
			 }
		 }
		 if(!flag) {
			 System.out.println("wrong date   : "+day);
		 }
		
	}
	
//	***********************************************************Wait Utils***************************************************************
	
	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	public String getAlertText(int timeOut) {
		String text = waitForAlertPresent(timeOut).getText();
		acceptAlert(timeOut);
		return text;
	}
	public void acceptAlert(int timeOut) {
		waitForAlertPresent(timeOut).accept();
	}
	public void dismissAlert(int timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}
	public String waitForTitleContains(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();	
	}
	
	public String waitForTitleIs(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
		
	}
	public Boolean waitForTitle(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleIs(title));
	}
	
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
	    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, is visible. 
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 */
		public WebElement waitVisibleOfElement(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
			
		}
		
		public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			
		}
		
		public void printElementsTexts(By locator, int timeOut) {
			waitForVisibilityOfElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
		}
		
		/**
		 * An expectation for checking an element is visible and enabled such that you can click it.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementToBeClickable(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		    return wait.until(ExpectedConditions.elementToBeClickable(locator));
			
		}
		
		public void clickWhenReady(By locator, int timeOut) {
			waitForElementToBeClickable(locator, timeOut).click();
		}
		
		public String getElementAttribute(By locator, int timeOut, String name) {
			return waitForElementToBeClickable(locator, timeOut).getAttribute(name);
		}
		
		public boolean waitForElementTextToBe(By locator, int timeOut, String value) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		    return wait.until(ExpectedConditions.textToBe(locator, value));	
		}
		
		public boolean waitForUrlToBe(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		    return wait.until(ExpectedConditions.urlContains(urlValue));	
		}
		
		public boolean waitForExactUrlToBe(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		    return wait.until(ExpectedConditions.urlToBe(urlValue));	
		}
		
		/**
		 * An expectation for checking if the given element is selected
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public boolean waitForElementToBeSelected(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		    return wait.until(ExpectedConditions.elementToBeSelected(locator));	
		}
		
		public void waitForFrameToBeAvailable(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));	
		}
		
		public void waitForFrameToBeAvailable(String nameOrId, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));	
		}
		
		public void waitForFrameToBeAvailable(int frameIndex, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
		     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));	
		}
		
		public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
			Wait<WebDriver> wait= new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
					
					return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}

	
	

}










