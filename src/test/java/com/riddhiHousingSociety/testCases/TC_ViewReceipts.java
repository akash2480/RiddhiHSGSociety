package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddReceiptsPage;
import com.riddhiHousingSociety.pageObjects.CommonPageObjects;
import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.pageObjects.ViewReceiptsPage;

public class TC_ViewReceipts extends BaseClass {
	
	static String receiptNum;
		
	@DataProvider(name = "AddReceiptDataFromGetData")
	public Object[][] getData() throws IOException 
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddReceipt.xlsx";
		Object[][] addReceiptData = getExcelMapData(filePath);

		return addReceiptData;
	}
	
	@DataProvider(name = "EditReceiptDataFromGetData")
	public Object[][] getUpdatedData() throws IOException 
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestEditReceipt.xlsx";
		Object[][] addReceiptData = getExcelMapData(filePath);

		return addReceiptData;
	}
			
	@Test(priority=1,dataProvider="AddReceiptDataFromGetData")
	public void validateReceiptInTableView(Map<String,String>mapData)
	{
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToReceiptsNavigationTab();
		commonPageObjects.clickViewReceiptsLink();
		
		assertEquals(driver.getTitle(),"List of Receipts");
		
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String currentDate = formatter.format(date);
						
		LocalDate beforeFiveDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeFiveDays);		
		
		ViewReceiptsPage viewReceiptsPage = new ViewReceiptsPage(driver);
		viewReceiptsPage.setReceiptFromDate(pastDate);
		viewReceiptsPage.setReceiptToDate(currentDate);
		viewReceiptsPage.clickReceiptSearchBtn();
		
		
		String[] str1 = mapData.get("Resident").split("\\(");
		String residentName = str1[0].trim();
		
		receiptNum = viewReceiptsPage.getReceiptNumber(residentName, 
				mapData.get("Payment_For"), mapData.get("Total_Amount"), mapData.get("Received_Amount"),
				mapData.get("Balance_Amount"));
				
		WebElement receiptRowData = viewReceiptsPage.getTableRowDataElement(residentName, 
				mapData.get("Payment_For"), mapData.get("Total_Amount"), mapData.get("Received_Amount"),
				mapData.get("Balance_Amount"));
		assertEquals(receiptRowData.isDisplayed(), true);
		
	}
	
	@Test(priority=2,enabled=true,dependsOnMethods = "validateReceiptInTableView" , dataProvider="AddReceiptDataFromGetData")
	public void validateReceiptInvoiceFromViewBtn(Map<String,String>mapData)
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToReceiptsNavigationTab();
		commonPageObjects.clickViewReceiptsLink();
						
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String currentDate = formatter.format(date);
						
		LocalDate beforeFiveDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeFiveDays);		
		
		ViewReceiptsPage viewReceiptsPage = new ViewReceiptsPage(driver);
		viewReceiptsPage.setReceiptFromDate(pastDate);
		viewReceiptsPage.setReceiptToDate(currentDate);
		viewReceiptsPage.clickReceiptSearchBtn();
		viewReceiptsPage.clickViewiewReceiptBtn(receiptNum);
		
		String pageSource = driver.getPageSource();
		
		String[] str1 = mapData.get("Resident").split("\\(");
		String residentName = str1[0].trim();
		
		pageSource.contains(residentName);
		pageSource.contains(mapData.get("Payment_For"));
		pageSource.contains(mapData.get("Total_Amount"));
		pageSource.contains(mapData.get("Received_Amount"));
		pageSource.contains(mapData.get("Balance_Amount"));
		pageSource.contains(mapData.get("Transaction_Details"));
	}
	
	@Test(priority=3, enabled=true, dataProvider="EditReceiptDataFromGetData", dependsOnMethods = "validateReceiptInTableView")
	public void editReceipt(Map<String,String>updatedMapData)
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToReceiptsNavigationTab();
		commonPageObjects.clickViewReceiptsLink();
			
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String currentDate = formatter.format(date);
						
		LocalDate beforeSevenDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeSevenDays);		
		
		ViewReceiptsPage viewReceiptsPage = new ViewReceiptsPage(driver);
		viewReceiptsPage.setReceiptFromDate(pastDate);
		viewReceiptsPage.setReceiptToDate(currentDate);
		viewReceiptsPage.clickReceiptSearchBtn();
		viewReceiptsPage.clickActionBtnReceiptTableRowData(receiptNum);
		viewReceiptsPage.editReceiptTableRowData(receiptNum);
		
		//Edit Receipt form is same as Add Receipt
		AddReceiptsPage addReceiptsPage = new AddReceiptsPage(driver);
		addReceiptsPage.selectReceiptResident(updatedMapData.get("Resident"));
		addReceiptsPage.setReceiptPaymentFor(updatedMapData.get("Payment_For"));
		addReceiptsPage.selectReceiptPaymentMode(updatedMapData.get("Payment_Mode"));
		addReceiptsPage.setReceiptTotalAmount(updatedMapData.get("Total_Amount"));
		addReceiptsPage.setReceiptReceivedAmount(updatedMapData.get("Received_Amount"));		
		assertEquals(Integer.parseInt(addReceiptsPage.getReceiptBalanceAmount()), Integer.parseInt(updatedMapData.get("Total_Amount"))-Integer.parseInt(updatedMapData.get("Received_Amount")));
		addReceiptsPage.setReceiptTransactionDetails(updatedMapData.get("Transaction_Details"));
		addReceiptsPage.clickReceiptSubmitBtn();
		assertEquals(addReceiptsPage.getAddReceiptSuccessmessage(), "Success!Receipt added successfully!");

	}
	
	@Test(enabled=true, priority=4,dataProvider="EditReceiptDataFromGetData", dependsOnMethods = "editReceipt")
	public void validateUpdatedReceiptInTableView(Map<String,String>updatedMapData)
	{
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToReceiptsNavigationTab();
		commonPageObjects.clickViewReceiptsLink();
				
		assertEquals(driver.getTitle(),"List of Receipts");
		
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String currentDate = formatter.format(date);
						
		LocalDate beforeFiveDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeFiveDays);		
		
		ViewReceiptsPage viewReceiptsPage = new ViewReceiptsPage(driver);
		viewReceiptsPage.setReceiptFromDate(pastDate);
		viewReceiptsPage.setReceiptToDate(currentDate);
		viewReceiptsPage.clickReceiptSearchBtn();
				
		String[] str1 = updatedMapData.get("Resident").split("\\(");
		String residentName = str1[0].trim();
				
		WebElement receiptRowData = viewReceiptsPage.getTableRowDataElement(receiptNum, residentName, 
				updatedMapData.get("Payment_For"), updatedMapData.get("Total_Amount"), updatedMapData.get("Received_Amount"),
				updatedMapData.get("Balance_Amount"));
		assertEquals(receiptRowData.isDisplayed(),true);
				
	}
	
	@Test(priority=5,enabled=true,dependsOnMethods = "validateUpdatedReceiptInTableView" , dataProvider="EditReceiptDataFromGetData")
	public void validateUpdatedReceiptInvoiceFromViewBtn(Map<String,String>updatedMapData)
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");
		
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		commonPageObjects.navigateToReceiptsNavigationTab();
		commonPageObjects.clickViewReceiptsLink();
				
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String currentDate = formatter.format(date);
						
		LocalDate beforeFiveDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeFiveDays);		
		
		ViewReceiptsPage viewReceiptsPage = new ViewReceiptsPage(driver);
		viewReceiptsPage.setReceiptFromDate(pastDate);
		viewReceiptsPage.setReceiptToDate(currentDate);
		viewReceiptsPage.clickReceiptSearchBtn();
		viewReceiptsPage.clickViewiewReceiptBtn(receiptNum);
		
		String pageSource = driver.getPageSource();
		
		String[] str1 = updatedMapData.get("Resident").split("\\(");
		String residentName = str1[0].trim();
		
		pageSource.contains(residentName);
		pageSource.contains(updatedMapData.get("Payment_For"));
		pageSource.contains(updatedMapData.get("Total_Amount"));
		pageSource.contains(updatedMapData.get("Received_Amount"));
		pageSource.contains(updatedMapData.get("Balance_Amount"));
		pageSource.contains(updatedMapData.get("Transaction_Details"));
	}
	
}