package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.riddhiHousingSociety.pageObjects.AddExpensePage;
import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.utilities.XLUtils;

public class TestClassDataDriven extends BaseClass{

	public static void main2(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddUserData.xlsx";
		//String filePath = System.getProperty("user.dir")+"./src/test/resources/TestExcelData.xlsx";
		//String filePath = ".//com.riddhiHousingSociety.testData//TestExcelData.xlsx";
		
		int rowCount = XLUtils.getRowCount(filePath,"Sheet1");
		System.out.println(rowCount);
		int colCount = XLUtils.getCellCount(filePath, "Sheet1", 1);
		
		String [][] loginData = new String[rowCount+1][colCount];
		
		for(int i=0; i<=rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				loginData[i][j] = XLUtils.getCellData(filePath, "Sheet1", i, j);
			}
		}
		
		for (String[] strings : loginData) {
			System.out.println(Arrays.toString(strings));
		}
		
		Map<String, String> mapData = new HashMap<String, String>();
		
		for(int i=0; i<colCount;i++)
		{
			mapData.put(loginData[0][i], loginData[1][i]);
		}
		
		for (String key: mapData.keySet()){
			System.out.println(key+ " = " + mapData.get(key));
		} 
		
		System.out.println(mapData.get("Aadhar_No"));
				
	}
	
	@Test(dataProvider = "AddExpenseDataFromGetData")
	public void addExpenseDetails(Map<String, String> mapData)
	{
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		
		AddExpensePage addExpense = new AddExpensePage(driver);
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(addExpense.expensesNavigationTab));
				
		addExpense.navigateToExpensesTab();
		addExpense.clickAddExpenseLink();		
		softAssert.assertEquals(driver.getTitle(),"Add Expense");
		
		addExpense.setExpenseDoneBy(mapData.get("Expense_Done_By"));
		addExpense.setExpenseAmount(mapData.get("Expense_Amount"));
		addExpense.selectExpenseType(mapData.get("Expense_Type"));
		addExpense.selectExpenseMode(mapData.get("Expense_Mode"));
		addExpense.setExpenseDate(mapData.get("Expense_Date"));
		addExpense.setExpenseNote(mapData.get("Expense_Note"));
		addExpense.clickSubmitBtn();
		assertEquals(addExpense.getAddExpenseSuccessMessage(), "Success! Expense added successfully!");
		softAssert.assertAll();
				
	}
	
	@DataProvider(name="AddExpenseDataFromGetData")
	public Object[][] getData() throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddExpense.xlsx";
		Object [][] addExpenseData =  getExcelMapData(filePath);
		
		return addExpenseData;
	
	}
	
	
}
