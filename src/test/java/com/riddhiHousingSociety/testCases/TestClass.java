package com.riddhiHousingSociety.testCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.riddhiHousingSociety.utilities.XLUtils;

public class TestClass extends BaseClass{

	public static WebDriver driver;

	public static void main2(String[] args) {

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter formatedDateTime = DateTimeFormatter.ofPattern("ddMMYY_HHmmss");
		System.out.println(myDateObj.format(formatedDateTime));

	}

	public static void main3(String[] args) throws InterruptedException, IOException {

		/*
		 * ChromeDriver driver =new ChromeDriver();
		 * driver.get("https://tronsoftech.in/projects/riddhi/index.php");
		 * driver.manage().window().maximize();
		 * 
		 * driver.findElement(By.xpath("//input[@name=\"myusername\"]")).sendKeys(
		 * "Riddhi"); driver.findElement(By.name("mypassword")).sendKeys("");
		 * driver.findElement(By.xpath("//button[@type='submit']")).click();
		 * 
		 * Thread.sleep(5000); WebElement errorText =
		 * driver.findElement(By.xpath("//div[@class=\"alert alert-danger\"]")); String
		 * errorTextMessage = errorText.getText();
		 * 
		 * assertEquals(errorTextMessage, "Error!Password field should not left blank");
		 */

		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html"); // Pass the File instead of FilePath // for another way
		
		/*
		 * sparkReporter.config().setTheme(Theme.DARK);
		 * sparkReporter.config().setReportName("Report Name");
		 * sparkReporter.config().setDocumentTitle("Document Title");
		 * sparkReporter.config().setTimeStampFormat("dd-MM-YY-HH_mm_ss");
		 * sparkReporter.config().setCss(".badge-primary{background-color:#d565df}");
		 */
		
		//sparkReporter.loadJSONConfig( new File("./src/test/resources/extent-reports-config.json"));
		//sparkReporter.loadXMLConfig( new File("./src/test/resources/extent-reports-config.json"));
		
		//sparkReporter.viewConfigurer()//to change order of Tab
		
		extentReports.attachReporter(sparkReporter);

		extentReports.createTest("Text Based Test").log(Status.INFO, "Info1").log(Status.PASS, "<b>Info2<b>")
				.log(Status.WARNING, "<i>Info3<i>").log(Status.PASS, "<b><i>Info3<b><i>");

		String xmlData = "<menu id=\"file\" value=\"File\">\r\n" + "  <popup>\r\n"
				+ "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\r\n"
				+ "    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\r\n"
				+ "    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\r\n" + "  </popup>\r\n" + "</menu>";

		String jsonData = "{\"menu\": {\r\n" + "  \"id\": \"file\",\r\n" + "  \"value\": \"File\",\r\n"
				+ "  \"popup\": {\r\n" + "    \"menuitem\": [\r\n"
				+ "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n"
				+ "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n"
				+ "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n" + "    ]\r\n" + "  }\r\n" + "}}";

		extentReports.createTest("xmlData Text based Test").log(Status.INFO, xmlData);

		extentReports.createTest("jsonData Text Based Test").log(Status.INFO, jsonData);

		extentReports.createTest("xmlData based Test").info(MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML));

		extentReports.createTest("jsonData Based Test").log(Status.INFO,
				MarkupHelper.createCodeBlock(jsonData, CodeLanguage.JSON));

		List<String> listString = new ArrayList<String>();
		listString.add("MichaelScott");
		listString.add("JimHalpert");
		listString.add("DwightSchrute");

		Map<Integer, String> mapData = new HashMap<Integer, String>();
		mapData.put(100, "Michael");
		mapData.put(101, "Dwaight");
		mapData.put(102, "Jimothy");

		Set<Integer> setStrings = mapData.keySet();

		extentReports.createTest("list based Test").info(MarkupHelper.createOrderedList(listString))
				.info(MarkupHelper.createUnorderedList(listString));

		extentReports.createTest("Set based Test").info(MarkupHelper.createOrderedList(setStrings))
				.info(MarkupHelper.createUnorderedList(setStrings));

		extentReports.createTest("Map based Test").info(MarkupHelper.createOrderedList(mapData))
				.info(MarkupHelper.createUnorderedList(mapData));

		extentReports.createTest("This is highlighted test").info("This is Highlighted text")
				.info(MarkupHelper.createLabel("This is Highlighted text", ExtentColor.RED));

		try {
			int i = 5 / 0;
		} catch (Exception e) {
			extentReports.createTest("This is Exception Test").fail(e);
		}

		Throwable th = new RuntimeException("This is unchecked Exception");
		extentReports.createTest("This is Exception Test2").fail(th);

		extentReports.createTest("Screenshot Test1", "This is the test for attaching Screenshot")
				.info("This is info message").addScreenCaptureFromPath(xmlData, jsonData);

		/*
		 * extentReports.createTest("Test1") .log(Status.FAIL, "This is failed Test");
		 * 
		 * extentReports.createTest("Test3") .skip("This is skipped Test");
		 */

		driver = new ChromeDriver();
		driver.get("https:\\www.google.com");
		driver.manage().window().maximize();

		String base64Code = captureScreenshot();
		String path = captureScreenshot1("Google.com");

		extentReports.createTest("base64 Screenshot Test","This is the test for attaching Screenshot")
		.info("This is base64 Screenshot Test info")
		.addScreenCaptureFromBase64String(base64Code,"Google Homepage");
 
		extentReports.createTest("Screenshot file Test","This is the test for attaching Screenshot")
		.info("This is Screenshot file Test info")
		.addScreenCaptureFromPath(path,"Google Homepage")
		.assignAuthor("Michael")
		.assignAuthor("Jim")
		.assignCategory("Sanity")
		.assignCategory("Regression")
		.assignDevice("Firefox 105");
		
		extentReports.createTest("Screenshot file Test","This is the test for attaching Screenshot at log level")
		.info("This is Screenshot file Test info")
		.fail(MediaEntityBuilder.createScreenCaptureFromPath(path,"Google Homepage").build())
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code,"Google Homepage").build())
		.assignAuthor("Michael")
		.assignAuthor("Dwight")
		.assignCategory("Smoke")
		.assignCategory("Regression")
		.assignDevice("Chrome 99");
		
		
		
		extentReports.flush();
		driver.quit();

		Desktop.getDesktop().browse(new File("report.html").toURI());

	}

	public static String captureScreenshot1(String fileName) {

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter formatedDateTime = DateTimeFormatter.ofPattern("ddMMYY_HHmmss");
		String randomString = myDateObj.format(formatedDateTime);

		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("./Screenshots/" + fileName + "_" + randomString + ".png");

		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Screenshot saved successfully");

		return targetFile.getAbsolutePath();
	}

	public static String captureScreenshot() {

		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		String base64Code = takeScreenshot.getScreenshotAs(OutputType.BASE64);
		System.out.println("Screenshot saved successfully");
		return base64Code;
	}
	
	public static void main4(String[] args) throws IOException
	{
		String filePath = "./src/test/java/com/riddhiHousingSociety/testData/TestContent.xlsx";
		//String filePath = System.getProperty("user.dir")+"./src/test/resources/TestExcelData.xlsx";
		//String filePath = ".//com.riddhiHousingSociety.testData//TestExcelData.xlsx";
		
		int rowCount = XLUtils.getRowCount(filePath,"Sheet1");
		int colCount = XLUtils.getCellCount(filePath, "Sheet1", 1);
		
		String [][] loginData = new String[rowCount][colCount];
		
		for(int i=1; i<=rowCount; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				loginData[i-1][j] = XLUtils.getCellData(filePath, "Sheet1", i, j);
			}
		}
		
		for (String[] strings : loginData) {
			System.out.println(Arrays.toString(strings));
		}
		
		//return loginData;
	}
	
	public static void main(String[] args) throws ParseException, IOException
	{
		
		
		/*
		 * String date="6/15/2023"; SimpleDateFormat sdf = new
		 * SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH); Date parsedDate =
		 * sdf.parse(date); SimpleDateFormat print = new SimpleDateFormat("dd-MM-yyyy");
		 * System.out.println(print.format(parsedDate));
		 */
		 
		/*
		 * String excelFilePath =
		 * "./src/test/java/com/riddhiHousingSociety/testData/TestAddExpense.xlsx"; int
		 * rowCount = XLUtils.getRowCount(excelFilePath, "Sheet1");
		 * System.out.println(rowCount); int colCount =
		 * XLUtils.getCellCount(excelFilePath, "Sheet1", 1); DataFormatter formatter =
		 * new DataFormatter();
		 * 
		 * 
		 * 
		 * LocalDate date = LocalDate.parse(LocalDate.now().toString());
		 * DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		 * String currentTime = formatter1.format(date);
		 * System.out.println("Current time: "+currentTime);
		 * 
		 * LocalDate beforeFiveMonths = date.minusDays(5); String pastTime =
		 * formatter1.format(beforeFiveMonths);
		 * System.out.println("Past time: "+pastTime);
		 * 
		 * 
		 * XLUtils.setCellData(excelFilePath, "Sheet1", 1, 4, pastTime);
		 */
        
		String s1 = "Rs. 29500/-";
		System.out.println(s1.substring(4,9));
		 
		String[] sentences = s1.split("/-"); 
		
		System.out.println(Integer.parseInt(sentences[0].substring(4)));
		System.out.println(LocalDate.now());
		
		String s2 = "Ankita Chheda (A Wing - 302)";
		String[] s3 = s2.split("\\(");
		System.out.println(s3[0]);
		
		String s4 = "06/10/2023";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date parsedDate = sdf.parse(s4);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf2.format(parsedDate));
		
		String randomAlphaNum = RandomStringUtils.randomAlphanumeric(8);	
		System.out.println(randomAlphaNum);
		
		System.out.println(getDateinFormat("06/10/2023","MM/dd/yyyy","yyyy-MM-dd"));	
		
	}

}
