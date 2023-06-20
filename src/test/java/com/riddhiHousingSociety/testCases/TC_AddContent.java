package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddContentPage;
import com.riddhiHousingSociety.pageObjects.LoginPage;

public class TC_AddContent extends BaseClass{
			
	@Test(dataProvider = "AddContentDataFromGetData")
	public void addContent(Map<String, String> mapData) throws InterruptedException
	{		
			LoginPage lpg = new LoginPage(driver);
			lpg.setUserName(userName);
			log.info("Entering Username");
			lpg.setPassword(password);
			log.info("Entering Password");
			lpg.clickSubitButton();
			log.info("Clicking Submit Button");
			
			AddContentPage addContentPage = new AddContentPage(driver);
			addContentPage.navigateToContentsNavigationTab();
			addContentPage.clickOnAddNewContentLink();
			assertEquals(driver.getTitle(),"Add New Content - Riddhi Co-op Housing Society");
			
			addContentPage.selectSectionName(mapData.get("Section_Name"));
			addContentPage.setContentTitle(mapData.get("Title"));
			addContentPage.setContentPrice(mapData.get("Price"));
			addContentPage.setContentSequence(mapData.get("Sequence"));
			addContentPage.setContentImageAltTag(mapData.get("Alt_Tag"));
			addContentPage.setContentDescription(mapData.get("Description"));
			addContentPage.setContentDate(mapData.get("Date"));
			addContentPage.setContentLocation(mapData.get("Location"));
			addContentPage.setContentLink(mapData.get("Link"));
			addContentPage.setContentImage("C:\\eclipse-workspace\\riddhiHousingSociety\\Screenshot_8.jpg");	
			addContentPage.clickOnSubmitButton();
			assertEquals(addContentPage.getAddContentSuccessMessage(), "Success!content added Successfully...");
			
	}
	
	@DataProvider(name="AddContentDataFromGetData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddContent.xlsx";
		Object [][] addContentData =  getExcelMapData(filePath);
		
		return addContentData;
	
	}
		
}
