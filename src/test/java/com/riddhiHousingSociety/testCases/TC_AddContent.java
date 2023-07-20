package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddContentPage;
import com.riddhiHousingSociety.pageObjects.CommonPageObjects;
import com.riddhiHousingSociety.pageObjects.LoginPage;

public class TC_AddContent extends BaseClass{
			
	@Test(dataProvider = "AddContentDataFromGetData", invocationCount = 1)
	public void addContent(Map<String, String> mapData) throws InterruptedException
	{		
			LoginPage lpg = new LoginPage(getDriver());
			lpg.setUserName(userName);
			log.info("Entering Username");
			lpg.setPassword(password);
			log.info("Entering Password");
			lpg.clickSubitButton();
			log.info("Clicking Submit Button");
			
			CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
			commonPageObjects.navigateToContentsNavigationTab();
			commonPageObjects.clickAddNewContentLink();
			
			AddContentPage addContentPage = new AddContentPage(getDriver());
			assertEquals(getDriver().getTitle(),"Add New Content - Riddhi Co-op Housing Society");
			
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
