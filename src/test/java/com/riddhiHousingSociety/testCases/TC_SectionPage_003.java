package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.pageObjects.SectionsPage;

public class TC_SectionPage_003 extends BaseClass {
	
	static String sectionID;
	
	@Test	
	public void validateAddSection() throws IOException
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		
		SectionsPage sectionPage = new SectionsPage(driver);
		sectionPage.clickSectionNavigationTab();  
		log.info("Navigate To Sections Page");
		
		FileReader reader = new FileReader("./src/test/java/com/riddhiHousingSociety/testData/SingleUsageData.properties");
		Properties properties = new Properties();
		properties.load(reader);
		
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
	
	@Test(enabled = false)
	public void validateAddSectionWithDuplicateSectionName()
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		
		SectionsPage sectionPage = new SectionsPage(driver);
		sectionPage.clickSectionNavigationTab();
		
		//Input Already existing data in the application
		sectionPage.setSectionName("Role");
		sectionPage.clickAddSectionSubmitBtn();
		
		assertEquals(sectionPage.getAddSectionErrorMessage(), "Error!This sections already exists!!!");
	}
	
	
	
}
