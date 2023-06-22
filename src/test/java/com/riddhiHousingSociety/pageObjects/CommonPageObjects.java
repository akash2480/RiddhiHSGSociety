package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
public class CommonPageObjects {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using="//span[text()='Dashboard']/parent::a")
	@CacheLookup
	private WebElement dashboardNavigationTab;
	
	@FindBy(how = How.XPATH, using="//span[text()='Sections']/parent::a")
	@CacheLookup
	private WebElement sectionNavigationTab;
	
	@FindBy(how = How.XPATH, using="//span[text()='Contents']/parent::a")
	@CacheLookup
	private WebElement contentsNavigationTab;
	
	@FindBy(how = How.XPATH, using="//span[text()=\"Receipt's\"]/parent::a")
	@CacheLookup
	private WebElement receiptsNavigationTab;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Users']/parent::a")
	@CacheLookup
	private WebElement usersNavigationTab;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Expenses']/parent::a")
	@CacheLookup
	private WebElement expensesNavigationTab;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Add New Content')]")
	@CacheLookup
	private WebElement addNewContentLink;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'View Contents')]")
	@CacheLookup
	private WebElement viewContentsLink;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Add Receipt')]")
	@CacheLookup
	private WebElement addReceiptsLink;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),\"View Receipt's\")]")
	@CacheLookup
	private WebElement viewReceiptsLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Add User')]")
	@CacheLookup
	private WebElement addUserLink;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'List Residents')]")
	@CacheLookup
	private WebElement listResidentsLink;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'List Staff')]")
	@CacheLookup
	private WebElement listStaffLink;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'List Tenants')]")
	@CacheLookup
	private WebElement listTenantsLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Add Expense')]")
	@CacheLookup
	private WebElement addExpenseLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'List Expenses')]")
	@CacheLookup
	private WebElement listExpensesLink;
	
	@FindBy(how=How.NAME, using="sampleTable_length")
	@CacheLookup
	private WebElement showEntriesCount;
	
	@FindBy(how=How.ID, using = "sampleTable_paginate")
	private WebElement paginationFooter;
	
	@FindBy(how=How.XPATH, using = "(//*[@id='sampleTable_paginate']//a)[last()-1]")
	private WebElement pagesCount;
	
	@FindBy(how=How.ID, using="sampleTable_previous")
	private WebElement previousBtnPagination;
	
	@FindBy(how=How.ID, using="sampleTable_next")
	private WebElement nextBtnPagination;
	
	public CommonPageObjects(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToSectionsNavigationTab()
	{
		sectionNavigationTab.click();
	}
	
	public void navigateToContentsNavigationTab()
	{
		contentsNavigationTab.click();
	}
	
	public void navigateToReceiptsNavigationTab()
	{
		receiptsNavigationTab.click();
	}
	
	public void navigateToUsersNavigationTab() {
		usersNavigationTab.click();
	}
	
	public void navigateToExpensesNavigationTab()
	{
		expensesNavigationTab.click();
	}
	
	public void clickAddNewContentLink()
	{
		addNewContentLink.click();
	}
	
	public void clickViewContentsLink()
	{
		viewContentsLink.click();
				
	}	
	
	public void clickAddReceiptLink()
	{
		addReceiptsLink.click();
	}
	
	public void clickViewReceiptsLink() 
	{
		viewReceiptsLink.click();
	}
	
	public void clickAddUsersLink() 
	{		
		addUserLink.click();
	}
	
	public void clickListResidentsLink()
	{
		listResidentsLink.click();
	}	
	
	public void clickAddExpenseLink()
	{
		addExpenseLink.click();
	}
	
	public void clickListExpenseLink() 
	{
		listExpensesLink.click();
	}
	
	public WebElement getSelectShowEntriesLocator()
	{
		return showEntriesCount;
	}
	
	public int getNumberOfPagesCount()
	{
		return Integer.parseInt(pagesCount.getText());	
	}
	
	public String getPreviousBtnAttribute(String attributeName)
	{
		String previousBtnAttr =  previousBtnPagination.getAttribute(attributeName);
		return previousBtnAttr;
	}
	
	public String getNextBtnAttribute(String attributeName)
	{
		String previousBtnAttr =  nextBtnPagination.getAttribute(attributeName);
		return previousBtnAttr;
	}
	
	public void clickNextBtnPagination()
	{
		nextBtnPagination.click();
		
	}
	
	public void clickPreviousBtnPagination()
	{
		previousBtnPagination.click();
	}
	
}
