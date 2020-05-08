package com.sentieo.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

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

}
