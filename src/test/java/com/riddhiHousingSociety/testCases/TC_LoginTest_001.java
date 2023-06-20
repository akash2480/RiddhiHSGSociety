package com.riddhiHousingSociety.testCases;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.riddhiHousingSociety.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test(enabled = true)
	public void loginTestWithValidCredentials() 
	{		
		LoginPage lpg = new LoginPage(driver);
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		assertEquals(driver.getTitle(), "Enquiries", "Homepage Title does not match");
		log.info("Verified Admin Landing page after successful login");
		assertEquals(lpg.adminProfileLink.isDisplayed(), true,"Admin Profile link is not visible");
		log.info("Verified Admin Profile link is visible");	
	}
	
	@Test(enabled = true)
	public void logoutFunction()
	{
		LoginPage lpg = new LoginPage(driver);

		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		assertEquals(driver.getTitle(), "Enquiries","Homepage Title does not match");
		log.info("Verified Admin Landing page after successful login");
		
		lpg.clickAdminProfileLink();
		log.info("Clicked Admin Profile linka at top right corner of the display");
		lpg.clickLogoutLink();
		log.info("Clicked logout link from Admin Profile link ");
		assertEquals(lpg.getLogoutSuccessMessage(), "Success!You are logged out!");
		log.info("Succesful logout");
		
	}

	@Test(enabled = true)
	public void loginTestWithInvalidCredentials() 
	{
		LoginPage lpg = new LoginPage(driver);
		
		lpg.setUserName("RandomString");
		log.info("Entering Username");
		lpg.setPassword("101");
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		log.info("Verifying Login upon entering invalid username and password ");
		assertEquals(lpg.getErrorTextMessage(), "Error!Invalid Username or Password");
		log.info("Verified Login Error Message with invalid username and Password ");

	}

	@Test(enabled = true)
	public void loginTestWithBlankUsername() 
	{
		LoginPage lpg = new LoginPage(driver);

		lpg.setUserName("");
		log.info("Entering Username");
		lpg.setPassword(password);
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		log.info("Verifying Login with Blank username and valid password ");
		assertEquals(lpg.getErrorTextMessage(), "Error!Username field should not left blank");
		log.info("Verified Login Error Message with Blank username and valid Password ");
		

	}

	@Test(enabled = true)
	public void loginTestWithBlankPassword() 
	{
		LoginPage lpg = new LoginPage(driver);
		
		lpg.setUserName(userName);
		log.info("Entering Username");
		lpg.setPassword("");
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		log.info("Verifying Login with valid username and blank password ");
		assertEquals(lpg.getErrorTextMessage(), "Error!Password field should not left blank");
		log.info("Verified Login Error Message with valid username and invalid Password ");

	}

	@Test(enabled = true)
	public void loginTestWithBlankUsernameAndPassword() {
		LoginPage lpg = new LoginPage(driver);

		lpg.setUserName("");
		log.info("Entering Username");
		lpg.setPassword("");
		log.info("Entering Password");
		lpg.clickSubitButton();
		log.info("Clicking Submit Button");
		log.info("Verifying Login with blank username and blank password ");
		assertEquals(lpg.getErrorTextMessage(), "Error!Username and Password field should not left blank");
		log.info("Verified Login Error Message with blank username and blank password ");

	}

}
