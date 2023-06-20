package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.riddhiHousingSociety.pageObjects.ListOfExpenses;
import com.riddhiHousingSociety.pageObjects.AddExpensePage;

import com.riddhiHousingSociety.pageObjects.LoginPage;

public class TC_ListOfExpenses extends BaseClass {

	@Test(dataProvider = "AddExpenseDataFromGetData")
	public void validateExpenseData(Map<String, String> mapData) throws ParseException 
	{		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");

		AddExpensePage addExpensePage = new AddExpensePage(driver);
		addExpensePage.navigateToExpensesTab();

		ListOfExpenses listOfExpenses = new ListOfExpenses(driver);
		listOfExpenses.clickListExpenseLink();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(),"List Expenses");
				
		LocalDate date = LocalDate.parse(LocalDate.now().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String currentDate = formatter.format(date);
						
		LocalDate beforeFiveDays = date.minusDays(7); 
		String pastDate = formatter.format(beforeFiveDays);		
		
		listOfExpenses.setFromDate(pastDate);
		listOfExpenses.setToDate(currentDate);
		
		listOfExpenses.selectEmployeeFromDropdown(mapData.get("Expense_Done_By"));
		listOfExpenses.clickEmployeeSearchBtn();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date parsedDate = sdf.parse(mapData.get("Expense_Date"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

		WebElement expenseRowData = listOfExpenses.getTableRowDataElement(mapData.get("Expense_Done_By"),
				mapData.get("Expense_Amount"), mapData.get("Expense_Type"), mapData.get("Expense_Mode"),
				sdf2.format(parsedDate), mapData.get("Expense_Note"));
		
		int totalExpenseAmountUser = listOfExpenses.getTotalExpenses();
		assertEquals(totalExpenseAmountUser, Integer.parseInt(mapData.get("Expense_Amount")));

		if (listOfExpenses.isWebELementPresentOnWebPage(expenseRowData)) 
		{
			assertTrue(true);
		} 
		else 
		{
			assertFalse(true, "Expense data is not available in table");
		}

	}

	@Test(dataProvider = "AddExpenseDataFromGetData", dependsOnMethods = "validateExpenseData")
	public void deleteExpenseData(Map<String, String> mapData) throws ParseException, InterruptedException 
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		loginpage.clickSubitButton();
		assertEquals(driver.getTitle(), "Enquiries");

		AddExpensePage addExpensePage = new AddExpensePage(driver);
		addExpensePage.navigateToExpensesTab();

		ListOfExpenses listOfExpenses = new ListOfExpenses(driver);
		listOfExpenses.clickListExpenseLink();
		
		int totalExpenseAmount = listOfExpenses.getTotalExpenses();
		
		listOfExpenses.selectEmployeeFromDropdown(mapData.get("Expense_Done_By"));
		listOfExpenses.clickEmployeeSearchBtn();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date parsedDate = sdf.parse(mapData.get("Expense_Date"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

		WebElement expenseRowData = listOfExpenses.getTableRowDataElement(mapData.get("Expense_Done_By"),
				mapData.get("Expense_Amount"), mapData.get("Expense_Type"), mapData.get("Expense_Mode"),
				sdf2.format(parsedDate), mapData.get("Expense_Note"));
		
		int totalExpenseAmountUser = listOfExpenses.getTotalExpenses();
		assertEquals(totalExpenseAmountUser, Integer.parseInt(mapData.get("Expense_Amount")));
		
		listOfExpenses.deleteExpenseTableRowData(mapData.get("Expense_Done_By"), mapData.get("Expense_Amount"),
				mapData.get("Expense_Type"), mapData.get("Expense_Mode"), sdf2.format(parsedDate),
				mapData.get("Expense_Note"));

		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		assertEquals(alertMessage, "Are you sure you want to delete this?");
		alert.dismiss();

		// Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(expenseRowData));

		if (listOfExpenses.isWebELementPresentOnWebPage(expenseRowData)) 
		{
			assertTrue(true);
		} else {
			assertTrue(false, "Delete cancel operation Failed. Expense data is not visible in table");
		}

		listOfExpenses.deleteExpenseTableRowData(mapData.get("Expense_Done_By"), mapData.get("Expense_Amount"),
				mapData.get("Expense_Type"), mapData.get("Expense_Mode"), sdf2.format(parsedDate),
				mapData.get("Expense_Note"));
		
		alert.accept();
		assertEquals(listOfExpenses.getDeleteExpenseSuccessMessage(), "Success! Expense deleted successfully...");
		
		By selectEmployee = By.name("user_name");
		WebElement selectEmp = listOfExpenses.retryForElement(selectEmployee, 3);
		Select selectAgainEmployee = new Select(selectEmp);
		List<WebElement> employeeList =	selectAgainEmployee.getOptions();
		
		for (WebElement eachWebElement : employeeList) 
		{
			if(eachWebElement.getText().equals(mapData.get("Expense_Done_By")))
			{
				assertFalse(true);
				break;
			}
			
		} 
		assertTrue(true);
	
		
		if (!driver.getPageSource().contains(mapData.get("Expense_Done_By")))
		{
			assertTrue(true);
		} 
		else 
		{
			assertTrue(false, "Delete operation Failed. Expense data is still visible in table");
		}
		
		int totalExpenseAmountUpdated = listOfExpenses.getTotalExpenses();
		assertEquals(totalExpenseAmountUpdated, (totalExpenseAmount - totalExpenseAmountUser));
		
	}

	@DataProvider(name = "AddExpenseDataFromGetData")
	public Object[][] getData() throws IOException 
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestAddExpense.xlsx";
		Object[][] addExpenseData = getExcelMapData(filePath);

		return addExpenseData;
	}
}
