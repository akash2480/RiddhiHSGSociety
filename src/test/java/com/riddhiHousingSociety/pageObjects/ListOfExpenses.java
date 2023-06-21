package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ListOfExpenses {

	WebDriver driver;

	@FindBy(how = How.NAME, using = "from_date")
	@CacheLookup
	WebElement expenseFromDate;

	@FindBy(how = How.NAME, using = "to_date")
	@CacheLookup
	WebElement expenseToDate;

	@FindBy(how = How.NAME, using = "user_name")
	@CacheLookup
	public WebElement expenseSelectEmployee;

	@FindBy(how = How.NAME, using = "submit")
	@CacheLookup
	WebElement expenseSearchButton;

	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement deleteExpenseSuccessMessage;

	@FindBy(how=How.XPATH, using="//th[text()='Total Expenses']/ancestor::table/tbody//td")
	WebElement totalExpenses;
	
	public ListOfExpenses(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectEmployeeFromDropdown(String employeeName) 
	{
		Select selectEmployee = new Select(expenseSelectEmployee);
		selectEmployee.selectByValue(employeeName);
	}
	
	public void setToDate(String toDate)
	{	
		expenseToDate.click();
		expenseToDate.sendKeys(toDate);
	}
	
	public void setFromDate(String fromDate)
	{
		expenseFromDate.click();
		expenseFromDate.sendKeys(fromDate);
	}
	
	public void clickEmployeeSearchBtn() {
		expenseSearchButton.click();
	}

	public WebElement getTableRowDataElement(String employeeName, String expenseAmount, String expenseType,
			String expenseMode, String expenseDate, String expenseNoteDescription) 
	{
		String xpath = "//td[text()='" 
						+ employeeName + "']/preceding-sibling::td[text()='" 
						+ expenseNoteDescription + "']/preceding-sibling::td[text()='" 
						+ expenseMode + "']/preceding-sibling::td/preceding-sibling::td[text()='" + "Rs. " 
						+ expenseAmount + "']/preceding-sibling::td[text()='" 
						+ expenseDate + "']";

		WebElement rowData = driver.findElement(By.xpath(xpath));

		return rowData;
	}

	public void deleteExpenseTableRowData(String employeeName, String expenseAmount, String expenseType,
			String expenseMode, String expenseDate, String expenseNoteDescription) 
	{
		String xpath = "//td[text()='" 
						+ employeeName + "']/preceding-sibling::td[text()='" 
						+ expenseNoteDescription + "']/preceding-sibling::td[text()='" 
						+ expenseMode + "']/preceding-sibling::td/preceding-sibling::td[text()='" + "Rs. " 
						+ expenseAmount	+ "']/preceding-sibling::td[text()='" 
						+ expenseDate + "']/parent::tr//a[text()=\"Delete\"]";

		WebElement rowData = driver.findElement(By.xpath(xpath));
		rowData.click();
	}

	public boolean isWebELementPresentOnWebPage(WebElement webElement) 
	{
		boolean isWebElementDisplayed = webElement.isDisplayed();
		return isWebElementDisplayed;
	}

	public String getDeleteExpenseSuccessMessage() 
	{
		String deleteExpenseSuccessMsg = deleteExpenseSuccessMessage.getText();
		return deleteExpenseSuccessMsg;
	}

	public WebElement retryForElement(By Locator, int timesToTry) 
	{
		WebElement foundElement = null;

		for (int repeat = 0; repeat <= timesToTry; repeat++) 
		{
			try {
				foundElement = driver.findElement(Locator);
				break;

			} catch (StaleElementReferenceException exception) {
				exception.printStackTrace();
			}

		}

		if (foundElement == null) 
		{
			throw new NotFoundException("The Element was not located");
		}

		return foundElement;

	}
	
	public int getTotalExpenses()
	{
		String totalExpensesText = totalExpenses.getText();
		String[] totalExpenseTextSplit = totalExpensesText.split("/-");
		String totalExpenseAmount = totalExpenseTextSplit[0].substring(4);
		return Integer.parseInt(totalExpenseAmount);
	}
	
	
}
