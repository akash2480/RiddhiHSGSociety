package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.pageObjects.AddUserPage;
import com.riddhiHousingSociety.pageObjects.CommonPageObjects;

public class TC_AddUser_001 extends BaseClass{
		
	@DataProvider(name="AddUsersDataFromGetData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/singleUserTestData.xlsx";
		Object [][] addUserData =  getExcelMapData(filePath);
		
		return addUserData;
	
	}
	
	@Test(dataProvider = "AddUsersDataFromGetData")
	public void addNewUser(Map<String, String> mapData) throws InterruptedException, IOException
	{	
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginpage = new LoginPage(getDriver());
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToUsersNavigationTab();
		commonPageObjects.clickAddUsersLink();
		
		AddUserPage addUser = new AddUserPage(getDriver());						
				
		assertEquals(getDriver().getTitle(),"Add User - Riddhi Co-op Housing Society");
						
		addUser.selectRole(mapData.get("Role"));
		addUser.selectWingNumber(mapData.get("Wing_House"));
		addUser.setHouseNumber(mapData.get("House_No"));
		addUser.setfullName(mapData.get("Full_Name"));
		addUser.setMobileNumber(mapData.get("Mobile_No"));
		addUser.setPassword(mapData.get("Password"));
		addUser.setAlternateNumber(mapData.get("Alt_No"));
		//String emailID = randomString(8)+"@gmail.com";
		addUser.setEmailID(mapData.get("Email"));
		addUser.setAddress(mapData.get("Address"));
		addUser.selectGender(mapData.get("Gender"));
		addUser.setAadharData(mapData.get("Aadhar_No"));
		addUser.setPANData(mapData.get("PAN_No"));
		addUser.clickSubmitButton();
		softAssert.assertEquals(addUser.getSuccessMessage(), "Success! User added successfully!");		
		softAssert.assertAll();
		
	}	
		
}
