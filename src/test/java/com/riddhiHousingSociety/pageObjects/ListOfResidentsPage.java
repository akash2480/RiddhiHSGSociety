package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ListOfResidentsPage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Add User')]")
	@CacheLookup
	WebElement listResidentsLink;
	
	@FindBy(how=How.XPATH, using="//input[@type='search']")
	WebElement searchUserInputField;
	
	public ListOfResidentsPage(WebDriver driver) 
	{
		this.driver = driver;
		
	}
	
	public void clickListResidentsLink()
	{
		listResidentsLink.click();
	}
	
	
}
