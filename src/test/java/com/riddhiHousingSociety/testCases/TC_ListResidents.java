package com.riddhiHousingSociety.testCases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddUserPage;
import com.riddhiHousingSociety.pageObjects.ListOfResidentsPage;
import com.riddhiHousingSociety.pageObjects.LoginPage;

public class TC_ListResidents extends BaseClass{
	
	@DataProvider(name = "AddedUserData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddSingleUserData.xlsx";
		Object[][] getUserData = getExcelMapData(filePath);
		return getUserData;
	}
	
	
	@Test(priority=1,dataProvider = "AddedUserData")
	public void validateUserOnListOfResidentsPageTable(Map<String,String> userMapData)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubitButton();
		
		AddUserPage addUser = new AddUserPage(driver);
		addUser.navigateToUsersNavigationTab();
		
		ListOfResidentsPage listOfResidents = new ListOfResidentsPage(driver);
		listOfResidents.clickListResidentsLink();
		
	}
	
	
}
