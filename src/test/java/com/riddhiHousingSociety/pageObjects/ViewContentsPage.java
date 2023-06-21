package com.riddhiHousingSociety.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ViewContentsPage {
			
	WebDriver driver;
	
	/*
	 * @FindBy(how=How.XPATH, using="//a[contains(text(),'View Contents')]")
	 * 
	 * @CacheLookup WebElement viewContentsLink;
	 */
	
	@FindBy(how=How.XPATH, using="//input[@type=\"search\"]")
	@CacheLookup
	WebElement contentSearchField;
	
	@FindBy(how=How.XPATH, using="//input[@type=\"file\"]")
	WebElement addPhotosInputField;
	
	@FindBy(how=How.NAME, using="add_photo")
	WebElement addPhotosUploadBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement addPhotoSuccessMessage;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement deletePhotoSuccessMessage;
	
	@FindBy(how=How.NAME, using="link")
	WebElement addVideoLinkInputField;	
	
	@FindBy(how=How.NAME, using="add_video")
	WebElement addVideoLinkSubmitBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement addVideoSuccessMessage;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
	WebElement deleteVideoSuccessMessage;
			
	public ViewContentsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getContentTableRowDataElement(String contentTitle, String sectionName) 
	{
		String contentRowData = "//td[contains(text(),'"+contentTitle+"')]/preceding-sibling::td[1][contains(text(),'"+sectionName+"')]//ancestor::tr//td[1]";
		WebElement rowData = driver.findElement(By.xpath(contentRowData));
		return rowData;
	}
	
	public String getContentId(String contentTitle, String sectionName)
	{
		String contentIdXpath = "//td[contains(text(),'"+contentTitle+"')]/preceding-sibling::td[1][contains(text(),'"+sectionName+"')]//ancestor::tr//td[1]";
		String contentId = driver.findElement(By.xpath(contentIdXpath)).getText();		
		return contentId;		
	}
	
	/*
	 * public void clickViewContentsLink() { viewContentsLink.click();
	 * 
	 * }
	 */
	
	//works with contentID as well
	public void searchContentTitle(String contentTitle) 
	{
		contentSearchField.sendKeys(contentTitle);
	}
	
	public void clickActionsBtnViewContent(String contentID)
	{
		WebElement contentActionBtn = driver.findElement(By.xpath("//td[text()='"+contentID+"']//ancestor::tr//button[contains(text(),'Actions')]"));
		contentActionBtn.click();		
	}
	
	public void clickActionViewBtnViewContent(String contentID)
	{
		WebElement contentActionBtn = driver.findElement(By.xpath("//td[text()='"+contentID+"']//ancestor::tr//a[contains(text(),'View')]"));
		contentActionBtn.click();		
	}
	
	public void clickActionEditBtnViewContent(String contentID)
	{
		WebElement contentActionBtn = driver.findElement(By.xpath("//td[text()='"+contentID+"']//ancestor::tr//a[contains(text(),'Edit')]"));
		contentActionBtn.click();		
	}
	
	public void clickActionAddPhotosBtnViewContent(String contentID)
	{
		WebElement contentActionBtn = driver.findElement(By.xpath("//td[text()='"+contentID+"']//ancestor::tr//a[contains(text(),'Add Photos')]"));
		contentActionBtn.click();		
	}
	
	public void clickActionsAddVideosBtnViewContent(String contentID)
	{
		WebElement contentActionBtn = driver.findElement(By.xpath("//td[text()='"+contentID+"']//ancestor::tr//a[contains(text(),'Add Videos')]"));
		contentActionBtn.click();		
	}
	
	public void clickActionsDeleteBtnViewContent(String contentID)
	{
		WebElement contentActionBtn = driver.findElement(By.xpath("//td[text()='"+contentID+"']//ancestor::tr//a[contains(text(),'Delete')]"));
		contentActionBtn.click();		
	}
	
	public String getContentTitleName()
	{
		WebElement contentTitleLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Title :']/following-sibling::span"));
		String contentTitle = contentTitleLoc.getText().trim();
		return contentTitle;
	}
	
	public String getContentPrice()
	{
		WebElement contentPriceLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Price :']/following-sibling::span"));
		String contentPrice = contentPriceLoc.getText().trim();
		return contentPrice;
	}
	
	public String getContentDescription()
	{
		WebElement contentDescriptionLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Description :']/following-sibling::span"));
		String contentDescription = contentDescriptionLoc.getText().trim();
		return contentDescription;
	}
	
	public String getContentSectionName()
	{
		WebElement contentSectionNameLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Section Name :']/following-sibling::span"));
		String contentSectionName = contentSectionNameLoc.getText().trim();
		return contentSectionName;
	}
	
	public String getContentSequence()
	{
		WebElement contentSequenceLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Sequence :']/following-sibling::span"));
		String contentSequence = contentSequenceLoc.getText().trim();
		return contentSequence;
	}
	
	public String getContentAltTag()
	{
		WebElement contentAltTagLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Alt Tag :']/following-sibling::span"));
		String contentAltTag = contentAltTagLoc.getText().trim();
		return contentAltTag;
	}
	
	public String getContentDate()
	{
		WebElement contentDateLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Date :']/following-sibling::span"));
		String contentDate = contentDateLoc.getText().trim();
		return contentDate;
	}
	
	public String getcontentLocation()
	{
		WebElement contentLocationLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Location :']/following-sibling::span"));
		String contentLocation = contentLocationLoc.getText().trim();
		return contentLocation;
	}
	
	public String getcontentLink()
	{
		WebElement contentLinkLoc = driver.findElement(By.xpath("//strong[normalize-space(text())='Link :']/following-sibling::span"));
		String contentLink = contentLinkLoc.getText().trim();
		return contentLink;
	}
	
	public void setAddContentImage(String contentImageData)
	{
		addPhotosInputField.sendKeys(contentImageData);
	}
	
	public void clickaddContentImageUploadBtn()
	{
		addPhotosUploadBtn.click();
	}
	
	public String getAddPhotoSuccessMsg()
	{
		String addPhotoSuccessMsg = addPhotoSuccessMessage.getText();
		return addPhotoSuccessMsg;
	}
	
	public String getAddPhotoSrc()
	{
		WebElement addImagrLoc = driver.findElement(By.xpath("//h3[text()='Uploaded Photos']/following-sibling::img"));
		String addImageSrc = addImagrLoc.getAttribute("src");
		return addImageSrc;		
	}
	
	public WebElement getaddPhotoWebElement(String fileName)
	{	
		WebElement photoLoc = driver.findElement(By.xpath("//img[contains(@src,'"+fileName+"')]"));
		return photoLoc;
	}
	
	public void deleteAddedPhoto(String fileName)
	{
		WebElement addPhotoDeleteBtn = driver.findElement(By.xpath("//img[contains(@src,'"+fileName+"')]/following-sibling::a[@title=\"Delete\"]")); 
		addPhotoDeleteBtn.click();
	}
	
	public String getDeletePhotoSuccessMsg()
	{
		String deletePhotoSuccessMsg = deletePhotoSuccessMessage.getText();
		return deletePhotoSuccessMsg;
	}
	
	public void setAddVideoLinkToContent(String videoLink)
	{
		addVideoLinkInputField.sendKeys(videoLink);
	}
	
	public void clickAddVideoLinkSubmitBtn()
	{
		addVideoLinkSubmitBtn.click();
	}
	
	public String getAddVideoLinkSuccessMsg()
	{
		String addVideoLinkSuccessMsg = addVideoSuccessMessage.getText();
		return addVideoLinkSuccessMsg;
	}
	
	public WebElement getAddVideoLinkIFrame()
	{
		WebElement iFrameLoc = driver.findElement(By.xpath("//*[text()='Uploaded Videos']/following-sibling::iframe"));
		return iFrameLoc;
	}
	
	public String getAddVideoLinkSubString()
	{	
		
		driver.switchTo().frame(getAddVideoLinkIFrame());
		WebElement addVideoLinkThumbnail = driver.findElement(By.className("ytp-cued-thumbnail-overlay-image"));
		String addVideoUrlSubString = addVideoLinkThumbnail.getAttribute("style");
		System.out.println(addVideoUrlSubString);
		driver.switchTo().defaultContent();
		return addVideoUrlSubString;		
	}
	
	/*
	 * public WebElement getaddPhotoWebElement(String fileName) { WebElement
	 * photoLoc =
	 * driver.findElement(By.xpath("//img[contains(@src,'"+fileName+"')]")); return
	 * photoLoc; }
	 */
	
	public void deleteAddedVideoLink(String videoLinkSubstring)
	{
		WebElement addPhotoDeleteBtn = driver.findElement(By.xpath("//*[text()='Uploaded Videos']/following-sibling::iframe/following-sibling::a[@title='Delete']")); 
		addPhotoDeleteBtn.click();
	}
	
	public String getDeleteVideoSuccessMsg()
	{
		String deleteVideoSuccessMsg = deletePhotoSuccessMessage.getText();
		return deleteVideoSuccessMsg;
	}

	
	
	
	
	
	
}
