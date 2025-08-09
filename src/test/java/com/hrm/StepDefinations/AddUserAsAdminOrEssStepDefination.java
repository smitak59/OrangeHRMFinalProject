package com.hrm.StepDefinations;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.hrm.keyword.Keyword;
import com.hrm.pages.AddAdminPage;
import com.hrm.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddUserAsAdminOrEssStepDefination {
	
	static Keyword keyword = new Keyword();
	private AddAdminPage addAdminPage = new AddAdminPage(Keyword.getDriver());
	private static final Logger log = Logger.getLogger(Keyword.class);
	private LoginPage loginPage = new LoginPage(Keyword.getDriver());
	public static WebDriver driver;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable credTable) {
        List<Map<String, String>> credList = credTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");
        keyword.implicittWait();
        keyword.lunchURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		addAdminPage = loginPage.doLogin(userName, password);

	}

	
	@When("User clicks on the Admin tab from the menu")
	public void user_clicks_on_the_tab_from_the_menu() {
	    addAdminPage.clickOnAdminMenuTab();
	}

	@And("Admin panel page should display Admin \\/ User Management options")
	public void admin_panel_page_should_display_admin_user_management_options() {
		String actualAdminPageUrl = keyword.getCurrentUrlLink();
	    //String actualText = addAdminPage.verifyAdminUserManagementPage();
	    keyword.assertExpectedWithActual(actualAdminPageUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers", "This not a Admin /User Management page");
	    log.info("User is on the Admin /User Management page");
	}

	@When("User clicks on the add button")
	public void user_clicks_on_the_add_button() {
	    addAdminPage.clickOnTheAddBtn();
	    log.info("User Click on Add Button");
	    	    
	    keyword.assertExpectedWithActual(addAdminPage.verifyAddAdminPageText(), "Add User", "User is not on the Add User Page");
	    log.info("User is on the Add User Page");
	}

	@And("User selects user role {string}")
	public void user_selects_user_role(String userRole){
		log.info("UserRole::"+userRole);
		addAdminPage.clickOnUserRoleDropDown();
		
		 List<WebElement> roles = addAdminPage.getallUserRoleOptions();
             
		 boolean found = false;
	        for (WebElement role : roles) {
	            if (role.getText().trim().equalsIgnoreCase(userRole)) {
	                role.click();
	                found = true;
	                System.out.println("Selected role: " + userRole);
	                break;
	            }
	        }

	        if (!found) {
	            System.out.println("Role not found: " + userRole);
	        }
		
//		List<WebElement> options = addAdminPage.getAllUserDropDownOptions().getOptions();
	
//		
//		for (WebElement option : options) {
//		    if (option.getText().equals(userRole)) {   // Select 'Admin' or 'Ess'
//		        option.click();
//		        break;
//		    }else {
//		    	log.error("User Role is not selected From dropdown");
//		    }
//		}
//		
		
	    
	    
	}

	@And("User enters employee name {string}")
	public void user_enters_employee_name(String empName){
		
	   addAdminPage.enterEmployeeName(empName); 
	   List<WebElement> getEmpSuggList = addAdminPage.getEmpSuggesionList();
	   for(WebElement suggestion : getEmpSuggList) {
		    
		    if (!(suggestion.getText()).equals(empName)) {
		    	
		    	suggestion.sendKeys(Keys.TAB);
		        
		        Assert.assertFalse(addAdminPage.isElementPresent("//span[normalize-space()='Invalid']"), "Employee not found error shown");
		       
		    }
	        if (suggestion.getText().equalsIgnoreCase(empName)) {
	            suggestion.click();
	            break;
	        }
	  }
	}
	@And("User selects status {string}")
	public void user_selects_status(String status){
		
		addAdminPage.clickOnStatusDropdown();
		List<WebElement> statusop = addAdminPage.getListOfStatus();

		 boolean found = false;
	        for (WebElement op : statusop) {
	            if (op.getText().trim().equalsIgnoreCase(status)) {
	                op.click();
	                found = true;
	                System.out.println("Selected Status: " + status);
	                break;
	            }
	        }

	        if (!found) {
	            System.out.println("Status not found: " + status);
	        }
	}

	@And("User enters username {string}")
	public void user_enters_username(String uname) throws InterruptedException {
		Thread.sleep(3000);
	    addAdminPage.enterUserName(uname);
	    
	}

	@And("User enters password {string}")
	public void user_enters_password(String pass) throws InterruptedException {
		Thread.sleep(3000);
	    addAdminPage.enterPassword(pass);
	}

	@And("User enters confirm password {string}")
	public void user_enters_confirm_password(String cpass) {
	    addAdminPage.confirmPassword(cpass);
	}
	@And("User clicks on the save button")
	public void user_clicks_on_the_save_button() throws InterruptedException {
		Thread.sleep(5000);
		addAdminPage.clickOnSaveBtn();
		
	}

	@Then("The system should show a success message {string}")
	public void the_system_should_show_a_success_message(String actualText) throws InterruptedException {
		Thread.sleep(2000);
		keyword.takeScreenShot();
		String getExpectedMesText = "Successfully Saved";
		Assert.assertEquals(actualText, getExpectedMesText,"User is not Successfully Saved");
		
		//addAdminPage.getSuccessMsgAfterAddUser();
		//Assert.assertTrue(driver.getPageSource().contains("Successfully Saved"));
		//log.info(addAdminPage.getSuccessMsgAfterAddUser());
	}

	@Then("The new user {string} should appear in the user list")
	public void the_new_user_should_appear_in_the_user_list(String ename) {
	    log.info("The new user is add in a list : "+ename);
	}

}
