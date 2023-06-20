package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.riddhiHousingSociety.testCases.BaseClass;

public class AddUserPage extends BaseClass {

	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Users']/parent::a")
	@CacheLookup
	public WebElement usersNavigationTab;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Add User')]")
	@CacheLookup
	WebElement addUserLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'List Residents')]")
	@CacheLookup
	WebElement viewListOfResidentsLink;
	
	@FindBy(how = How.ID, using = "role")
	@CacheLookup
	WebElement role;

	@FindBy(how = How.NAME, using = "wing")
	@CacheLookup
	WebElement wingNo;

	@FindBy(name = "house_no")
	@CacheLookup
	WebElement houseNo;

	@FindBy(name = "fullname")
	@CacheLookup
	WebElement fullName;

	@FindBy(how = How.XPATH, using = "//input[@name='mobile']")
	@CacheLookup
	WebElement mobNo;

	@FindBy(how = How.NAME, using = "password")
	@CacheLookup
	WebElement password;

	@FindBy(how = How.NAME, using = "landline")
	@CacheLookup
	WebElement altNo;

	@FindBy(how = How.CSS, using = "input[type=\"email\"]")
	@CacheLookup
	WebElement emailId;

	@FindBy(name = "address")
	@CacheLookup
	WebElement address;

	@FindBy(name = "gender")
	@CacheLookup
	WebElement gender;

	@FindBy(name = "adhar_no")
	@CacheLookup
	WebElement aadharData;

	@FindBy(how = How.CSS, using = "input[placeholder='PAN Card Number']")
	@CacheLookup
	WebElement panData;

	@FindBy(name = "submit")
	@CacheLookup
	WebElement submitBtn;

	@FindBy(how = How.CSS, using = "button[type=\"reset\"]")
	@CacheLookup
	WebElement clearBtn;

	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement addUserSuccessMessage;	
	
	@FindBy(xpath="//*[text()='List of Residents']/ancestor::div[@class=\"tile\"]//input[@type=\"search\"]")
	WebElement searchUserInputTextlocator;	
	
	public AddUserPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public void navigateToUsersNavigationTab() {
		usersNavigationTab.click();
	}

	public void clickAddUsersLink() {
		addUserLink.click();
	}

	public void selectRole(String roleString) {
		Select selectRole = new Select(role);
		selectRole.selectByVisibleText(roleString);
	}

	public void selectWingNumber(String wingNum) {
		Select selectWingNo = new Select(wingNo);
		selectWingNo.selectByValue(wingNum);
	}

	public void setHouseNumber(String string) {
		houseNo.sendKeys(string);
	}

	public void setfullName(String fname) {
		fullName.sendKeys(fname);
	}

	public void setMobileNumber(String mobileNum) {
		mobNo.sendKeys(mobileNum);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void setAlternateNumber(String altNum) {
		altNo.sendKeys(altNum);
	}

	public void setEmailID(String emailID) {
		emailId.sendKeys(emailID);
	}

	public void setAddress(String addr) {
		address.sendKeys(addr);
	}

	public void selectGender(String gender) {
		this.gender.sendKeys(gender);
	}

	public void setAadharData(String aadharInfoData) {
		aadharData.sendKeys(aadharInfoData);
	}

	public void setPANData(String panNum) {
		panData.sendKeys(panNum);
	}

	public void clickSubmitButton() {
		submitBtn.click();
	}

	public void clickClearButton() {
		clearBtn.click();
	}

	public String getSuccessMessage() {
		String addUserSuccessMsg = addUserSuccessMessage.getText();
		return addUserSuccessMsg;
	}
	
	
	

}
