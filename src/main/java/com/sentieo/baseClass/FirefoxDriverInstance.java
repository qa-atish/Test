package com.sentieo.baseClass;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverInstance {
	
	WebDriver driver = null;
	
	public WebDriver getFirefoxDriver() {
		try {
			System.setProperty("webdriver.gecko.driver", 
					System.getProperty("user.dir") + 
					File.separator + "src" + 
					File.separator + "test" + 
					File.separator + "resources" +
					File.separator + "geckodriver");
			
			driver=new FirefoxDriver();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

}
