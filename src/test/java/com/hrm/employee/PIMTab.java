package com.hrm.employee;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.hrm.base.BaseClass;
import com.hrm.keyword.Keyword;
import com.hrm.pages.AddPIMPage;
import com.hrm.pages.LoginPage;
import com.hrm.util.TestUtil;

public class PIMTab extends BaseClass {
	static Keyword keyword = new Keyword();
	String generatedFirstName;
    String generatedLastName;

//	
	@Test(priority=1)
	public void addEmployee() throws InterruptedException, AWTException, IOException{
		AddPIMPage pimpage=new AddPIMPage();
		System.out.println("Enter in method");
		pimpage.clickOnAddempBtn();
		pimpage.enterFirstName();
		pimpage.enterLastName();
		pimpage.MouseKeyMethodForEmpID();
		pimpage.enterEmployeeId();
		pimpage.addImage();
		pimpage.clickSaveButton();
		pimpage.empcreatedmsg();
	}
	
	@Test(priority=0)
	 public void user_enters_random_employee_name() throws AWTException, IOException {
		    AddPIMPage pimpage=new AddPIMPage();
		    pimpage.clickOnAddempBtn();
	        generatedFirstName = TestUtil.generateRandomFirstName();
	        generatedLastName = TestUtil.generateRandomLastName();

	        pimpage.enterFirstName(generatedFirstName);
	        pimpage.enterLastName(generatedLastName);
	        pimpage.MouseKeyMethodForEmpID();
			pimpage.enterEmployeeId();
			pimpage.addImage();
			pimpage.clickSaveButton();
			pimpage.empcreatedmsg();
	    }
		
	@Test(priority=2)
	public void searchEmployeeNyName() throws InterruptedException{
		AddPIMPage pimpage=new AddPIMPage();
	pimpage.searchEmployeeByName();
	pimpage.RecordsFoundMessageDisplayed();
	}
	
	
	@Test(priority = 3)
    public void SearchEmployeeById() throws InterruptedException {
		AddPIMPage pimpage=new AddPIMPage();
		pimpage.searchEmployeeById();
		keyword.ScrolldownMethod();
		pimpage.searchEmpIDresult();
	}


	@Test(priority=4)
	public void ListEmployees() throws InterruptedException	{
		AddPIMPage pimpage=new AddPIMPage();
		pimpage.clickEmployeeListTab();
		pimpage.GetAllListOfUsers();
	}
	
	
	@Test(priority=5)
	public void ResetEmpSearch() throws InterruptedException {
		AddPIMPage pimpage=new AddPIMPage();
		pimpage.searchEmployeeByName();
		pimpage.ResetMethod();
	
	}
	@Test(priority=6)
	public void deleteEmployee() throws InterruptedException{
		AddPIMPage pimpage=new AddPIMPage();
		pimpage.searchEmployeeByName();
		keyword.ScrolldownMethod();
		pimpage.DeleteEmp();
		
		
		 
	}
	@Test(priority=7)
	public void AddEmpWithUsernameAndPassword() throws AWTException, InterruptedException, IOException {
		AddPIMPage pimpage=new AddPIMPage();
		pimpage.clickOnAddempBtn();
		pimpage.enterFirstName();
		pimpage.enterLastName();
		pimpage.MouseKeyMethodForEmpID();
		pimpage.enterEmployeeId();
		pimpage.addImage();
		pimpage.AddEmpWithLoginDetails();
		pimpage.clickSaveButton();
		pimpage.empcreatedmsg();
	}

}
