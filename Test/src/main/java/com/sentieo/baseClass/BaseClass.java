package com.sentieo.baseClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public WebDriver driver;
	public String browser = "";
	public String appUrl = "";

	@BeforeClass(alwaysRun = true)
	public void setUpBeforeClass(ITestContext context) throws Exception {
		initExecutionContext(context);
		initWebDriver();
		setUp();
	}

	public void initExecutionContext(ITestContext context) throws Exception {
		browser = context.getCurrentXmlTest().getParameter("browser");
		appUrl = context.getCurrentXmlTest().getParameter("appUrl");
	}

	public void initWebDriver() throws Exception {
		driver = new WebDriverFactory(browser).getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

	}

	public void setUp() throws Exception {

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}