package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.AddReceiptsPage;
import com.riddhiHousingSociety.pageObjects.CommonPageObjects;
import com.riddhiHousingSociety.pageObjects.LoginPage;


public class TC_AddReceipt extends BaseClass{

	@Test(dataProvider = "AddReceiptDataFromGetData")
	public void addReceipt(Map<String, String> mapData)
	{		
			LoginPage lpg = new LoginPage(getDriver());
			lpg.setUserName(userName);
			lpg.setPassword(password);
			lpg.clickSubitButton();
			
			CommonPageObjects commonPageObjects = new CommonPageObjects(getDriver());
			commonPageObjects.navigateToReceiptsNavigationTab();
			commonPageObjects.clickAddReceiptLink();
			
			AddReceiptsPage addReceiptPage = new AddReceiptsPage(getDriver());
			assertEquals(getDriver().getTitle(),"Add Receipt - Riddhi Co-op Housing Society");
							
			addReceiptPage.selectReceiptResident(mapData.get("Resident"));
			addReceiptPage.setReceiptPaymentFor(mapData.get("Payment_For"));
			addReceiptPage.selectReceiptPaymentMode(mapData.get("Payment_Mode"));
			addReceiptPage.setReceiptTotalAmount(mapData.get("Total_Amount"));
			addReceiptPage.setReceiptReceivedAmount(mapData.get("Received_Amount"));
			//addReceiptPage.setReceiptBalanceAmount(mapData.get("Balance_Amount"));
			//assertEquals(Integer.parseInt(addReceiptPage.getReceiptBalanceAmount()), Integer.parseInt(mapData.get("Total_Amount"))-Integer.parseInt(password));
			addReceiptPage.setReceiptTransactionDetails(mapData.get("Transaction_Details"));
			addReceiptPage.clickReceiptSubmitBtn();
			assertEquals(addReceiptPage.getAddReceiptSuccessmessage(), "Success!Receipt added successfully!");
			
	}
	
	@DataProvider(name="AddReceiptDataFromGetData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddReceipt.xlsx";
		Object [][] addReceiptData =  getExcelMapData(filePath);
		
		return addReceiptData;	
	}
	
}
