package com.riddhiHousingSociety.testCases;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ExistingChromeSession {
	
		public static void main(String[] args) {
		
		
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
		
		options.setExperimentalOption("debuggerAddress", "localhost:9222");
		options.setExperimentalOption("networkConnectionEnabled", true);
		
		/*
		 * ChromeDriver driver = new ChromeDriver(); 
		 * Capabilities cap = driver.getCapabilities();
		 */
		
		WebDriver driver = new ChromeDriver(options); 
		driver.get("https:\\www.google.com");
		
		
		/*
		 * Map<String, Object> myCap = cap.asMap(); 
		 * System.out.println(myCap);
		 */
	}

}
