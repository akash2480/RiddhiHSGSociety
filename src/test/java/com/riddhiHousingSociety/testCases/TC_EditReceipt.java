package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddReceiptsPage;
import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.pageObjects.ViewReceiptsPage;

public class TC_EditReceipt extends BaseClass{
	
	@DataProvider(name = "EditReceiptDataFromGetData")
	public Object[][] getUpdatedData() throws IOException 
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestEditReceipt.xlsx";
		Object[][] addReceiptData = getExcelMapData(filePath);

		return addReceiptData;
	}

	@Test(dataProvider="EditReceiptDataFromGetData")
	public void editReceipt(Map<String,String>updatedMapData)
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");
		
		AddReceiptsPage addReceiptsPage = new AddReceiptsPage(driver);
		addReceiptsPage.navigateToReceiptssNavigationTab();
		
		ViewReceiptsPage viewReceiptsPage = new ViewReceiptsPage(driver);
		viewReceiptsPage.clickViewReceiptsLink();
		
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String currentDate = formatter.format(date);
						
		LocalDate beforeSevenDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeSevenDays);		
		
		viewReceiptsPage.setReceiptFromDate(pastDate);
		viewReceiptsPage.setReceiptToDate(currentDate);
		viewReceiptsPage.clickReceiptSearchBtn();
		//viewReceiptsPage.clickActionBtnReceiptTableRowData(mapData.get("Resident"), mapData.get("Payment_For"), mapData.get("Total_Amount"), mapData.get("Received_Amount"), mapData.get("Balance_Amount"));
		//viewReceiptsPage.editReceiptTableRowData(mapData.get("Resident"), mapData.get("Payment_For"), mapData.get("Total_Amount"), mapData.get("Received_Amount"), mapData.get("Balance_Amount"));
		
		addReceiptsPage.selectReceiptResident(updatedMapData.get("Resident"));
		addReceiptsPage.setReceiptPaymentFor(updatedMapData.get("Payment_For"));
		addReceiptsPage.selectReceiptPaymentMode(updatedMapData.get("Payment_Mode"));
		addReceiptsPage.setReceiptTotalAmount(updatedMapData.get("Total_Amount"));
		addReceiptsPage.setReceiptReceivedAmount(updatedMapData.get("Received_Amount"));
		//addReceiptPage.setReceiptBalanceAmount(mapData.get("Balance_Amount"));
		//assertEquals(Integer.parseInt(addReceiptPage.getReceiptBalanceAmount()), Integer.parseInt(mapData.get("Total_Amount"))-Integer.parseInt(password));
		addReceiptsPage.setReceiptTransactionDetails(updatedMapData.get("Transaction_Details"));
		addReceiptsPage.clickReceiptSubmitBtn();
		assertEquals(addReceiptsPage.getAddReceiptSuccessmessage(), "Success!Receipt added successfully!");
		
		
	}
}
