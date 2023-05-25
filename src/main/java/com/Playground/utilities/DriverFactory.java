package com.Playground.utilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static DriverFactory instance = null;
	WebDriver driver;
	ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	ThreadLocal<WiniumDriver> windowDriver = new ThreadLocal<WiniumDriver>();
	public static final String LOCALDIR = System.getProperty("user.dir");

	private DriverFactory() {

	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public final void setWebDriver(String Remote, String Browser) throws Exception {

//		DesiredCapabilities caps = null;
		HashMap<String, Integer> content_Settings = new HashMap<String, Integer>();
		HashMap<String, Object> profile1 = new HashMap<String, Object>();
		HashMap<String, Object> prefs1 = new HashMap<String, Object>();

		switch (Remote.toLowerCase()) {

		case "false":
			switch (Browser.toLowerCase()) {

			case "chrome":

				ChromeOptions chOptions = new ChromeOptions();
				Map<String, Object> chromePrefs = new HashMap<String, Object>();
				Map<String, Object> contentSettings = new HashMap<String, Object>();
				Map<String, Object> prefs = new HashMap<String, Object>();
				Map<String, Object> profile = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
				prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
				prefs.put("profile.default_content_setting_values.geolocation", 1);
				prefs.put("profile.default_content_setting_values.notifications", 1);
// chOptions.setExperimentalOption("prefs", prefs);
				chromePrefs.put("credentials_enable_service", false);
				chOptions.setExperimentalOption("prefs", chromePrefs);
				chOptions.addArguments("--no-sandbox");
				chOptions.addArguments("--remote-allow-origins=*");
// chOptions.addArguments("--headless"); // !!!should be enabled for Jenkins
				chOptions.addArguments("--use-fake-ui-for-media-stream");
				chOptions.addArguments("--allow-file-access");
				chOptions.addArguments("--use-fake-device-for-media-stream");
				chOptions.addArguments("--use-file-for-fake-video-capture=" + LOCALDIR
						+ "\\src\\test\\resources\\FaceDetection.mjpeg");
				chOptions.addArguments("--disable-dev-shm-usage"); // !!!should be enabled for Jenkins
				chOptions.addArguments("--window-size=1920x1080"); // !!!should be enabled for Jenkins
				chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");
				chOptions.setCapability(ChromeOptions.CAPABILITY, chOptions);
				chOptions.setCapability("applicationCacheEnabled", false);
				System.setProperty("webdriver.chrome.silentOutput", "true");
				WebDriverManager.chromedriver().setup();
				webDriver.set(new ChromeDriver(chOptions));
				getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				getWebDriver().manage().window().maximize();
				break;


			}

			break;
		}
	}

	public WebDriver getWebDriver() {
		return webDriver.get();
	}

}
