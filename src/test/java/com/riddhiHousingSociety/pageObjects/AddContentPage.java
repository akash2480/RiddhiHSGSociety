package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.riddhiHousingSociety.testCases.BaseClass;

public class AddContentPage extends BaseClass {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using="//span[text()='Contents']/parent::a")
	@CacheLookup
	WebElement contentsNavigationTab;	
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Add New Content')]")
	@CacheLookup
	WebElement addNewContentLink;
	
	@FindBy(how=How.NAME, using="sectionname")
	@CacheLookup	
	WebElement selectSectionName;
	
	@FindBy(how=How.NAME, using="contenttitle")
	@CacheLookup
	WebElement contentTitle;
	
	@FindBy(how=How.NAME, using="price")
	@CacheLookup
	WebElement contentPrice;
	
	@FindBy(how=How.NAME, using="sequence")
	@CacheLookup
	WebElement contentSequence;
	
	@FindBy(how=How.NAME, using="alt_tag")
	@CacheLookup
	WebElement contentImageAltTag;
	
	@FindBy(how=How.NAME, using="contentdesc")
	@CacheLookup
	WebElement contentDescription;
	
	@FindBy(how=How.NAME, using="content_date")
	@CacheLookup
	WebElement contentDate;
	
	@FindBy(how=How.NAME, using="content_location")
	@CacheLookup
	WebElement contentLocation;
	
	@FindBy(how=How.NAME, using="link")
	@CacheLookup
	WebElement contentLink;
	
	@FindBy(how=How.NAME, using="image_filename")
	@CacheLookup
	WebElement contentImage;
	
	@FindBy(how=How.NAME, using="submit")
	@CacheLookup
	WebElement contentSubmitBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement addContentSuccessMessage;
	
	public AddContentPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToContentsNavigationTab()
	{
		contentsNavigationTab.click();
	}
	
	public void clickOnAddNewContentLink()
	{
		addNewContentLink.click();
	}
	
	public void selectSectionName(String sectionName)
	{
		Select selectSection = new Select(selectSectionName);
		selectSection.selectByValue(sectionName);
	}
	
	public void setContentTitle(String contentTitleString)
	{	
		contentTitle.clear();
		contentTitle.sendKeys(contentTitleString);
	}
	
	public void setContentPrice(String contentpriceAmount)
	{
		contentPrice.clear();
		contentPrice.sendKeys(contentpriceAmount);
	}
	
	public void setContentSequence(String contentSequenceString)
	{
		contentSequence.clear();
		contentSequence.sendKeys(contentSequenceString);
	}
	
	public void setContentImageAltTag(String contentImageAltTageString)
	{
		contentImageAltTag.clear();
		contentImageAltTag.sendKeys(contentImageAltTageString);
	}
	
	public void setContentDescription(String contentDescriptionText)
	{
		contentDescription.clear();
		contentDescription.sendKeys(contentDescriptionText);
	}
	
	public void setContentDate(String contentDateString)
	{
		contentDate.clear();
		contentDate.sendKeys(contentDateString);
	}
	
	public void setContentLocation(String contentLocationString)
	{
		contentLocation.clear();
		contentLocation.sendKeys(contentLocationString);
	}
	
	public void setContentLink(String contentLinkString)
	{
		contentLink.clear();
		contentLink.sendKeys(contentLinkString);
	}
	
	public void setContentImage(String contentImageData)
	{
		contentImage.sendKeys(contentImageData);
	}
	
	public void clickOnSubmitButton()
	{
		contentSubmitBtn.click();
	}
	
	public String getAddContentSuccessMessage()
	{
		String addContentSuccessMsg = addContentSuccessMessage.getText();
		return addContentSuccessMsg;
	}
	
	
	
}
