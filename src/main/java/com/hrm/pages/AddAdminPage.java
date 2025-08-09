package com.hrm.pages;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hrm.keyword.Keyword;

public class AddAdminPage {
	WebDriver driver;
	
	@FindBy(css="h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")
	WebElement dashboardTxt;
	
	//@FindBy(xpath="//a[contains(@href,'Admin')]/span")
	@FindBy(xpath="//span[text()='Admin']")
	WebElement adminTab;
	
	@FindBy(css="h6.oxd-text")
	WebElement AdminUserManagementPageTxt; 
	
	@FindBy(xpath="//button[normalize-space()='Add']")
	WebElement addBtn;
	//Replaces multiple spaces between words with a single space.
	//normalize-space() makes the XPath more reliable and accurate when matching text.
	
	@FindBy(css="h6.oxd-text.oxd-text--h6.orangehrm-main-title")
	WebElement addAdminPageVerifyText;
	
	
	@FindBy(xpath="(//div[contains(@class,'oxd-select-text')])[1]")
	WebElement userRoleSelectdropdown;
	
	@FindBy(xpath="//div[@role='option']//span")
	List<WebElement> getallUserRoleOptions;
	
	@FindBy(xpath="//input[@placeholder='Type for hints...']")
	WebElement empName;
	
	@FindBy(xpath="//div[@role='listbox']//span")
	List<WebElement> empSuggeList;
	
	@FindBy(xpath="//span[normalize-space()='Invalid']")
	WebElement empNotFoundTxt;

	@FindBy(xpath="//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
	WebElement statusDropdown;
	
	@FindBy(xpath="//div[@role='option']//span")
	List<WebElement> getListOfStatus;
	
	
	@FindBy(xpath="//label[text()='Username']/following::input[1]")
	WebElement userName;
	
	//@FindBy(xpath="//input[@fdprocessedid='q76c68p']")
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input")
	WebElement password; 
	
	//@FindBy(xpath="//input[@fdprocessedid='7fzllm']")
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")
	WebElement confirmPassword; 
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement saveBtn;
	
	@FindBy(css="div.oxd-toast.oxd-toast--success .oxd-toast-content-text")
	WebElement successMsgAfterAddUser;
	
	 public AddAdminPage(WebDriver driver) {
		 PageFactory.initElements(Keyword.driver, this);
	 }
//	public AddAdminPage(WebDriver driver) {
//	    this.driver = driver; 
//	    PageFactory.initElements(driver, this); 
//	}
	 
	 public String getDashboardText() {
			String actualDashBoardText = dashboardTxt.getText();
			return actualDashBoardText;
	 }
	 
	 public void clickOnAdminMenuTab() {
		    adminTab.click();
	 }
	 
	 public String verifyAdminUserManagementPage() {
		 String adminUserManagementPageTxt = AdminUserManagementPageTxt.getText();
		 return adminUserManagementPageTxt; 
	 }
	 
	 public void clickOnTheAddBtn() {
		 addBtn.click();
	 }
	 
	 public String verifyAddAdminPageText() {
		 return addAdminPageVerifyText.getText();
	 }
	 
	 public void clickOnUserRoleDropDown() {
		 userRoleSelectdropdown.click();
	}
	
	
	 public List<WebElement> getallUserRoleOptions() {
		 return getallUserRoleOptions;
	 }
	 
	 public void clickOnStatusDropdown() {
		 statusDropdown.click();
	 }
	 
	 public List<WebElement> getListOfStatus(){
		
		 return getListOfStatus;
	 }
	 
	 public void enterEmployeeName(String ename) {
		 empName.sendKeys(ename);
	 }
	 
	 public void enterUserName(String uname) {
		 userName.sendKeys(uname);

	}
	 
	 public String getEmpRecordNotFoundTxt() {
		String actualempNotfoundTxt =  empNotFoundTxt.getText();
		return actualempNotfoundTxt;
	 }
	 public List<WebElement> getEmpSuggesionList() {
		 return empSuggeList;
	 }
	 
	 public void enterPassword(String pass) {
		 password.sendKeys(pass);
	 }
	 
	 public void confirmPassword(String cpass) {
		 confirmPassword.sendKeys(cpass);
	 }
	 
	 public void clickOnSaveBtn() {
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].click();", saveBtn);
		 saveBtn.click();
	 }
	 
	 public String getSuccessMsgAfterAddUser() {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    
		    WebElement successMsgAfterAddUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.cssSelector("div.oxd-toast.oxd-toast--success .oxd-toast-content-text")
		    ));
		 
		String getactualSuccessMsg = successMsgAfterAddUser.getText();
		return getactualSuccessMsg;
		
	}
	 
	 public boolean isElementPresent(String xpath) {
	        List<WebElement> elements = driver.findElements(By.xpath(xpath));
	        return !elements.isEmpty();
	    }
	 
	
	 
	}
