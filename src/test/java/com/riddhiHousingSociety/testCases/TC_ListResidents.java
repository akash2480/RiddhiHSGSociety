package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddUserPage;
import com.riddhiHousingSociety.pageObjects.CommonPageObjects;
import com.riddhiHousingSociety.pageObjects.ListOfResidentsPage;
import com.riddhiHousingSociety.pageObjects.LoginPage;

public class TC_ListResidents extends BaseClass{
	
	static String userOriginalFullName ;
	
	@DataProvider(name = "AddedUserData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/singleUserTestData.xlsx";
		Object[][] getUserData = getExcelMapData(filePath);
		return getUserData;
	}
	
	@DataProvider(name = "UpdateUserProfileTestData")
	public Object[][] getUpdatedData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/SingleUserUpdatedTestData.xlsx";
		Object[][] getUserData = getExcelMapData(filePath);
		return getUserData;
	}
	
	
	@Test(priority=1,dataProvider = "AddedUserData", enabled=true)
	public void validateUserOnListOfResidentsPageTable(Map<String,String> userMapData)
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubitButton();
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToUsersNavigationTab();
		commonPageObjects.clickListResidentsLink();
		
		assertEquals(getDriver().getTitle(),"List of Residents", "Page Title did not match");
		
		ListOfResidentsPage listOfResidents = new ListOfResidentsPage(getDriver());
		userOriginalFullName = userMapData.get("Full_Name");
		listOfResidents.searchUserInputField(userMapData.get("Full_Name"));
		
		WebElement userDetailInTableView = listOfResidents.getUserDetailsInTableView(userMapData.get("Full_Name"), userMapData.get("Mobile_No"), userMapData.get("Email"), userMapData.get("Wing_House"), userMapData.get("House_No"), userMapData.get("Role"));
		assertEquals(userDetailInTableView.isDisplayed(), true, "User is not available");
	}
	
	@Test(priority=2, dependsOnMethods="validateUserOnListOfResidentsPageTable", dataProvider="AddedUserData",enabled=true)
	public void validateUserDetailsFromUserLink(Map<String,String> userMapData)
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubitButton();
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToUsersNavigationTab();
		commonPageObjects.clickListResidentsLink();
		
		ListOfResidentsPage listOfResidents = new ListOfResidentsPage(getDriver());
						
		listOfResidents.searchUserInputField(userMapData.get("Full_Name"));
		listOfResidents.goToUserDetailFromUserLink(userMapData.get("Full_Name"));
		assertEquals(getDriver().getTitle(),"User Profile - Riddhi Co-op Housing Society Admin");
		
		assertEquals(listOfResidents.getUserFullNameHeaderOnUserProfilePage(userMapData.get("Full_Name"), userMapData.get("Role")).isDisplayed(), true, "UserProfile Header did not match");
		
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Role"), userMapData.get("Role"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Wing / Row House"), userMapData.get("Wing_House"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("House / Flat Number"), userMapData.get("House_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Name"), userMapData.get("Full_Name"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Mobile Number"), userMapData.get("Mobile_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Alternate / Landline Number"), userMapData.get("Alt_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Email"), userMapData.get("Email"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Password"), userMapData.get("Password"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Gender"), userMapData.get("Gender"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Aadhar No"), userMapData.get("Aadhar_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("PAN No"), userMapData.get("PAN_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Address"), userMapData.get("Address"));
	}
	
	@Test(priority=3, dependsOnMethods="validateUserOnListOfResidentsPageTable", dataProvider="AddedUserData",enabled=true)
	public void validateUserDetailsFromViewBtn(Map<String,String> userMapData)
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubitButton();
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToUsersNavigationTab();
		commonPageObjects.clickListResidentsLink();
		
		ListOfResidentsPage listOfResidents = new ListOfResidentsPage(getDriver());
					
		listOfResidents.searchUserInputField(userMapData.get("Full_Name"));
		listOfResidents.goToUserDetailPageFromViewBtn(userMapData.get("Full_Name"));
		
		assertEquals(getDriver().getTitle(),"User Profile - Riddhi Co-op Housing Society Admin");
		
		assertEquals(listOfResidents.getUserFullNameHeaderOnUserProfilePage(userMapData.get("Full_Name"), userMapData.get("Role")).isDisplayed(), true, "UserProfile Header did not match");
		
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Role"), userMapData.get("Role"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Wing / Row House"), userMapData.get("Wing_House"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("House / Flat Number"), userMapData.get("House_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Name"), userMapData.get("Full_Name"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Mobile Number"), userMapData.get("Mobile_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Alternate / Landline Number"), userMapData.get("Alt_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Email"), userMapData.get("Email"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Password"), userMapData.get("Password"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Gender"), userMapData.get("Gender"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Aadhar No"), userMapData.get("Aadhar_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("PAN No"), userMapData.get("PAN_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Address"), userMapData.get("Address"));
	}
	
	
	@Test(priority=4, dataProvider="UpdateUserProfileTestData", dependsOnMethods="validateUserOnListOfResidentsPageTable", enabled = true)
	public void updateUserProfile(Map<String,String> updatedUserMapData)
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubitButton();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
				
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToUsersNavigationTab();
		commonPageObjects.clickListResidentsLink();
		
		ListOfResidentsPage listOfResidents = new ListOfResidentsPage(getDriver());
				
		listOfResidents.searchUserInputField(userOriginalFullName);
		listOfResidents.goToUserDetailPageFromViewBtn(userOriginalFullName);
		
		listOfResidents.clickUpdateProfileLink();
		
		AddUserPage addUser = new AddUserPage(getDriver());
		wait.until(ExpectedConditions.elementToBeClickable(listOfResidents.selectWingNumberWebElement()));
		addUser.selectWingNumber(updatedUserMapData.get("Wing_House"));
		addUser.setHouseNumber(updatedUserMapData.get("House_No"));
		addUser.setfullName(updatedUserMapData.get("Full_Name"));
		addUser.setMobileNumber(updatedUserMapData.get("Mobile_No"));
		addUser.setPassword(updatedUserMapData.get("Password"));
		addUser.setAlternateNumber(updatedUserMapData.get("Alt_No"));
		addUser.setEmailID(updatedUserMapData.get("Email"));
		addUser.setAddress(updatedUserMapData.get("Address"));
		addUser.selectGender(updatedUserMapData.get("Gender"));
		addUser.setAadharData(updatedUserMapData.get("Aadhar_No"));
		addUser.setPANData(updatedUserMapData.get("PAN_No"));
		listOfResidents.clickSaveBtn();
		assertEquals(addUser.getSuccessMessage(), "Success! Profile updated successfully!");	
		assertEquals(getDriver().getPageSource().contains(userOriginalFullName), false, "User Profile not updated");
			
	}
	
	@Test(priority=5, dependsOnMethods="updateUserProfile", dataProvider="UpdateUserProfileTestData", enabled=true)
	public void validateUpdatedUserOnListOfResidentsPageTable(Map<String,String> userMapData)
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubitButton();
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToUsersNavigationTab();
		commonPageObjects.clickListResidentsLink();
		
		ListOfResidentsPage listOfResidents = new ListOfResidentsPage(getDriver());
		listOfResidents.searchUserInputField(userMapData.get("Full_Name"));
		
		WebElement userDetailInTableView = listOfResidents.getUserDetailsInTableView(userMapData.get("Full_Name"), userMapData.get("Mobile_No"), userMapData.get("Email"), userMapData.get("Wing_House"), userMapData.get("House_No"), userMapData.get("Role"));
		assertEquals(userDetailInTableView.isDisplayed(), true, "User is not available");
	}
	
	@Test(priority=6, dependsOnMethods="updateUserProfile", dataProvider="UpdateUserProfileTestData", enabled=true)
	public void validateUpdatedUserDetailsFromViewBtn(Map<String,String> userMapData)
	{
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubitButton();
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToUsersNavigationTab();
		commonPageObjects.clickListResidentsLink();
		
		ListOfResidentsPage listOfResidents = new ListOfResidentsPage(getDriver());		
		listOfResidents.searchUserInputField(userMapData.get("Full_Name"));
		listOfResidents.goToUserDetailPageFromViewBtn(userMapData.get("Full_Name"));
		
		assertEquals(getDriver().getTitle(),"User Profile - Riddhi Co-op Housing Society Admin");
		
		assertEquals(listOfResidents.getUserFullNameHeaderOnUserProfilePage(userMapData.get("Full_Name"), userMapData.get("Role")).isDisplayed(), true, "UserProfile Header did not match");
		
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Role"), userMapData.get("Role"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Wing / Row House"), userMapData.get("Wing_House"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("House / Flat Number"), userMapData.get("House_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Name"), userMapData.get("Full_Name"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Mobile Number"), userMapData.get("Mobile_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Alternate / Landline Number"), userMapData.get("Alt_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Email"), userMapData.get("Email"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Password"), userMapData.get("Password"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Gender"), userMapData.get("Gender"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Aadhar No"), userMapData.get("Aadhar_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("PAN No"), userMapData.get("PAN_No"));
		assertEquals(listOfResidents.getUserDetailsFromUserProfilePage("Address"), userMapData.get("Address"));
	}
	
	public void validateShowEntriesCount()
	{	
		LoginPage lpg = new LoginPage(getDriver());
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
		commonPageObjects.navigateToSectionsNavigationTab();
		
		commonPageObjects.selectShowEntries(10);
		assertEquals(commonPageObjects.getRowCountTable()<=10, true, "Show Entries is not working as expected");
				
		commonPageObjects.selectShowEntries(25);
		assertEquals(commonPageObjects.getRowCountTable()<=25, true, "Show Entries is not working as expected");
		
		commonPageObjects.selectShowEntries(50);
		assertEquals(commonPageObjects.getRowCountTable()<=50, true, "Show Entries is not working as expected");
		
		commonPageObjects.selectShowEntries(100);
		assertEquals(commonPageObjects.getRowCountTable()<=100, true, "Show Entries is not working as expected");
		
	}
	
	
}
