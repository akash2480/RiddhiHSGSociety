package com.riddhiHousingSociety.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.riddhiHousingSociety.testCases.BaseClass;

public class LoginPage extends BaseClass{
	
	WebDriver driver;
	
	@FindBy(how=How.NAME,using="myusername")
	WebElement username;
	
	@FindBy(name="mypassword")
	WebElement password;
	
	@FindBy(xpath="//button[@type=\"submit\"]")
	WebElement signInButton;
	
	@FindBy(how=How.XPATH, using="//div[@class=\"alert alert-danger\"]")
	WebElement errorMessage;
	
	@FindBy(how=How.XPATH, using="//div[@class=\"alert alert-success\"]")
	WebElement successMessage;
	
	@FindBy(how=How.XPATH, using="//a[@aria-label=\"Open Profile Menu\"]")
	public	WebElement adminProfileLink;
	
	@FindBy(how=How.XPATH, using="//a[text()=' Logout']")
	WebElement logoutLink;
	
	public LoginPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String userName)
	{
		username.sendKeys(userName);
	}
	
	public void setPassword(String passWord)
	{
		password.sendKeys(passWord);
	}
	
	public void clickSubitButton()
	{
		signInButton.click();
	}
	
	public String getErrorTextMessage() 
	{
		String errorTextMessage = errorMessage.getText();
		return errorTextMessage;
	}
	
	public void clickAdminProfileLink()
	{
		adminProfileLink.click();
	}
		
	public void clickLogoutLink()
	{
		logoutLink.click();;
	}
	
	public String getLogoutSuccessMessage()
	{
		String successTextMessage = successMessage.getText();
		return successTextMessage ;
	}
		
}
	

