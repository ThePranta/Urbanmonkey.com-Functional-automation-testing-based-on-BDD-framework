package com.Playground.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.Playground.utilities.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {
	
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static Map<Long, ExtentTest> testMap = new HashMap<>();
	public static Map<String, ExtentTest> extentMap = new HashMap<>();

	public static void startReport() {

		if (htmlReporter == null) {
			extent = new ExtentReports();
			String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
			String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
//                            htmlReporterWeb = new ExtentSparkReporter(System.getProperty("user.dir") + "/Execution_Reports/Web_Reports/Web.html"+
//                                                   dateStamp + "/" + "QOBOX-" + timeStamp + ".html");

//                           
			htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")
					+ "/Execution_Reports/Web_Reports/Extent_Web.html");
			extent.attachReporter(htmlReporter);

			// spark.config().setEncoding("utf-8");
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setDocumentTitle("Demo");
			htmlReporter.config().setReportName("Demo");

			extent.setSystemInfo("Organization", "DEMO");
			extent.setSystemInfo("Employee",
					"<b> Test User1</b>" + " " + "" + " " + "");
			extent.setSystemInfo("Domain", "Engineering (IT - Software)");
			extent.setSystemInfo("Skill", "Test Automation Engineer");
		}
	}

	public static void startTest(String testName, String categories) {

	
		extent= new ExtentReports();

		ExtentTest test = extent.createTest(testName);
		test.assignCategory(categories);
//		test.assignCategory("Candidate");
		testMap.put(Thread.currentThread().getId(), test);
		extentMap.put(testName, test);

	}

	public static void logPass(String message) {
		getCurrentTest().log(Status.PASS, message);

	}

	/**
	 * =============================================================================
	 * Method: logScreenShot | Author: Amitabha Bagchi | Date:10th March 2021 |
	 * Description: This method log take screenshot | Parameters: message | Return:
	 * none
	 * =============================================================================
	 * 
	 * @param driver
	 * @throws IOException
	 */
	public static void logScreenshot() throws IOException {
		// getCurrentTest().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver()));
		Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
				ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver())).build();
		getCurrentTest().fail("", mediaModel);

	}

	/**
	 * =============================================================================
	 * Method: logScreenShot | Author: Amitabha Bagchi | Date:10th March 2021 |
	 * Description: This method log take screenshot | Parameters: message | Return:
	 * none
	 * =============================================================================
	 * 
	 * @param driver
	 * @throws IOException
	 */
	public static void logScreenshotInfo() throws IOException {
		Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
				ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver())).build();
		getCurrentTest().info("", mediaModel);

	}
	
	/**
	 * =============================================================================
	 * Method: logScreenShot | Author: Amitabha Bagchi | Date:10th March 2021 |
	 * Description: This method log take screenshot | Parameters: message | Return:
	 * none
	 * =============================================================================
	 * 
	 * @param driver
	 * @throws IOException
	 */
//	public static void logScreenshotInfo1() throws IOException {
//		Media mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
//				ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWindowDriver())).build();
//		getCurrentTest().info("", mediaModel);
//
//	}

	public static void logFail(String message) {
		getCurrentTest().log(Status.FAIL, message);

	}

	public static void logInfo(String message) {
		getCurrentTest().log(Status.INFO, message);

	}

	public static void endCurrentTest() {
		getCurrentTest().getExtent().flush();

		testMap.remove(Thread.currentThread().getId());
	}

	public static ExtentTest getCurrentTest() {
		return testMap.get(Thread.currentThread().getId());

	}

	public static void endReport() {
		extent.flush();
		System.out.println("report is creating");
	}

	
}
