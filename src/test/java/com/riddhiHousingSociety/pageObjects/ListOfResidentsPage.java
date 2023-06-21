package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ListOfResidentsPage {
	
	WebDriver driver;
		
	@FindBy(how=How.XPATH, using="//input[@type='search']")
	WebElement searchUserInputField;
	
	@FindBy(how=How.XPATH, using="//a[text()='Update Profile']")
	WebElement updateProfileTab;
	
	@FindBy(how=How.XPATH, using="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(how = How.NAME, using = "wing")
	@CacheLookup
	WebElement wingNo;
	
	public ListOfResidentsPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public void searchUserInputField(String fullName)
	{
		searchUserInputField.sendKeys(fullName);
	}
	
	public WebElement getUserDetailsInTableView(String FullName, String MobNo, String email, String wingNo, String houseNo, String type )
	{
		String userDetailTableViewXpath = "//a[text()='"+FullName+"']/parent::td/following-sibling::td[text()='"+MobNo+"']/following-sibling::td[text()='"+email+"']/following-sibling::td[text()='"+wingNo+"']/following-sibling::td[text()='"+houseNo+"']/following-sibling::td[text()='"+type+"']";
		WebElement userDetailTableView = driver.findElement(By.xpath(userDetailTableViewXpath));
		return userDetailTableView;
	}
	
	public void goToUserDetailFromUserLink(String FullName)
	{
		WebElement userDetailFromUserLinkXpath = driver.findElement(By.xpath("//a[text()='"+FullName+"']"))	;
		userDetailFromUserLinkXpath.click();
	}
	
	public String getUserDetailsFromUserProfilePage(String userDetailKey)
	{
		WebElement userDetailFromUserLinkXpath = driver.findElement(By.xpath("//th[text()='"+userDetailKey+"']/following-sibling::td/span"))	;
		return userDetailFromUserLinkXpath.getText();
	}
	
	public WebElement getUserFullNameHeaderOnUserProfilePage(String fullName, String Role)
	{
		WebElement userFullNameHeaderOnProfilePage = driver.findElement(By.xpath("//h3[text()='"+fullName+" ("+Role+")']"));
		return userFullNameHeaderOnProfilePage;
		
	}
	
	public void goToUserDetailPageFromViewBtn(String fullName)
	{
		WebElement viewBtnFromUserLink = driver.findElement(By.xpath("//a[text()='"+fullName+"']//ancestor::tr//a[text()='View']"));
		viewBtnFromUserLink.click();
	}
	
	public void clickUpdateProfileLink()
	{
		updateProfileTab.click();
	}
	
	public void clickSaveBtn() 
	{
		saveBtn.click();	
	}
	
	public WebElement selectWingNumberWebElement()
	{
		return wingNo;
	}
}
