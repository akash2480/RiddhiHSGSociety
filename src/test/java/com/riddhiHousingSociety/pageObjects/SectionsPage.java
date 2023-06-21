package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SectionsPage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[text()='Add Section']/parent::div//input[@name=\"sectionname\"]")
	WebElement addSectionInputTextLocator;
	
	@FindBy(how=How.XPATH, using="//*[text()='Add Section']/parent::div//button[@name=\"submit\"]")
	WebElement addSectionSubmitBtnLocator;
	
	@FindBy(how=How.XPATH, using="//*[text()='Add Section']/parent::div//div[@class=\"alert alert-danger\"]")
	WebElement errorMessageOnAddSection;
	
	@FindBy(how=How.XPATH, using="//*[text()='Add Section']/parent::div//div[@class=\"alert alert-success\"]")
	WebElement successMessageOnAddSection;
		
	@FindBy(xpath = "//*[text()='List of Sections']/parent::div[@class=\"tile\"]//input[@type=\"search\"]")
	WebElement searchSectionInputTextLocator;
	
	@FindBy(xpath = "//*[text()='List of Sections']/parent::div[@class=\"tile\"]//table/tbody/tr/td[2]/h5")
	WebElement searchedSectionInTableLocator;
	
	public SectionsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public void setSectionName(String sectionName)
	{
		addSectionInputTextLocator.sendKeys(sectionName);
	}
	
	public void clickAddSectionSubmitBtn()
	{
		addSectionSubmitBtnLocator.click();
	}
	
	public String getAddSectionSuccessMessage()
	{
		String addSectionSuccessMessage = successMessageOnAddSection.getText();
		return addSectionSuccessMessage;
	}
	
	public String getAddSectionErrorMessage()
	{
		String addSectionErrorMessage = errorMessageOnAddSection.getText();
		return addSectionErrorMessage;
	}
	
	public void searchSectionName(String sectionName)
	{
		searchSectionInputTextLocator.sendKeys(sectionName);
	}
	
	public String getSectionNameFromTable()
	{
		String sectionName = searchedSectionInTableLocator.getText();
		return sectionName;
	}
	
	public String getSectionId(String sectionName)
	{
		WebElement sectionIDLoc = driver.findElement(By.xpath("//*[text()='"+sectionName+"']//ancestor::tr/td[1]"));
		String sectionIDText = sectionIDLoc.getText();
		return sectionIDText;
	}
			
}
