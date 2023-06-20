package com.riddhiHousingSociety.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.riddhiHousingSociety.pageObjects.LoginPage;

import com.riddhiHousingSociety.pageObjects.AddUserPage;

public class TC_AddUser_001 extends BaseClass{
		
	
	@Test(dataProvider = "AddUsersDataFromGetData")
	public void addNewUser(Map<String, String> mapData) throws InterruptedException, IOException
	{	
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		
		AddUserPage addUser = new AddUserPage(driver);
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(addUser.usersNavigationTab));
				
		addUser.navigateToUsersNavigationTab();
		addUser.clickAddUsersLink();		
		softAssert.assertEquals(driver.getTitle(),"Add User - Riddhi Co-op Housing Society");
						
		addUser.selectRole(mapData.get("Role"));
		addUser.selectWingNumber(mapData.get("Wing House"));
		addUser.setHouseNumber(mapData.get("House no"));
		addUser.setfullName(mapData.get("Full Name"));
		addUser.setMobileNumber(mapData.get("Mobile Number"));
		addUser.setPassword(mapData.get("Password"));
		addUser.setAlternateNumber(mapData.get("Alt Number"));
		//String emailID = randomString(8)+"@gmail.com";
		addUser.setEmailID(mapData.get("Email"));
		addUser.setAddress(mapData.get("Address"));
		addUser.selectGender(mapData.get("Gender"));
		addUser.setAadharData(mapData.get("Aadhar_No"));
		addUser.setPANData(mapData.get("Pan_No"));
		addUser.clickSubmitButton();
		softAssert.assertEquals(addUser.getSuccessMessage(), "Success! User added successfully!");		
		softAssert.assertAll();
		
	}	
		
	@DataProvider(name="AddUsersDataFromGetData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddUserData.xlsx";
		Object [][] addUserData =  getExcelMapData(filePath);
		
		return addUserData;
	
	}
}
