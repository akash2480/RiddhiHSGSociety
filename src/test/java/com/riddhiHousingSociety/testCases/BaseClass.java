package com.riddhiHousingSociety.testCases;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.riddhiHousingSociety.utilities.ReadConfig;
import com.riddhiHousingSociety.utilities.XLUtils;

import com.riddhiHousingSociety.pageObjects.*;

public class BaseClass {
	
	public static WebDriver driver;
	ReadConfig readConfig = new ReadConfig();	
		
	public String url = readConfig.getApplicationUrl();
	public String userName = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public static Logger log;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void setup(@Optional("chrome") String br)
	{	
		log = LogManager.getLogger(BaseClass.class);
		
		
		if(br.equals("chrome"))
		{
			driver = new ChromeDriver();			
		}
		else if(br.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else 
		{	if(br.equals("edge"))
			driver = new EdgeDriver();
		}
	
		driver.get(url);
		log.info("Opening URL");
		driver.manage().window().maximize();
		log.info("Maximizing window");
		
		//Set up implicit wait here
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	@AfterMethod
	public void teardown() throws InterruptedException 
	{
		
		//driver.quit();
		
	}
	
	public void captureScreenshot(String fileName)
	{	
		DateTimeFormatter formatedDateTime = DateTimeFormatter.ofPattern("ddMMYY_HHmmss");
		String timeStampString = LocalDateTime.now().format(formatedDateTime);
		
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("./Screenshots/"+fileName+timeStampString+".png");
		
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Screenshot saved successfully");
				
	}
	
	public static Object[][] getExcelMapData(String excelFilePath) throws IOException
	{
		int rowCount = XLUtils.getRowCount(excelFilePath, "Sheet1");
				
		int colCount = XLUtils.getCellCount(excelFilePath, "Sheet1", 1);
		DataFormatter formatter = new DataFormatter();
		
		Object[][] obj = new Object[rowCount][1];
		Map<String,String> mapContentData; 

		for (int i = 1; i <= rowCount; i++) {
			mapContentData = new HashMap<String, String>();
			for (int j = 0; j < colCount; j++) {
								
				String key = formatter.formatCellValue(XLUtils.ws.getRow(0).getCell(j));
				String value = formatter.formatCellValue(XLUtils.ws.getRow(i).getCell(j));				
				
				mapContentData.put(key, value);
			}
			obj[i-1][0] = mapContentData;
		}
		return obj;
	}
 	
	
	
	public static String randomString(int characterCount)
	{
		 String randomString = RandomStringUtils.randomAlphabetic(characterCount);
		 return randomString;
		
	}
	
	public static String randomNumber(int digitCount) 
	{	
		String randomNum = RandomStringUtils.randomNumeric(digitCount);
		return randomNum;
	}
	
	public static String randomAlphaNumeric(int digitCount) 
	{	
		String randomAlphaNum = RandomStringUtils.randomAlphanumeric(digitCount);
		return randomAlphaNum;
	}
	
	public static String getDateinFormat(String Date, String givenFormat, String requiredFormat) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(givenFormat, Locale.ENGLISH);
		Date parsedDate = sdf.parse(Date);
		SimpleDateFormat sdf2 = new SimpleDateFormat(requiredFormat);
		String expectedDate = sdf2.format(parsedDate);
		return expectedDate;
	}
	
	public static void selectShowEntries(int entriesCount)
	{	
		CommonPageObjects commonPageObjects = new CommonPageObjects(driver);
		Select selectEntriesCount = new Select(commonPageObjects.getSelectShowEntriesLocator());
		selectEntriesCount.selectByVisibleText(Integer.toString(entriesCount));
	}
	
	public static int getRowCountTable()
	{
		List<WebElement> noOfRows = driver.findElements(By.xpath("//table/tbody//tr"));
		return noOfRows.size();
	}	
	
}
