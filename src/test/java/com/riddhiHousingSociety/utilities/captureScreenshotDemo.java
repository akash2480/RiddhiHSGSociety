package com.riddhiHousingSociety.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class captureScreenshotDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https:\\www.google.com");
		
		
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("./Screenshots/image.png");
		
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Screenshot taken");
		
		driver.quit();
	}

}
