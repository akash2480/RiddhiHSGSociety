package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ViewReceiptsPage {
		
	WebDriver driver;
	
	/*
	 * @FindBy(how=How.XPATH, using="//a[contains(text(),\"View Receipt's\")]")
	 * 
	 * @CacheLookup WebElement viewReceiptsLink;
	 */
	
	@FindBy(how=How.NAME, using="from_date")
	@CacheLookup
	WebElement receiptFromDate;
	
	@FindBy(how=How.NAME, using="to_date")
	@CacheLookup
	WebElement receiptToDate;
	
	@FindBy(how = How.NAME, using = "submit")
	@CacheLookup
	WebElement receiptSearchButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement deleteReceiptSuccessMessage;
	
	public ViewReceiptsPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * public void clickViewReceiptsLink() { viewReceiptsLink.click(); }
	 */
	public void setReceiptFromDate(String rFromDate)
	{
		receiptFromDate.click();
		receiptFromDate.sendKeys(rFromDate);
	}
	
	public void setReceiptToDate(String rToDate)
	{
		receiptToDate.click();
		receiptToDate.sendKeys(rToDate);
	}
	
	public void clickReceiptSearchBtn()
	{
		receiptSearchButton.click();;
	}
	
	public WebElement getTableRowDataElement(String residentName, String Payment_For, String Total_Amount, String Received_Amount, String Balance_Amount) 
	{
		String xpath = "//td[text()='"+residentName+"']/following-sibling::td[text()='"
						+Payment_For+"']/following-sibling::td[contains(text(),'"
						+Total_Amount+"')]/following-sibling::td[contains(text(),'"
						+Received_Amount+"')]/following-sibling::td[contains(text(),'"
						+Balance_Amount+"')]";
		
		WebElement rowData = driver.findElement(By.xpath(xpath));
		return rowData;
	}
	
	public WebElement getTableRowDataElement(String receiptNo, String residentName, String Payment_For, String Total_Amount, String Received_Amount, String Balance_Amount) 
	{
		String xpath = "//td[text()='"+residentName+"']/following-sibling::td[text()='"
						+Payment_For+"']/following-sibling::td[contains(text(),'"
						+Total_Amount+"')]/following-sibling::td[contains(text(),'"
						+Received_Amount+"')]/following-sibling::td[contains(text(),'"
						+Balance_Amount+"')]//ancestor::tr//td[text()='"+receiptNo+"']";
		
		WebElement rowData = driver.findElement(By.xpath(xpath));
		return rowData;
	}
	 
	public void clickViewiewReceiptBtn(String residentName, String Payment_For, String Total_Amount, String Received_Amount, String Balance_Amount) 
	{
		String viewBtnXpath = "//td[text()='"+residentName+"']/following-sibling::td[text()='"
				+Payment_For+"']/following-sibling::td[contains(text(),'"
				+Total_Amount+"')]/following-sibling::td[contains(text(),'"
				+Received_Amount+"')]/following-sibling::td[contains(text(),'"
				+Balance_Amount+"')]//ancestor::tr//a[text()='View Receipt']";

		WebElement rowDataViewReceipt = driver.findElement(By.xpath(viewBtnXpath));
		rowDataViewReceipt.click();
	}
	
	public void clickActionBtnReceiptTableRowData(String residentName, String Payment_For, String Total_Amount, String Received_Amount, String Balance_Amount) 
	{	
		String actionBtnXpath = "//td[text()='"+residentName+"']/following-sibling::td[text()='"
				+Payment_For+"']/following-sibling::td[contains(text(),'"
				+Total_Amount+"')]/following-sibling::td[contains(text(),'"
				+Received_Amount+"')]/following-sibling::td[contains(text(),'"
				+Balance_Amount+"')]//ancestor::tr//button[contains(text(),'Action')]";

		WebElement rowDataActionBtn = driver.findElement(By.xpath(actionBtnXpath));
		rowDataActionBtn.click();
	}

	public void editReceiptTableRowData(String residentName, String Payment_For, String Total_Amount, String Received_Amount, String Balance_Amount) 
	{
		String editBtnXpath = "//td[text()='"+residentName+"']/following-sibling::td[text()='"
				+Payment_For+"']/following-sibling::td[contains(text(),'"
				+Total_Amount+"')]/following-sibling::td[contains(text(),'"
				+Received_Amount+"')]/following-sibling::td[contains(text(),'"
				+Balance_Amount+"')]//ancestor::tr//a[contains(text(),'Edit')]";

		WebElement rowDataEditReceiptBtn = driver.findElement(By.xpath(editBtnXpath));
		rowDataEditReceiptBtn.click();
	}

	public void deleteReceiptTableRowData(String residentName, String Payment_For, String Total_Amount, String Received_Amount, String Balance_Amount) 
	{
		String deleteBtnXpath = "//td[text()='"+residentName+"']/following-sibling::td[text()='"
				+Payment_For+"']/following-sibling::td[contains(text(),'"
				+Total_Amount+"')]/following-sibling::td[contains(text(),'"
				+Received_Amount+"')]/following-sibling::td[contains(text(),'"
				+Balance_Amount+"')]//ancestor::tr//a[contains(text(),'Delete')]";

		WebElement rowDataDeleteReceipt = driver.findElement(By.xpath(deleteBtnXpath));
		rowDataDeleteReceipt.click();
	}
	
	public void clickViewiewReceiptBtn(String receiptNo) 
	{
		String viewBtnXpath = "//td[text()='"+receiptNo+"']//ancestor::tr//a[text()='View Receipt']";
		WebElement rowDataViewReceipt = driver.findElement(By.xpath(viewBtnXpath));
		rowDataViewReceipt.click();
	}
	
	public void clickActionBtnReceiptTableRowData(String receiptNo) 
	{	
		String actionBtnXpath = "//td[text()='"+receiptNo+"']//ancestor::tr//button[contains(text(),'Action')]";
		WebElement rowDataActionBtn = driver.findElement(By.xpath(actionBtnXpath));
		rowDataActionBtn.click();
	}

	public void editReceiptTableRowData(String receiptNo) 
	{
		String editBtnXpath = "//td[text()='"+receiptNo+"']//ancestor::tr//a[contains(text(),'Edit')]";
		WebElement rowDataEditReceiptBtn = driver.findElement(By.xpath(editBtnXpath));
		rowDataEditReceiptBtn.click();
	}

	public void deleteReceiptTableRowData(String receiptNo) 
	{
		String deleteBtnXpath = "//td[text()='"+receiptNo+"']//ancestor::tr//a[contains(text(),'Delete')]";
		WebElement rowDataDeleteReceipt = driver.findElement(By.xpath(deleteBtnXpath));
		rowDataDeleteReceipt.click();
	}

	
	public boolean isWebELementPresentOnWebPage(WebElement webElement) 
	{
		boolean isWebElementDisplayed = webElement.isDisplayed();
		return isWebElementDisplayed;
	}
	
	public String getReceiptNumber(String residentName, String Payment_For, String Total_Amount, String Received_Amount, String Balance_Amount)
	{
		String receiptNoXpath = "//td[text()='"+residentName+"']/following-sibling::td[text()='"
				+Payment_For+"']/following-sibling::td[contains(text(),'"
				+Total_Amount+"')]/following-sibling::td[contains(text(),'"
				+Received_Amount+"')]/following-sibling::td[contains(text(),'"
				+Balance_Amount+"')]//ancestor::tr//td[1]";
		String receiptNo = driver.findElement(By.xpath(receiptNoXpath)).getText();
		return receiptNo;		
	}

}
