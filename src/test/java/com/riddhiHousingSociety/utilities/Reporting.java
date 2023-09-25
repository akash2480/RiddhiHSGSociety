package com.riddhiHousingSociety.utilities;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.riddhiHousingSociety.testCases.BaseClass;

public class Reporting extends BaseClass implements ITestListener{

	private ExtentReports extentReports;
	private ExtentSparkReporter sparkReporter;
	private ExtentTest logger;
	private long timeTaken;

	@Override
	public void onStart(ITestContext iTestContext) {

		DateTimeFormatter formatedDateTime = DateTimeFormatter.ofPattern("ddMMYY_HHmmss");
		String timeStampString = LocalDateTime.now().format(formatedDateTime);

		String reportName = "./test-output/extent-report/Extent-Report_"+ timeStampString + ".html";
		
		sparkReporter = new ExtentSparkReporter(reportName);
		
		
		try {
			sparkReporter.loadXMLConfig("./src/test/resources/extent-reports-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Parallel Test Automation");
		sparkReporter.config().setReportName("Riddhi Housing Society");
		sparkReporter.config().thumbnailForBase64(true);
		*/
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("HostName", "localhost");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("user", "Gangad");

	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		
		timeTaken = iTestResult.getEndMillis() - iTestResult.getStartMillis();
		double totalTime = Math.round(timeTaken)/1000.0;
		
		logger = extentReports.createTest(iTestResult.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREEN));
		logger.log(Status.PASS, "Total time taken: "+ totalTime);
		log.info(iTestResult.getMethod().getMethodName()+" Testcase passed");

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) 
	{
		
		timeTaken = iTestResult.getEndMillis() - iTestResult.getStartMillis();
		double totalTime = Math.round(timeTaken)/1000.0;
		
		logger = extentReports.createTest(iTestResult.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.RED));
				
		DateTimeFormatter formatedDateTime = DateTimeFormatter.ofPattern("ddMMYY_HHmmss");
		String timeStampString = LocalDateTime.now().format(formatedDateTime);
		
		/*
		//ImageFile implementation
		String screenShotPath = "./Screenshots/" + iTestResult.getName() + timeStampString + ".png";
		captureScreenshot(iTestResult.getName());
		logger.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, iTestResult.getName()).build());
		log.info(iTestResult.getMethod().getMethodName()+" Testcase failed");
		*/

		//Bas64 screenshot implementation
		String base64Screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		
		logger.fail(iTestResult.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, iTestResult.getName()).build());		
		logger.log(Status.FAIL, "Total time taken: "+ totalTime);
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {

		logger = extentReports.createTest(iTestResult.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.ORANGE));

	}

	@Override
	public void onFinish(ITestContext iTestContext) {

		extentReports.flush();

	}
	
}
