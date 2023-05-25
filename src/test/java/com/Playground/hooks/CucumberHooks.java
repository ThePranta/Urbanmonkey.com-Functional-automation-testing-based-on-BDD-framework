package com.Playground.hooks;

import java.util.ArrayList;

import com.Playground.utilities.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
//import static org.junit.Assume.*;

public class CucumberHooks {


	public static ArrayList<String> passedTests = new ArrayList<String>();
	public static ArrayList<String> failedTests = new ArrayList<String>();
	public static ArrayList<String> totalTestCases = new ArrayList<String>();

	private static String str_Execution_TYPE = "Web_UI";
// public String str_BrowserType = ConfigReader.getValue("Browser");
// public String str_RunType = ConfigReader.getValue("Remote");
// public String str_RunType = System.getProperty("Remote");
	String str_BrowserType = System.getProperty("Browser");
	String str_RunType = System.getProperty("Remote");

// public String str_BrowserType = System.getProperty("Browser");
// public String str_BrowserType;
	DriverFactory driverFactory = DriverFactory.getInstance();
// public static String strBrowser = ConfigReader.getValue("Browser");
//public static String strBrowser = "Firefox";
	public static String featureFileName;

//	@Before(value = "@Noaudio", order = 0)
//	public void skipscenario(Scenario scenario) {
//		System.out.println("Skipped scenario is:" + scenario.getName());
//// Assume.assumetrue(false);
//	}

	@Before
	public void before(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++before hooks++++++++++++++++++");

		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
// System.out.println(scenario.getSourceTagNames().toString());
// String myCurrentTag = System.getProperty("cucumber.filter.tags");
// System.out.println(myCurrentTag
			ReportManager.startTest(scenario.getName(), "WEB");
			System.out.println("Execution started @ " + str_BrowserType + " browser & for type : Web UI");
			try {

				driverFactory.setWebDriver(str_RunType.trim(), str_BrowserType.trim());
			} catch (Exception e) {
// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new Exception(
					"[-] Please set exection type[Web_UI or Mobile_UI or API] value in cucumberhooks class line number 21");
		}

		/**
		 * ReportManager.createAPI_Node(scenario.getName()); String str_Features =
		 * scenario.getId().split(";")[0]; String[] str_arryFeature =
		 * str_Features.split("features/"); System.out.println(str_arryFeature);
		 * String[] str_Feature = str_arryFeature[1].split(".feature"); featureFileName
		 * = str_Feature[0]; System.out.println(str_Feature[0]);
		 **/
	}

	@After
	public void after(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++after hooks+++++++++++++++++++");

		/*
		 * if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
		 * System.out.println("+++++++++++++++++++Web_UI+++++++++++++++++++");
		 * totalTestCases.add(scenario.getName()); if (scenario.isFailed()) {
		 * failedTests.add(scenario.getName()); ReportManager.logFail(scenario.getName()
		 * + " Test case Fail"); System.out.println("Test Fail: " + scenario.getName());
		 * } else {
		 * System.out.println("+++++++++++++++++++++onTestSuccess++++++++++++++++++++");
		 * System.out.println("Test Success: " + scenario.getName());
		 * ReportManager.logPass(scenario.getName() + " Test case passed");
		 * ReportManager.endCurrentTest();
		 * 
		 * 
		 * 
		 * driverFactory.getWebDriver().quit(); passedTests.add(scenario.getName()); }
		 * 
		 * 
		 * 
		 * }
		 */
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			totalTestCases.add(scenario.getName());
			if (totalTestCases.contains(scenario.getName())) {
				System.out.println(" TC from totalTestCases: " + scenario.getName());
				totalTestCases.remove(scenario.getName());
			}
			if (failedTests.contains(scenario.getName())) {
				System.out.println(" TC from failedTests: " + scenario.getName());
				failedTests.remove(scenario.getName());
			}
			if (passedTests.contains(scenario.getName())) {
				System.out.println(" TC from passedTests: " + scenario.getName());
				passedTests.remove(scenario.getName());
			}
			if (scenario.isFailed()) {
				failedTests.add(scenario.getName());
			} else {
				passedTests.add(scenario.getName());
			}
		} else {
			throw new Exception(
					"[-] Please set exection type[Web_UI or Mobile_UI or API] value in cucumberhooks class line number 21");
		}

	}

}

