package com.sentieo.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sentieo.listener.Retry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ExtentTestManager {  
	static int retryLog = 0;
	static HashSet<String> set = new HashSet<String>();
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {

    	if(Retry.temp==0) {
    		ExtentTest test = extent.startTest(testName, desc);
    		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
    		return test;
    	}
    	else {
    		ExtentManager.getReporter().flush();
    		ExtentTest test = extent.startTest(testName + "<p><font size=\"2\" color=\"red\">[ Retry attempt#" + Retry.temp + " ] </font></p>", desc);
    		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
    		return test;
    	}
        
    }
}
