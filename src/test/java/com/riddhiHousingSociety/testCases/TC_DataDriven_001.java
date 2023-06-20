package com.riddhiHousingSociety.testCases;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.utilities.XLUtils;

public class TC_DataDriven_001 extends BaseClass{
	
	
	@Test(dataProvider = "loginTestData")
	public void loginDDT(String uname, String pwd)
	{
		LoginPage loginPage = new LoginPage(driver); 
		loginPage.setUserName(uname);		
		loginPage.setPassword(pwd);
		loginPage.clickSubitButton();
	}
	
	@DataProvider(name="loginTestData")
	public String[][] getData() throws IOException
	{
		String filePath = System.getProperty("user.dir")+"./src/test/java/com/riddhiHousingSociety/testData/TestExcelData.xlsx";
		//String filePath = System.getProperty("user.dir")+"./src/test/resources/TestExcelData.xlsx";
		//String filePath = ".//com.riddhiHousingSociety.testData//TestExcelData.xlsx";
		
		int rowCount = XLUtils.getRowCount(filePath,"Sheet1");
		int colCount = XLUtils.getCellCount(filePath, "Sheet1", 1);
		
		String [][] loginData = new String[rowCount][colCount];
		
		for(int i=1; i<=rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				loginData[i-1][j] = XLUtils.getCellData(filePath, "Sheet1", i, j);
			}
		}
		
		for (String[] strings : loginData) {
			System.out.println(Arrays.toString(strings));
		}
		
		return loginData;
	}

}
