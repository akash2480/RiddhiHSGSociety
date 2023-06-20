package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.riddhiHousingSociety.pageObjects.AddExpensePage;
import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.utilities.XLUtils;

public class TC_AddExpense extends BaseClass {

	@Test(dataProvider = "AddExpenseDataFromGetData")
	public void addExpenseDetails(Map<String, String> mapData) 
	{
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");

		AddExpensePage addExpense = new AddExpensePage(driver);

		// Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(addExpense.expensesNavigationTab));

		addExpense.navigateToExpensesTab();
		addExpense.clickAddExpenseLink();
		softAssert.assertEquals(driver.getTitle(), "Add Expense - Riddhi Co-op Housing Society");

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

	@DataProvider(name = "AddExpenseDataFromGetData")
	public Object[][] getData() throws IOException 
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddExpense.xlsx";
		
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						
		LocalDate beforeFiveDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeFiveDays);
		
		XLUtils.setCellData(filePath, "Sheet1", 1, 4, pastDate);
		Object[][] addExpenseData = getExcelMapData(filePath);

		return addExpenseData;

	}

}
