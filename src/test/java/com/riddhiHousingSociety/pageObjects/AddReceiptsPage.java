package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddReceiptsPage {
	
	WebDriver driver;
		
	@FindBy(how=How.NAME, using="userid")
	@CacheLookup
	WebElement receiptSelectResident;
	
	@FindBy(how=How.NAME, using="payment_desc")
	@CacheLookup
	WebElement receiptPaymentFor;
	
	@FindBy(how=How.NAME, using="mode_of_payment")
	@CacheLookup
	WebElement receiptSelectPaymentMode;
	
	@FindBy(how=How.NAME, using="total_amount")
	@CacheLookup
	WebElement receiptTotalAmount;
	
	@FindBy(how=How.NAME, using="received_amount")
	@CacheLookup
	WebElement receiptReceivedAmount;
	
	@FindBy(how=How.NAME, using="balance_amount")
	@CacheLookup
	WebElement receiptBalanceAmount;
	
	@FindBy(how=How.NAME, using="payment_note")
	@CacheLookup
	WebElement receiptTransactionDetails;
	
	@FindBy(how=How.NAME, using="submit")
	@CacheLookup
	WebElement receiptSubmitBtn;
	
	@FindBy(how=How.XPATH, using="//button[@type=\"reset\"]")
	@CacheLookup
	WebElement receiptClearBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement addReceiptSuccessMessage;
	
	public AddReceiptsPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public void selectReceiptResident(String residentName)
	{
		Select selectResident = new Select(receiptSelectResident);
		selectResident.selectByVisibleText(residentName);
	}
	
	public void setReceiptPaymentFor(String paymentFor)
	{
		receiptPaymentFor.clear();
		receiptPaymentFor.sendKeys(paymentFor);
	}
	
	public void selectReceiptPaymentMode(String paymentMode)
	{
		Select selectPaymentMode = new Select(receiptSelectPaymentMode); 
		selectPaymentMode.selectByVisibleText(paymentMode);
	}
	
	public void setReceiptTotalAmount(String totalAmount)
	{
		receiptTotalAmount.clear();
		receiptTotalAmount.sendKeys(totalAmount);
	}

	public void setReceiptReceivedAmount(String receivedAmount)
	{
		receiptReceivedAmount.clear();
		receiptReceivedAmount.sendKeys(receivedAmount);
	}

	public String getReceiptBalanceAmount()
	{
		String balanceAmount = receiptBalanceAmount.getAttribute("value");
		return balanceAmount;
	}

	public void setReceiptTransactionDetails(String transactionDetails)
	{
		receiptTransactionDetails.clear();
		receiptTransactionDetails.sendKeys(transactionDetails);
	}
	
	public void clickReceiptSubmitBtn()
	{
		receiptSubmitBtn.click();
	}
	
	public void clickReceiptClearBtn()
	{
		receiptClearBtn.click();
	}
	
	public String getAddReceiptSuccessmessage()
	{
		String addContentSuccessMsg = addReceiptSuccessMessage.getText();
		return addContentSuccessMsg;
	}

	

}
