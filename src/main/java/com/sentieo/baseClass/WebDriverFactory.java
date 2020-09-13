package com.sentieo.baseClass;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

	String browser = "";

	public WebDriverFactory(String browser) {
		this.browser = browser;
	}

	public WebDriver getDriver() throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			return new ChromeDriverInstance().getChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			return new FirefoxDriverInstance().getFirefoxDriver();
		} else
			return new ChromeDriverInstance().getChromeDriver();

	}

}
