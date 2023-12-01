package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.pageObjects.CommonPageObjects;
import com.riddhiHousingSociety.pageObjects.SectionsPage;

public class TC_SectionPage_003 extends BaseClass {
	
	static String sectionID;
	
	@Test(enabled=true)	
	public void validateAddSection() throws IOException
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
		log.info("Navigate To Sections Page");
				
		FileReader reader = new FileReader("./src/test/java/com/riddhiHousingSociety/testData/SingleUsageData.properties");
		Properties properties = new Properties();
		properties.load(reader);
		
		SectionsPage sectionPage = new SectionsPage(getDriver());
		String sectionName = properties.getProperty("sectionName")+randomAlphaNumeric(7);
		sectionPage.setSectionName(sectionName);
		log.info("Set a unique Section Name in Add New Section");
		sectionPage.clickAddSectionSubmitBtn();
		log.info("Click submit button");
		
		assertEquals(sectionPage.getAddSectionSuccessMessage(), "Success!Section added Successfully...");
		log.info("Validate Create New Section Success Message");
		
		sectionPage.searchSectionName(sectionName);
		log.info("Search for the sectionName from the search Section field");
		assertEquals(sectionPage.getSectionNameFromTable(), sectionName);	
		log.info("Validate Create New Section Success Message");
		
		sectionID = sectionPage.getSectionId(sectionName); 
				
	}
	
	@Test(enabled = true)
	public void validateAddSectionWithDuplicateSectionName()
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
		
		SectionsPage sectionPage = new SectionsPage(getDriver());
				
		//Input Already existing data in the application
		sectionPage.setSectionName("Role");
		sectionPage.clickAddSectionSubmitBtn();
		
		assertEquals(sectionPage.getAddSectionErrorMessage(), "Error!This sections already exists!!!");
	}
	
	@Test(enabled = true)
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
	
	@Test(enabled = true)
	public void paginationFunc()
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
				
		////////////////////////////
		

		
		
		////////////////////////////
				
		int noOfPages = commonPageObjects.getNumberOfPagesCount();
		
		String isPreviousBtnClassAttr = commonPageObjects.getPreviousBtnAttribute("class");
		assertEquals(isPreviousBtnClassAttr.contains("disabled"), true, "Previous button is not disabled");
				
		if (noOfPages>1)
		{	
			do
			{	
				String isNextBtnClassAttr = commonPageObjects.getNextBtnAttribute("class");
				if(!isNextBtnClassAttr.contains("disabled"))
				{
					commonPageObjects.clickNextBtnPagination();
				}
				else
				{
					break;
				}
			}
			while(true);				
		}
		else
		{
			System.out.println("No Pagination in this page");
		}
		
		
	}
	
	
}
