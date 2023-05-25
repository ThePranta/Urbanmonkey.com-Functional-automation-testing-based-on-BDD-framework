package com.Playground.stepDefinitions;

import org.asynchttpclient.util.Assertions;

import com.Playground.pages.*;
import com.Playground.utilities.DriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class Demo {
	
	//DriverFactory DF = new DriverFactory();

	
	Demopage demoobj = new Demopage();

	@Given("User navigates to the UrbanMonkey.com landing page")
	public void user_navigates_to_the_urban_monkey_com_landing_page() {
		demoobj.openurl();
		
	}
	@When("User close the subscription popup")
	public void User_close_the_subscription_popup() {
	demoobj.closePopup();	
		
	}

	@When("User click on the profile icon")
	public void user_hover_on_the_profile_icon() throws Exception {
		demoobj.clickonProfileIcon();
	
	}
	
	

	@Then("User logins with the valida credentials")
	public void user_logins_with_the_valida_credentials() throws InterruptedException {
		demoobj.enterUsername();
		demoobj.enterPassword();
		Thread.sleep(9000);
	
	}

	
	
	@Then("User validates the ProfileHolder name from the Dashboard")
	public void user_validates_the_profile_holder_name_from_the_dashboard() throws Exception {
		
		Thread.sleep(9000);
	
		demoobj.hoverOnProfile();
		demoobj.dashBoardClick();
	
		   String expected = "Pranta Saha";
		    String actual = demoobj.NameValidation();
		    Assert.assertEquals(expected, actual);
		
	}

	@Then("User click on the Logout")
	public void user_click_on_the_logout() throws Exception {
		demoobj.hoverOnProfile();
		demoobj.logout();
		
	}

	@Then("User Validate the landing page again By checking Login text from slider")
	public void user_validate_the_landing_page_again_by_checking_login_text_from_slider() {
		demoobj.clickonProfileIcon();
		  String expected = "LOGIN";
		    String actual = demoobj.LandingPageVarification();
		    Assert.assertEquals(expected, actual);
		
		
	}

}
