package com.Playground.NGListeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Playground.utilities.*;

public class WebEvent implements ITestListener {
	//private String str_BrowserType = System.getProperty("BROWSER");
	//private String str_BrowserType = ConfigReader.getValue("Browser");
	DriverFactory driverFactory = DriverFactory.getInstance();
	public static String strBrowser;

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++++onTestStart++++++++++++++++++++");
			
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		
	
		ReportManager.endCurrentTest();
		driverFactory.getWebDriver().quit();
		
		try {
			// Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
			//Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			// Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("+++++++++++++++++++++onTestFailure++++++++++++++++++++");
		ReportManager.logFail(iTestResult.getThrowable().toString());
		
			try {
				ReportManager.logScreenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ReportManager.endCurrentTest();
			driverFactory.getWebDriver().quit();
		
	

		try {
			//Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			// Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
System.out.println("skipped scenario");
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
System.out.println("On test finish+++++++++++++++");
	}

	@Override
	public void onStart(ITestContext arg0) {
System.out.println("onstart===================");
		//strBrowser = arg0.getCurrentXmlTest().getParameter("Browser");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
System.out.println("Pass percentage");
	}

}

