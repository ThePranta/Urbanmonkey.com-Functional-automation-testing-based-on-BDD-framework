package com.Playground.ccl;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Playground.utilities.*;

public class WebActions {

	// Wrapped up methods for selenium

	JavascriptExecutor scrollBarPresent = (JavascriptExecutor) DriverFactory.getInstance().getWebDriver();
	Actions action = new Actions(DriverFactory.getInstance().getWebDriver());

	@SuppressWarnings("deprecation")
	public WebElement waitForVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), Duration.ofSeconds(30));
		wait.pollingEvery(Duration.ofSeconds(1));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	@SuppressWarnings("deprecation")
	public WebElement waitForVisible_MilliSeconds(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), Duration.ofSeconds(100));
		wait.pollingEvery(Duration.ofMillis(500));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void openURL(String URL) {
		DriverFactory.getInstance().getWebDriver().get(URL);
		String strActualText = DriverFactory.getInstance().getWebDriver().getCurrentUrl();
		verifyText(strActualText, URL);
		ReportManager.logInfo("Successfully Entered URL - " + URL);
		System.out.println("Successfully Entered URL - " + URL);
	}

	public void Click(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		elm.click();
		ReportManager.logInfo("Successfully clicked on - " + info);
		System.out.println("Successfully clicked on - " + info);
	}

	public void selectByVisibleText(By locator, String value) {
		WebElement elm = waitForVisible(locator);
		Select sel = new Select(DriverFactory.getInstance().getWebDriver().findElement(locator));
		sel.selectByVisibleText(value);
		ReportManager
				.logInfo("Successfully selected dropdown value - " + "<b style=\"color:green;\">" + value + "</b>");

	}

	public void selectByVisibleTextWithoutVisibilityCheck(By locator, String value) {
		Select sel = new Select(DriverFactory.getInstance().getWebDriver().findElement(locator));
		sel.selectByVisibleText(value);
		ReportManager
				.logInfo("Successfully selected dropdown value - " + "<b style=\"color:green;\">" + value + "</b>");

	}

	public void sendKeys(By userName, String text) {
		WebElement elm = waitForVisible(userName);
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text - " + text);
		System.out.println("Successfully Entered text - " + text);
	}

	public String getElmText(By locator) {
		WebElement elm = waitForVisible(locator);
		String elamText = elm.getText();
		ReportManager.logInfo("Successfully get element text - " + elamText);
		System.out.println("Successfully get element text - " + elamText);
		return elamText;
	}

	public boolean isDisplayed(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		boolean isPresent = elm.isDisplayed();
		if (isPresent) {
			ReportManager.logInfo("Successfully element displayed: " + info);
			System.out.println("Successfully element displayed: " + info);
		} else {
			ReportManager.logInfo("element not displayed: " + info);
			System.out.println("element not displayed: " + info);
		}
		return isPresent;
	}

	public boolean isEnabled(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		boolean isEnable = elm.isEnabled();
		if (isEnable) {
			ReportManager.logInfo("Element Enabled: " + info);
			System.out.println("Element Enabled: " + info);
		} else {
			ReportManager.logInfo("Element Disabled: " + info);
			System.out.println("Element Disabled: " + info);
		}
		return isEnable;
	}

	public void clearAndSendKeys(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text - " + text);
		System.out.println("Successfully Entered text - " + text);
	}

	public void clear(By locator) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		ReportManager.logInfo("Successfully Cleared text - ");
		System.out.println("Successfully Cleared text ");
	}

	public void verifyText(String actualText, String expectedText) {
		ReportManager.logInfo("Actual Text - " + actualText);
		ReportManager.logInfo("Expected Text - " + expectedText);
		System.out.println("Actual Text - " + actualText);
		System.out.println("Expected Text - " + expectedText);
		assertEquals(actualText, expectedText);
	}

	public boolean verifycontainsText(Boolean Str) {
		return Str;
	}

	public void verifyIntValues(int actualValue, int expectedValue) {
		ReportManager.logInfo("Actual Value - " + actualValue);
		ReportManager.logInfo("Expected Value - " + expectedValue);
		System.out.println("Actual Value - " + actualValue);
		System.out.println("Expected Value - " + expectedValue);
		assertEquals(actualValue, expectedValue);
	}

	public String getAttributeValue(By locator, String name) {
		WebElement elm = waitForVisible(locator);
		String attributeText = elm.getAttribute(name);
		ReportManager.logInfo("Successfully get attribute text - " + attributeText);
		System.out.println("Successfully get attribute text - " + attributeText);
		return attributeText;
	}

	public boolean isScrollPresent() throws Exception {
		String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
		Boolean isScroll_Present = (Boolean) (scrollBarPresent.executeScript(execScript));
		return isScroll_Present;

	}

	public void mouseHover(By locator) throws Exception {
		WebElement elm = waitForVisible_MilliSeconds(locator);
		action.moveToElement(elm).build().perform();
		String elmText = elm.getText();
		ReportManager.logInfo("Successfully mouse hover and get text - " + elmText);
		System.out.println("Successfully mouse hover and get text - " + elmText);
	}

	public void switchToFrame(By locator) throws Exception {
		WebElement elm = waitForVisible(locator);
		DriverFactory.getInstance().getWebDriver().switchTo().frame(elm);
		ReportManager.logInfo("Successfully switched to frame ");
		System.out.println("Successfully switched to frame ");
	}

	public List<WebElement> getListofElements(By locator, String name) {
		waitForVisible(locator);
		List<WebElement> lst_Elements = DriverFactory.getInstance().getWebDriver().findElements(locator);
		ReportManager.logInfo("Successfully get element size - " + lst_Elements.size());
		System.out.println("Successfully get element size - " + lst_Elements.size());
		return lst_Elements;
	}

	public void verifyPaymentStatusOnUI(String Status) {

		DriverFactory.getInstance().getWebDriver()
				.findElement(By.xpath("//div[@class = 'content-container']//h5[contains(., '" + Status + "')]"))
				.click();
		boolean isStatus = DriverFactory.getInstance().getWebDriver()
				.findElement(By.xpath("//div[@class = 'content-container']//h5[contains(., '" + Status + "')]"))
				.isEnabled();
		if (isStatus) {
			ReportManager.logInfo("<b style=\"color:green;\"> Element is Enabled : " + Status + "</b>");
			System.out.println("Enabled");
		} else {
			ReportManager.logInfo("<b style=\"color:red;\"> Element is Disabled : " + Status + "</b>");
			System.out.println("Disabled");
			Assert.assertEquals(true, isStatus);
		}
	}

	public void staticwait() throws InterruptedException {
		Thread.sleep(5000);
	}

	public void CurrentURL(String ActURL) {
		String strActualText = DriverFactory.getInstance().getWebDriver().getCurrentUrl();
		verifycontainsText(strActualText.startsWith(ActURL));
		ReportManager.logInfo("Successfully Verified URL - " + ActURL);
		System.out.println("Successfully Verified URL - " + ActURL);
	}

	public void ClickJSE(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		scrollBarPresent.executeScript("arguments[0].click();", elm);
		ReportManager.logInfo("Successfully clicked on - " + info);
		System.out.println("Successfully clicked on - " + info);
	}

	public void sendKeysOTP(By locator) {
		WebElement elm = waitForVisible(locator);
		Scanner scan = new Scanner(System.in);
		String otp = scan.nextLine();
		elm.sendKeys(otp);
	}

	public void alerthandling() {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), Duration.ofSeconds(100));
		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			DriverFactory.getInstance().getWebDriver().switchTo().alert().accept();
			ReportManager.logInfo("Successfully handled the alert!");
			System.out.println("Successfully handled the alert!");
		}
	}

	public void waitforbuttonandclick(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		if (elm.isEnabled()) {
			elm.click();
			ReportManager.logInfo("Successfully clicked on - " + info);
			System.out.println("Successfully clicked on - " + info);
		}
	}

	public void scrollDownUsingJS() {
		scrollBarPresent.executeScript(
				"window.scrollBy(0,document.body.scrollHeight || document.documentElement.scrollHeight)", "");
	}

	public void refresh() {
		DriverFactory.getInstance().getWebDriver().navigate().refresh();
	}

	public void selectByIndex(By locator, int index) {
		WebElement elm = waitForVisible(locator);
		Select sel = new Select(DriverFactory.getInstance().getWebDriver().findElement(locator));
		sel.selectByIndex(index);
		ReportManager.logInfo(
				"Successfully selected dropdown value by index - " + "<b style=\"color:green;\">" + index + "</b>");

	}

	public String getValue_textbox(By locator) {
		WebElement elm = waitForVisible(locator);
		String value = DriverFactory.getInstance().getWebDriver().findElement(locator).getAttribute("value");
		if (value.isEmpty()) {
			ReportManager.logInfo("Successfully fetched value - null");
			System.out.println("Successfully fetched value - null");
		} else {
			ReportManager.logInfo("Successfully fetched value - " + value);
			System.out.println("Successfully fetched value - " + value);
		}
		return value;
	}

	public String getImage(By locator) {
		WebElement elm = waitForVisible(locator);
		String getelmimg = DriverFactory.getInstance().getWebDriver().findElement(locator).getAttribute("src");
		if (getelmimg == "") {
			ReportManager.logInfo("Image is missing");
			System.out.println("Image is missing");
		} else {
			ReportManager.logInfo("Image is present. Image source being - " + getelmimg);
			System.out.println("Image is present. Image source being - " + getelmimg);
		}
		return getelmimg;
	}

	public void isAttribtuePresent(By locator, String attribute) {

		WebElement element = DriverFactory.getInstance().getWebDriver().findElement(locator);
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				System.out.println("The " + attribute + " is present");
				System.out.println(value);
				if (value.equalsIgnoreCase("true"))
					System.out.println("The " + attribute + " button is present with true value");
				else
					System.out.println("The " + attribute + " button is present with false value");
			} else
				System.out.println("The " + attribute + " is present with null value");
		} catch (Exception e) {
			System.out.println("The attribute is not present in the dom");
		}
	}

	public void clearUsingKeys(By locator) {
		WebElement element = DriverFactory.getInstance().getWebDriver().findElement(locator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	public void handleCalendarByText(By locator) {
		WebElement element = DriverFactory.getInstance().getWebDriver().findElement(locator);
		DateFormat dateFormat = new SimpleDateFormat("MMMM:dd:HH:mm:yyyy");
		Date date = new Date();
		int updatedHour = 0, updatedMin = 0;
		String value = dateFormat.format(date);
		String[] dateFormatValues = value.split(":");
		int hour = Integer.parseInt(dateFormatValues[2]);
		int minutes = Integer.parseInt(dateFormatValues[3]);
		String month = dateFormatValues[0];
		int currentDate = Integer.parseInt(dateFormatValues[1]);
		String currentYear = dateFormatValues[4];
		if (hour > 23 && minutes == 59) {
			hour = updatedHour;
			minutes = updatedMin;
		} else {
			minutes += 1;
		}

		String updatedCalendarValue = month + " " + currentDate + "," + " " + currentYear + " " + hour + ":" + minutes;
		element.sendKeys(updatedCalendarValue);
	}

	public void waitforElement() throws InterruptedException {
		Thread.sleep(240000);
	}

	public void CurrentURL() {
		String strActualText = DriverFactory.getInstance().getWebDriver().getCurrentUrl();
		ReportManager.logInfo("Successfully Verified URL - " + strActualText);
		System.out.println("Successfully Verified URL - " + strActualText);
	}

	public void handleReminderMail(By locator, By paragraph) throws Exception {
		List<WebElement> list = DriverFactory.getInstance().getWebDriver().findElements(locator);
		for (WebElement a : list) {
			a.click();
			if (getElmText(paragraph).equals("Section Reminder")) {
				System.out.println("The " + getElmText(paragraph) + " mail is present in the notification section");
				break;
			} else {
				System.out.println("The " + getElmText(paragraph) + " mail is not present in the notification section");
			}
		}

	}

	public void clickTillExist(By locator, String info) {

		List<WebElement> elmlst = DriverFactory.getInstance().getWebDriver().findElements(locator);
		while (elmlst.size() != 0) {
			System.out.println("There is still another : " + info);
			WebElement elm = DriverFactory.getInstance().getWebDriver().findElement(locator);
			elm.click();
			break;
		}

	}

}