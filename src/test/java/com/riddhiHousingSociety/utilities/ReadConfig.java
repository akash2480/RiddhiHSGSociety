package com.riddhiHousingSociety.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() 
	{
		FileReader fr;
		try {
			fr = new FileReader("./Configurations/Configuration.properties");
			prop = new Properties();
			prop.load(fr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBrowserName() {
		String browser = prop.getProperty("browser");
		return browser;
	}
	
	public String getPropertyValue(String url) {
		String browser = prop.getProperty(url);
		return browser;
	}
	
	public String getApplicationUrl() {
		String url = prop.getProperty("url");
		return url;
	}

	public String getUserName() {
		String userName = prop.getProperty("username");
		return userName;
	}

	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
	}

}
