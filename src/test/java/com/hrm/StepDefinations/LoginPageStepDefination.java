package com.hrm.StepDefinations;

import java.util.Properties;

import org.testng.Assert;

import com.hrm.keyword.Keyword;
import com.hrm.pages.LoginPage;
import com.hrm.util.ConfigReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDefination {
	

        static Keyword keyword = new Keyword();

    	private LoginPage loginPage = new LoginPage(Keyword.getDriver());
    	Properties prop;

		@Then("I should get the Page Title")
	        public void i_should_get_the_page_title() {
				String actualLoginPageTitle =  keyword.getTitleText();
				String expectedLoginPageTitle = "OrangeHRM";
				Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle,"Invalid Login Page");
		}
		
		@Given("I have entered a valid username {string} and password {string}")
		public void i_have_entered_a_valid_username_and_password(String uname ,String pass) {
//			loginPage.enterUserName(prop.getProperty("adminValidUserName"));
//			loginPage.enterPassword(prop.getProperty("adminValidPassword"));
			
			loginPage.enterUserName(uname);
			loginPage.enterPassword(pass);
			//loginPage.doLogin(uname, pass);
		}
		
		
		@When("I click on the login button")
		public void i_click_on_the_login_button() {
			loginPage.clickOnLogin();
		    
		}
		
		@Then("I should logged in successfully {string}")
		public void i_should_logged_in_successfully(String string) {
		    
		    String actualDashBoardText = loginPage.getDashboardText();
		    String expectedDashBoardText = "Dashboard";
		    Assert.assertEquals(actualDashBoardText, expectedDashBoardText,"Login Failed");
		}
		
		@Given("I have entered invalid  username {string} and  username {string}")
		public void i_have_entered_invalid_username_and_username(String uname, String pass) {
			loginPage.enterUserName(uname);
		    loginPage.enterPassword(pass);
		}
		
		@Then("I should see an error message indicating")
		public void i_should_see_an_error_message_indicating() {
		     String errorMsg = loginPage.getErrorMsgText();
		     System.err.println(errorMsg);
		     keyword.takeScreenShot();
		}
		
		@When("I click on the Forgot your password? link")
		public void i_click_on_the_forgot_your_password_link() {
			loginPage.clickOnForgotPasswordLink();
		}
		
		@Then("I should be restricted to the Reset Password page")
		public void navigateToResetPwdPage() {
			String actualCurrentURL = keyword.getCurrentUrlLink();
			String expectedCurrentURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
			Assert.assertEquals(actualCurrentURL, expectedCurrentURL,"Invalid Reset Password link");
			
		}
		
		@When("User clicks on the profile icon")
		public void userClickOnProfileIcon() {
			loginPage.clickOnlogoutUserDropdown();
		}
		 
		@And("User clicks on the Logout option")
		public void selectLogoutFromDropdown() throws InterruptedException {
			Thread.sleep(3000);
			loginPage.clickOnLogoutBtn();
		}
		  
		@Then("User should be redirected to the OrangeHRM login page")
		public void navigateToLoginPage() {
			String actualLoginPageTitle =  keyword.getTitleText();
			String expectedLoginPageTitle = "OrangeHRM";
			Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle,"Invalid Login Page");
		}

}
