package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddExpensePage {
	
	WebDriver driver;
		
	@FindBy(how=How.NAME, using="employee_name")
	@CacheLookup
	WebElement expenseDoneBy;
	
	@FindBy(name = "expense_amount")
	@CacheLookup
	WebElement expenseAmount;
	
	//Expense Type and Expense Mode both have same name="expense_mode"
	@FindBy(how=How.XPATH, using="//label[text()='Expense Type']/following-sibling::div/select")
	@CacheLookup
	WebElement expenseType;
		
	@FindBy(how=How.XPATH, using="//label[text()='Expense Mode']/following-sibling::div/select")
	@CacheLookup
	WebElement expenseMode;
	
	@FindBy(name="expense_date")
	@CacheLookup
	WebElement expenseDate;
	
	@FindBy(how=How.NAME, using="expense_note")
	@CacheLookup
	WebElement expenseNote;
	
	@FindBy(how=How.NAME, using="submit")
	@CacheLookup
	WebElement submitBtn;
	
	@FindBy(how=How.XPATH, using="//button[@type=\"reset\"]")
	@CacheLookup
	WebElement clearBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement addExpenseSuccessMessage;
			
	public AddExpensePage(WebDriver driver)
	{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setExpenseDoneBy(String employeeName)
	{
		expenseDoneBy.sendKeys(employeeName);
	}
	
	public void setExpenseAmount(String Amount)
	{
		expenseAmount.sendKeys(Amount);
	}
	
	public void selectExpenseType(String typeValue)
	{	
		Select selectExpenseType = new Select(expenseType);
		selectExpenseType.selectByValue(typeValue);
	}
	
	public void selectExpenseMode(String modeValue)
	{
		Select selectExpenseMode = new Select(expenseMode);
		selectExpenseMode.selectByValue(modeValue);
	}
	
	public void setExpenseDate(String expenseDated)
	{
		expenseDate.sendKeys(expenseDated);
	}
	
	public void setExpenseNote(String expenseNoteDescription)
	{
		expenseNote.sendKeys(expenseNoteDescription);
	}
	
	public void clickSubmitBtn()
	{
		submitBtn.click();
	}
	
	public void clickClearBtn()
	{
		clearBtn.click();
	}
	
	public String getAddExpenseSuccessMessage()
	{
		String addExpenseSuccessMsg = addExpenseSuccessMessage.getText();
		return addExpenseSuccessMsg;
	}
	
	
	
}
