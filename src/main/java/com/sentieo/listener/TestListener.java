package com.sentieo.listener;

import com.relevantcodes.extentreports.LogStatus;
import com.sentieo.base.interfaces.BaseTestInterface;
import com.sentieo.baseClass.BaseClass;
import com.sentieo.report.ExtentManager;
import com.sentieo.report.ExtentTestManager;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseClass implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

//	private static String getMethodLabel(String text) {
//		return "<font size=\"2\">" + text + " (Test) <br/><br/>";
//	}

	private static String getMethodLabel(String text, String params) {
		return "<font size=\"2\">" + text + " (" + params + ") <br/><br/>";
		// return "<font size=\"2\">" + text + " (Test) <br/><br/>";
	}

	private static String getClassLabel(String text) {
		return "<font size=\"2\">" + text.substring(text.lastIndexOf(".") + 1, text.length()) + " (Class) <br/><br/>";
		// return "<font size=\"2\">" + text + " (Class) <br/><br/>";
	}

	// Before starting all tests, below method runs.
	@Override
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	// After ending all tests, below method runs.
	@Override
	public void onFinish(ITestContext iTestContext) {
		// Do tier down operations for extentreports reporting!
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
//		String methodDesc = getMethodLabel(iTestResult.getMethod().getMethodName());
//		String classDesc = getClassLabel(iTestResult.getInstanceName()).toString();
//		String description = iTestResult.getMethod().getMethodName().toString();
//
//		// Start operation for extentreports.
//		ExtentTestManager.startTest(methodDesc, "");
//		ExtentTestManager.getTest().setDescription(description);

		Object[] params = iTestResult.getParameters();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < params.length; i++) {
			if (!StringUtils.isEmpty(params[i].toString())) {
				sb.append(params[i].toString());
				if (i < params.length - 1) {
					sb.append(" , ");
				}
			}
		}
		String paramsList = sb.toString();
		String methodDesc = getMethodLabel(iTestResult.getMethod().getMethodName(), paramsList);
		String classDesc = getClassLabel(iTestResult.getTestClass().getName());

		String description = "<u>Test description</u> --> " + iTestResult.getMethod().getDescription();
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		// Start operation for extentreports.
		ExtentTestManager.startTest(classDesc + methodDesc, "");
		ExtentTestManager.getTest().setDescription(description);
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Retry.temp = 0;
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		WebDriver webDriver = null;
		Object testClass = iTestResult.getInstance();

		if (testClass instanceof BaseTestInterface) {
			BaseTestInterface bti = ((BaseTestInterface) testClass);
			webDriver = bti.getDriver();
		}

		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);// Extentreports log and screenshot
																					// operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
