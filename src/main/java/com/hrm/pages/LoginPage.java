package com.hrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hrm.keyword.Keyword;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(css="input[name*='username']")
	WebElement userNameTXTBx;
	
	@FindBy(css="input[name*='password']")
	WebElement passwordTXTBx;
	
	@FindBy(css="button[type*='submit']")
	WebElement loginBtn;
	
	@FindBy(css="h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")
	WebElement dashboardTxt;
	
	@FindBy(css="p.oxd-text.oxd-text--p.oxd-alert-content-text")
	WebElement errorMessageTxt;
	
	@FindBy(css="p.oxd-text.oxd-text--p.orangehrm-login-forgot-header")
	WebElement forgotPasswordLink;
	
	//@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	@FindBy(css="p.oxd-userdropdown-name")
	WebElement EmpNameText;
	
	@FindBy(css="span.oxd-userdropdown-tab")
	WebElement logoutUserDropdown;
	
	@FindBy(xpath="//a[@href=\"/web/index.php/auth/logout\"]")
	WebElement logoutBtn;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(Keyword.driver, this);
	}
	
	public LoginPage() {
	  PageFactory.initElements(Keyword.driver, this);
    }

	public void enterUserName(String uname) {
		userNameTXTBx.sendKeys(uname);
	}
	
	public void enterPassword(String pass) {
		passwordTXTBx.sendKeys(pass);
	}
	public void clickOnLogin() {
		loginBtn.click();
    }
	
	public String getDashboardText() {
		String actualDashBoardText = dashboardTxt.getText();
		return actualDashBoardText;
	}
	
	public String getErrorMsgText() {
		String actualErrorMsgText = errorMessageTxt.getText();
		return actualErrorMsgText;
	}
	
	public void clickOnForgotPasswordLink() {
		forgotPasswordLink.click();
	}
	
	public AddAdminPage doLogin(String uname, String pass) {
		System.out.println("login with: " + uname + " and " + pass);
		userNameTXTBx.sendKeys(uname);
		passwordTXTBx.sendKeys(pass);
		loginBtn.click();
		return new AddAdminPage(driver);
	}
	
	public AddPIMPage doLoginWithPim(String uname, String pass) {
		System.out.println("login with: " + uname + " and " + pass);
		userNameTXTBx.sendKeys(uname);
		passwordTXTBx.sendKeys(pass);
		loginBtn.click();
		return new AddPIMPage();
	}
	
	public String getEmpNameTxtAfterlgn() {
		String actualEmpName =EmpNameText.getText();
		return actualEmpName;

	}
	
	public void clickOnlogoutUserDropdown() {
		logoutUserDropdown.click();
	}
	public void clickOnLogoutBtn() {
		//Select dropdown = new Select(logoutBtn);
		logoutBtn.click();
	}
	
	
	
}
