package com.riddhiHousingSociety.utilities;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

public class Reporting extends BaseClass implements ITestListener {

	public ExtentReports extentReports;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest logger;

	public void onStart(ITestContext iTestContext) {

		DateTimeFormatter formatedDateTime = DateTimeFormatter.ofPattern("ddMMYY_HHmmss");
		String timeStampString = LocalDateTime.now().format(formatedDateTime);

		String reportName = "./test-output/test-report/Test-Report_"+ timeStampString + ".html";
		
		sparkReporter = new ExtentSparkReporter(reportName);
		try {
			sparkReporter.loadXMLConfig("./src/test/resources/extent-reports-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("HostName", "localhost");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("user", "Gangad");

	}

	public void onTestSuccess(ITestResult iTestResult) {

		logger = extentReports.createTest(iTestResult.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREEN));
		log.info(iTestResult.getMethod().getMethodName()+" Testcase passed");

	}

	public void onTestFailure(ITestResult iTestResult) {

		logger = extentReports.createTest(iTestResult.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.RED));

		DateTimeFormatter formatedDateTime = DateTimeFormatter.ofPattern("ddMMYY_HHmmss");
		String timeStampString = LocalDateTime.now().format(formatedDateTime);

		// iTestResult.getMethod().getMethodName(); points the exact method of the test
		// where the test failed
		String screenShotName = iTestResult.getMethod().getMethodName() + timeStampString + ".png";
		String screenShotPath = "./Screenshots/" + screenShotName;
		//System.out.println(screenShotPath);
		log.info(iTestResult.getMethod().getMethodName()+" Testcase failed");
		captureScreenshot(iTestResult.getName());

		logger.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, iTestResult.getName()).build());
		
	}

	public void onTestSkipped(ITestResult iTestResult) {

		logger = extentReports.createTest(iTestResult.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.ORANGE));

	}

	public void onFinish(ITestContext iTestContext) {

		extentReports.flush();

	}

}
