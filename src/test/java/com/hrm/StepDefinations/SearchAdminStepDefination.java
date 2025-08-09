package com.hrm.StepDefinations;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.hrm.keyword.Keyword;
import com.hrm.pages.SearchUserFromAdminPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchAdminStepDefination {
	   Keyword keyword = new Keyword();
	
        private SearchUserFromAdminPage searchUserFrmAdmin = new SearchUserFromAdminPage(Keyword.getDriver());
    	private static final Logger log = Logger.getLogger(Keyword.class);

		@When("User enters username {string} in the search field")
		public void user_enters_username_in_the_search_field(String uname) {
			searchUserFrmAdmin.enterUserNameForSearch(uname);
		}
		
		@And("User clicks on the search button")
		public void user_clicks_on_the_search_button() throws InterruptedException {
			searchUserFrmAdmin.clickOnSearchBtn();
			Thread.sleep(2000);
		}
		
		@Then("The system should display user details {string}")
		public void the_system_should_display_user_details(String expectedUsername) {
//          List<WebElement> rows = searchUserFrmAdmin.getAllSearchDataFromTable();
//			
//			//log.info("number of rows:"+rows.size());
//          
////          for(int r=1;r<=rows.size();r++) {
////        	  List<WebElement> name = searchUserFrmAdmin.getSearchDetails();
////        	  log.info(((WebElement) name).getText());
////          }
//
//          boolean found = false;
//          List<WebElement> actualUsername= searchUserFrmAdmin.getSearchDetails();
//          for (WebElement row : rows) {
//              
//        	    if ((actualUsername).equalsIgnoreCase(expectedUsername)) {
//        	        found = true;
//        	        break;
//        	    }
//        	}
//          //Assert.assertTrue(found, "Expected username not found in the table!");
//          Assert.assertEquals(searchUserFrmAdmin.getSearchDetails().getText(), expectedUsername,"Expected username not found in the table!");

		}
		
		@Then("The system should display user details for {string}")
		public void the_system_should_display_user_details_for(String expectedUsername) {
			List<Map<String, String>> tableData = searchUserFrmAdmin.getSearchResults();

		    for (Map<String, String> row : tableData) {
		        Assert.assertEquals(expectedUsername, row.get("username"));
		    }
		}
		
		
		@Then("I should see the results matching all fields by user role {string}")
		public void i_should_see_the_results_matching_all_fields_by_user_role(String expectedRole) {
		    List<Map<String, String>> tableData = searchUserFrmAdmin.getSearchResults();

		    for (Map<String, String> row : tableData) {
		        Assert.assertEquals(expectedRole, row.get("role"));
		    }
		}

		
		
		@Then("I should see the results matching all Employee fields {string}")
		public void validate_table_data_employee_field(String expectedEmpName) {
		    List<Map<String, String>> tableData = searchUserFrmAdmin.getSearchResults();
		    log.info("List :" +tableData);
		    for (Map<String, String> row : tableData) {
		    	    Assert.assertEquals(expectedEmpName, row.get("empName"));
		    }
		}
		
		@Then("I should see the results matching all Status fields {string}")
		public void validate_table_data_status_field(String expectedStatus) {
		    List<Map<String, String>> tableData = searchUserFrmAdmin.getSearchResults();
		    log.info("List :" +tableData);
		    for (Map<String, String> row : tableData) {
		        Assert.assertEquals(expectedStatus, row.get("status"));
		    }
		}
	
		@Then("I should see the results matching all fields {string}{string} {string}{string}")
		public void i_should_see_the_results_matching_all_fields(String expectedUsername,String expectedRole,String expectedEmpName,String expectedStatus) {
			 List<Map<String, String>> tableData = searchUserFrmAdmin.getSearchResults();
			    log.info("List :" +tableData);
			    for (Map<String, String> row : tableData) {
			    	Assert.assertEquals(expectedUsername, row.get("username"));
			        Assert.assertEquals(expectedRole, row.get("role"));
			        Assert.assertEquals(expectedEmpName, row.get("empName"));
			        Assert.assertEquals(expectedStatus, row.get("status"));
			    }
		}
		
		@And("User clicks on Reset button")
		public void clickOnResetBtn() {
			searchUserFrmAdmin.clickOnResetBtn();
		}
		
		@Then("All search fields should be cleared")
	    public void all_search_fields_should_be_cleared() {
			searchUserFrmAdmin.areSearchFieldsCleared();
			log.info("Search fields are not cleared");
	    }
		
}
