package com.Playground.pages;

import org.openqa.selenium.By;

import com.Playground.utilities.*;

import junit.framework.Assert;

import com.Playground.ccl.*;

public class Demopage {

	WebActions actions = new WebActions();

	By popupClose = By.xpath("//i[@class='fa fa-times closeIcon']");
	By profileIcon = By.xpath("//a[@aria-label='accounts link']//*[name()='svg']");
	By username = By.xpath("//input[@id='CustomerEmail']");
	By password = By.xpath("//input[@id='CustomerPassword']");
	By signin_btn = By.xpath("//button[text()='Sign In']");
	By profileIcon2 = By.xpath("(//*[name()='svg'][@class='t4s-icon t4s-icon--account'])[1]");
	By dashboard = By.xpath("//a[@aria-label='accounts link'][text()='Dashboard']");
	By Name_validation = By.xpath("//div/table/tbody/tr[1]/td[2]");
	By LandingPage = By.xpath("(//div[@class='t4s-drawer__header'])[2]");
	By Logout = By.xpath("//a[@aria-label='accounts logout link']");

	public void openurl() {
		actions.openURL(ConfigReader.getValue("url"));
	}

	public void closePopup() {
		actions.Click(popupClose, "closepopup");
	}

	public void clickonProfileIcon() {

		actions.Click(profileIcon, "ProfileIcon");
	}

	public void hoverOnProfile() throws Exception {
		actions.mouseHover(profileIcon2);
	}

	public void enterUsername() {
		actions.sendKeys(username, ConfigReader.getValue("username"));
	}

	public void enterPassword() {
		actions.sendKeys(password, ConfigReader.getValue("password"));
		actions.Click(signin_btn, "signin");
	}

	public void dashBoardClick() {
		actions.Click(dashboard, "Dashboard");
	}

	public String NameValidation() {
		return actions.getElmText(Name_validation);
	}

	public void logout() {

		actions.Click(Logout, "Logout");
	}

	public String LandingPageVarification() {
		return actions.getElmText(LandingPage);
	}

}
////svg[@xpath='1'] profile icone action class

//////a[@xpath='2'] dashbord click

/// //td[@xpath='1'] get text

////a[@xpath='1'] logout click. with this code