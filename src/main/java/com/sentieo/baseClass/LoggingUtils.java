package com.sentieo.baseClass;

import com.google.common.io.BaseEncoding;
import com.relevantcodes.extentreports.LogStatus;
import com.sentieo.report.ExtentTestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import java.io.File;
import java.util.HashMap;

public class LoggingUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger("default_logger");
	private static final String INFO = "<span style=\"font-weight: bold;\"> Step details : </span>";
	private static final String ERROR = "<span style=\"font-weight: bold;\"><font color=\"red\"> Error details : </span>";

	private LoggingUtils() {
		// statics only
	}

	// level 1
	public static void trace(Logger LOGGER, String msg) {
		ExtentTestManager.getTest().log(LogStatus.FATAL, msg.toString().toLowerCase());
		LOGGER.trace(msg);
		Reporter.log(msg);
	}

	// level 2
	public static void info(String msg) {
		ExtentTestManager.getTest().log(LogStatus.INFO, msg.toString().toLowerCase());
		LOGGER.debug(msg);
		Reporter.log(msg);
	}

	public static void info(Logger LOGGER, HashMap<String, String> msg) {
		String message = INFO + msg.toString().toLowerCase();
		ExtentTestManager.getTest().log(LogStatus.INFO, message);
		LOGGER.debug(message);
		Reporter.log(message);
	}

	public static void error(Logger LOGGER, HashMap<String, String> msg) {
		String message = ERROR + msg.toString().toLowerCase();
		ExtentTestManager.getTest().log(LogStatus.INFO, message);
		LOGGER.debug(message);
		Reporter.log(message);
	}

	// level 3
	public static void debug(String msg) {
		ExtentTestManager.getTest().log(LogStatus.SKIP, msg.toString().toLowerCase());
		LOGGER.info(msg);
		Reporter.log(msg);
	}

	// level 4
	public static void warn(String msg) {
		ExtentTestManager.getTest().log(LogStatus.WARNING, msg.toString().toLowerCase());
		LOGGER.warn(msg);
		Reporter.log(msg);
	}

	// level 5
	public static void error(Logger logger, String msg) {
		ExtentTestManager.getTest().log(LogStatus.ERROR, msg.toString().toLowerCase());
		LOGGER.error(msg);
		Reporter.log(msg);
	}

	public static void log(File file, String message) {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
	}

	public static void log(byte[] bytes, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(bytes), message);
	}

	public static void logBase64(String base64, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
	}

	public static void info(Logger logger, String msg) {
		ExtentTestManager.getTest().log(LogStatus.INFO, msg.toString().toLowerCase());
		logger.info(msg);
		Reporter.log(msg);
	}

	public static void warn(Logger logger, String msg) {
		ExtentTestManager.getTest().log(LogStatus.WARNING, msg.toString().toLowerCase());
		logger.warn(msg);
		Reporter.log(msg);
	}

	public static void log(Logger logger, File file, String message) {
		logger.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
	}

	public static void log(Logger logger, byte[] bytes, String message) {
		logger.info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(bytes), message);
	}

	public static void logBase64(Logger logger, String base64, String message) {
		logger.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
	}
}