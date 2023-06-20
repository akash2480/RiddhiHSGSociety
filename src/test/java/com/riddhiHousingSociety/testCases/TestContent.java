package com.riddhiHousingSociety.testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.LoginPage;
import com.riddhiHousingSociety.utilities.XLUtils;

public class TestContent extends BaseClass {

	@Test
	public void addContent() throws IOException, InterruptedException {
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		lpg.setPassword(password);
		lpg.clickSubitButton();

		driver.findElement(By.xpath("//span[text()='Contents']/parent::a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Add New Content')]")).click();
		
		//*************************************************************************************
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestContent.xlsx";

		int rowCount = XLUtils.getRowCount(filePath, "Sheet1");
		System.out.println(rowCount);
		int colCount = XLUtils.getCellCount(filePath, "Sheet1", 1);

		String[][] loginData = new String[rowCount + 1][colCount];

		for (int i = 0; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i][j] = XLUtils.getCellData(filePath, "Sheet1", i, j);
			}
		}

		Map<String, String> mapData = new HashMap<String, String>();

		for (int i = 0; i < colCount; i++) {
			mapData.put(loginData[0][i], loginData[1][i]);
		}
		
		//*****************************************************************************************
		
		
		
		
		WebElement selectSection = driver.findElement(By.xpath("//select[@name=\"sectionname\"]"));

		Select selectSectionDropDown = new Select(selectSection);
		selectSectionDropDown.selectByValue(mapData.get("Section Name"));
		
		driver.findElement(By.xpath("//input[@name=\"contenttitle\"]")).sendKeys(mapData.get("Title"));
		
		driver.findElement(By.xpath("//input[@name=\"price\"]")).sendKeys(mapData.get("Price"));
		
		driver.findElement(By.xpath("//input[@name=\"sequence\"]")).sendKeys(mapData.get("Sequence"));
		
		driver.findElement(By.xpath("//input[@name=\"alt_tag\"]")).sendKeys(mapData.get("Alt Tag"));
		
		driver.findElement(By.xpath("//textarea[@name=\"contentdesc\"]")).sendKeys(mapData.get("Description"));
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name=\"content_date\"]")).sendKeys(mapData.get("Date"));
		
		driver.findElement(By.xpath("//input[@name=\"content_location\"]")).sendKeys(mapData.get("Location"));
		
		driver.findElement(By.xpath("//input[@name=\"link\"]")).sendKeys(mapData.get("Link"));
		
		//driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

		
		
		

	}

}
