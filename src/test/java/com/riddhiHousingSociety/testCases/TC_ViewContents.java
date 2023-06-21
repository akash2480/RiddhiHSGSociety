package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddContentPage;
import com.riddhiHousingSociety.pageObjects.CommonPageObjects;
import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.pageObjects.ViewContentsPage;

public class TC_ViewContents extends BaseClass {

	static String contentId;
	static String contentTitle;
	
	
	@DataProvider(name="AddContentDataFromGetData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddContent.xlsx";
		Object [][] addContentData =  getExcelMapData(filePath);
		
		return addContentData;
	
	}
	
	@DataProvider(name="EditContentDataFromGetData")
	public Object[][] getUpdatedData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestEditContent.xlsx";
		Object [][] addContentData =  getExcelMapData(filePath);
		
		return addContentData;
	
	}
	
	@Test(priority=1, enabled=true, dataProvider = "AddContentDataFromGetData")
	public void validateViewContentInTableView(Map<String,String> mapData)
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToContentsNavigationTab();
		commonPageObjects.clickViewContentsLink();
				
		ViewContentsPage viewContentsPage = new ViewContentsPage(driver);
		assertEquals(driver.getTitle(), "Content - Riddhi Co-op Housing Society");
		
		contentTitle = mapData.get("Title");
		viewContentsPage.searchContentTitle(contentTitle);
				
		WebElement contentRowData =  viewContentsPage.getContentTableRowDataElement(contentTitle, mapData.get("Section_Name"));
		assertEquals(true, contentRowData.isDisplayed());
				
		contentId = viewContentsPage.getContentId(contentTitle, mapData.get("Section_Name"));	
				
	}
	
	
	@Test(priority=2,enabled=true, dependsOnMethods="validateViewContentInTableView", dataProvider="AddContentDataFromGetData")
	public void viewContentFromActionsViewLink(Map<String,String> mapData) throws ParseException
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToContentsNavigationTab();
		commonPageObjects.clickViewContentsLink();
		
		ViewContentsPage viewContentsPage = new ViewContentsPage(driver);
		viewContentsPage.searchContentTitle(contentTitle);
		
		viewContentsPage.clickActionsBtnViewContent(contentId);
		viewContentsPage.clickActionViewBtnViewContent(contentId);
		
		assertEquals(driver.getTitle(), "View Content - Riddhi Co-op Housing Society Admin");
		
		assertEquals(viewContentsPage.getContentTitleName(), contentTitle);
		assertEquals(viewContentsPage.getContentPrice(), mapData.get("Price"));
		assertEquals(viewContentsPage.getContentDescription(), mapData.get("Description"));
		assertEquals(viewContentsPage.getContentSectionName(), mapData.get("Section_Name"));
		assertEquals(viewContentsPage.getContentSequence(), mapData.get("Sequence"));
		assertEquals(viewContentsPage.getContentAltTag(), mapData.get("Alt_Tag"));
			
		
		String expectedDate = getDateinFormat(mapData.get("Date"),"MM/dd/yyyy","yyyy-MM-dd");
		assertEquals(viewContentsPage.getContentDate(), expectedDate);
		
		//assertEquals(viewContentsPage.getContentTitleName(), mapData.get("Location"));
		assertEquals(viewContentsPage.getcontentLink(), mapData.get("Link"));
					
	}
		
	@Test(priority=3, enabled=true, dependsOnMethods="validateViewContentInTableView")
	public void addPhotosContentFromActionsEditLink() 
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
						
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToContentsNavigationTab();
		commonPageObjects.clickViewContentsLink();
		
		ViewContentsPage viewContentsPage = new ViewContentsPage(driver);
			
		viewContentsPage.searchContentTitle(contentTitle);
		
		viewContentsPage.clickActionsBtnViewContent(contentId);
		viewContentsPage.clickActionAddPhotosBtnViewContent(contentId);
				
		assertEquals(driver.getTitle(),"Add Photo - "+contentTitle);
		
		viewContentsPage.setAddContentImage("C:\\eclipse-workspace\\riddhiHousingSociety\\Google.png");
		viewContentsPage.clickaddContentImageUploadBtn();
		
		assertEquals(viewContentsPage.getAddPhotoSuccessMsg(), "Success!Photos are uploaded Successfully");
		assertEquals(viewContentsPage.getAddPhotoSrc().contains("Google.png"),true);
		
		
		viewContentsPage.deleteAddedPhoto("Google.png");
		
		// Alert is not caught by automation
		
		/*
		 * Thread.sleep(5000);
		 * 
		 * Alert alert = driver.switchTo().alert(); String alertMessage =
		 * alert.getText(); assertEquals(alertMessage,
		 * "Are you sure you want to delete?"); alert.dismiss();
		 * 
		 * assertEquals(viewContentsPage.getaddPhotoWebElement("Google.png").isDisplayed
		 * (), true);
		 * 
		 * viewContentsPage.deleteAddedPhoto("Google.png"); alert.accept();
		 */
				
		assertEquals(viewContentsPage.getDeletePhotoSuccessMsg(), "Success!Photo Deleted Successfully");
		assertEquals(driver.getPageSource().contains("Google.png"),false);
				
	}
	
	@Test(priority=4, enabled=true, dependsOnMethods="validateViewContentInTableView")
	public void addVideosContentFromActionsEditLink() throws IOException
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
						
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToContentsNavigationTab();
		commonPageObjects.clickViewContentsLink();
		
		ViewContentsPage viewContentsPage = new ViewContentsPage(driver);
			
		viewContentsPage.searchContentTitle(contentTitle);
		
		viewContentsPage.clickActionsBtnViewContent(contentId);
		viewContentsPage.clickActionsAddVideosBtnViewContent(contentId);
				
		assertEquals(driver.getTitle(),"Add Video - "+contentTitle);
		
		FileReader reader = new FileReader("./src/test/java/com/riddhiHousingSociety/testData/SingleUsageData.properties");
		Properties properties = new Properties();
		properties.load(reader);
		String youtubeVideoLink = properties.getProperty("youtubelink");
		String[] splitSubstring = youtubeVideoLink.split("=");
		String expectedYTLinkSubstring = splitSubstring[1];
		
		viewContentsPage.setAddVideoLinkToContent(properties.getProperty("youtubelink"));
		viewContentsPage.clickAddVideoLinkSubmitBtn();
		
		assertEquals(viewContentsPage.getAddVideoLinkSuccessMsg(), "Success!Video added Successfully");
		assertEquals(viewContentsPage.getAddVideoLinkIFrame().isEnabled(), true);
		
		//video is in iframe
		assertEquals(driver.getPageSource().contains("iframe"),true );
		
		//assertEquals(viewContentsPage.getAddVideoLinkSubString().contains(expectedYTLinkSubstring),true);		
		
		viewContentsPage.deleteAddedVideoLink(expectedYTLinkSubstring);
		
		// Alert is not caught by automation
				  
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		assertEquals(alertMessage, "Are you sure you want to delete?");
		alert.dismiss();
		
		assertEquals(driver.getPageSource().contains("iframe"),true );
		assertEquals(viewContentsPage.getAddVideoLinkIFrame().isEnabled(), true);

		viewContentsPage.deleteAddedVideoLink(expectedYTLinkSubstring);
		alert.accept();
				
		assertEquals(viewContentsPage.getDeleteVideoSuccessMsg(), "Success!Video Deleted Successfully");
	
		assertEquals(driver.getPageSource().contains("iframe"),false );
				
	}
	
	@Test(priority=5,enabled=true, dependsOnMethods="validateViewContentInTableView", dataProvider="EditContentDataFromGetData")
	public void editContentFromActionsEditLink(Map<String,String> mapUpdatedData)
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToContentsNavigationTab();
		commonPageObjects.clickViewContentsLink();		
		
		ViewContentsPage viewContentsPage = new ViewContentsPage(driver);
		viewContentsPage.searchContentTitle(contentTitle);
		
		viewContentsPage.clickActionsBtnViewContent(contentId);
		viewContentsPage.clickActionEditBtnViewContent(contentId);
				
		assertEquals(driver.getTitle(),"Edit Content - Riddhi Co-op Housing Society");
		
		//Edit content field locators are same as Add content
		AddContentPage addContentPage = new AddContentPage(driver);
		addContentPage.selectSectionName(mapUpdatedData.get("Section_Name"));
		addContentPage.setContentTitle(mapUpdatedData.get("Title"));
		addContentPage.setContentPrice(mapUpdatedData.get("Price"));
		addContentPage.setContentSequence(mapUpdatedData.get("Sequence"));
		addContentPage.setContentImageAltTag(mapUpdatedData.get("Alt_Tag"));
		addContentPage.setContentDescription(mapUpdatedData.get("Description"));
		addContentPage.setContentDate(mapUpdatedData.get("Date"));
		addContentPage.setContentLocation(mapUpdatedData.get("Location"));
		addContentPage.setContentLink(mapUpdatedData.get("Link"));
		addContentPage.setContentImage("C:\\eclipse-workspace\\riddhiHousingSociety\\Screenshot_8.jpg");	
		addContentPage.clickOnSubmitButton();
		
		//no success message. Handle redirects to view Content Table page
		//assertEquals(addContentPage.getAddContentSuccessMessage(), "Success!content added Successfully...");
					
	}
	
	@Test(priority=6,enabled=true, dependsOnMethods="editContentFromActionsEditLink", dataProvider="EditContentDataFromGetData")
	public void validateContentFromActionsViewLink(Map<String,String> mapUpdatedData) throws ParseException
	{
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToContentsNavigationTab();
		commonPageObjects.clickViewContentsLink();	
		
		ViewContentsPage viewContentsPage = new ViewContentsPage(driver);
			
		viewContentsPage.searchContentTitle(contentId);
		viewContentsPage.clickActionsBtnViewContent(contentId);
		viewContentsPage.clickActionViewBtnViewContent(contentId);
		
		assertEquals(driver.getTitle(), "View Content - Riddhi Co-op Housing Society Admin");
		
		assertEquals(viewContentsPage.getContentTitleName(), mapUpdatedData.get("Title"));
		assertEquals(viewContentsPage.getContentPrice(), mapUpdatedData.get("Price"));
		assertEquals(viewContentsPage.getContentDescription(), mapUpdatedData.get("Description"));
		assertEquals(viewContentsPage.getContentSectionName(), mapUpdatedData.get("Section_Name"));
		assertEquals(viewContentsPage.getContentSequence(), mapUpdatedData.get("Sequence"));
		assertEquals(viewContentsPage.getContentAltTag(), mapUpdatedData.get("Alt_Tag"));
			
		
		String expectedDate = getDateinFormat(mapUpdatedData.get("Date"),"MM/dd/yyyy","yyyy-MM-dd");
		assertEquals(viewContentsPage.getContentDate(), expectedDate);
		
		//assertEquals(viewContentsPage.getContentTitleName(), mapData.get("Location"));
		assertEquals(viewContentsPage.getcontentLink(), mapUpdatedData.get("Link"));
					
	}
	
	


}
