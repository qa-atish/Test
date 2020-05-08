package com.sentieo.baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverInstance {

	WebDriver driver = null;

	public WebDriver getChromeDriver() throws Exception {
		try {
			ChromeOptions options = new ChromeOptions();
			String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				WebDriverManager.chromedriver().setup();
				options.addArguments("--incognito");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-popup-blocking");
				options.setExperimentalOption("useAutomationExtension", false);
				System.setProperty("webdriver.chrome.silentOutput", "true");

			} else if (os.toLowerCase().contains("mac")) {
				WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.silentOutput", "true");
			} else {
				WebDriverManager.chromedriver().setup();
//				System.setProperty("webdriver.chrome.driver",
//						System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//								+ File.separator + "resources" + File.separator + "chromedriver_linux");
				options.setHeadless(true);
				options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				System.setProperty("webdriver.chrome.silentOutput", "true");
			}

			driver = new ChromeDriver(options);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return driver;
	}

}
