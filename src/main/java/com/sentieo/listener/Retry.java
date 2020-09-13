package com.sentieo.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.sentieo.base.interfaces.BaseTestInterface;
import com.sentieo.report.ExtentManager;
import com.sentieo.report.ExtentTestManager;

public class Retry implements IRetryAnalyzer {

	public int count = 0;
	public static int maxTry = 1;
	public static int temp = 0;
	public static boolean retry = true;
	public static int tempVar = 0;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (retry) {
			System.out.println("Retry # " + count);
			tempVar = count;
			if (!iTestResult.isSuccess()) { // Check if test not succeed
				if (count < maxTry) { // Check if maxtry count is reached
					iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
					count++; // Increase the maxTry count by 1
					tempVar = count;
					temp++;
					return true; // Tells TestNG to re-run the test
				}
			} else {
				iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
			}
		} else {
			retry = true;
		}

		temp = 0;
		return false;
	}

	public void extendReportsFailOperations(ITestResult iTestResult) {
		WebDriver webDriver = null;
		Object testClass = iTestResult.getInstance();

		if (testClass instanceof BaseTestInterface) {
			BaseTestInterface bti = ((BaseTestInterface) testClass);
			webDriver = bti.getDriver();
		}

		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));

		ExtentManager.getReporter().flush();
		String ACTUAL_DECO = "<br><span style=\"font-weight: bold;\"><font color=\"red\"> ------------------------------------------------------------------------------ <br/>Oops the test failed... <br/>Hang on! To avoid chances of false failure, we are retrying this test again <br/>------------------------------------------------------------------------------ </span>";
		ExtentTestManager.getTest().log(LogStatus.INFO, ACTUAL_DECO);

	}

}
