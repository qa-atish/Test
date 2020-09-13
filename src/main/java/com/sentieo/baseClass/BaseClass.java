package com.sentieo.baseClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.sentieo.base.interfaces.BaseTestInterface;

public class BaseClass implements BaseTestInterface {

	protected WebDriver driver = null;
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

	@Override
	public WebDriver getDriver() {
		return driver;
	}

	@Override
	public String getBaseClassName() {
		return BaseClass.class.getSimpleName();
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}