package com.hrm.StepDefinations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.hrm.keyword.Keyword;
import com.hrm.pages.LoginPage;
import com.hrm.util.ConfigReader;
import com.utilities.ReadXLSdata;

public class EmployeeLogin {
	WebDriver driver;
	Keyword keyword = new Keyword();;
	 
	 private ConfigReader configReader; 
	 Properties prop;
	
	public void getProperty() {
		 configReader = new ConfigReader();
		 prop = configReader.init_prop();
   }
	@BeforeMethod
	public void launchBrowser() {
		getProperty();
		String browserName = prop.getProperty("browserName");
		String webUrl = prop.getProperty("webURL");
		keyword.lunchBrowser(browserName);
	    keyword.maximizeWindow();
	    keyword.lunchURL(webUrl);
	    keyword.implicittWait();

	}

	@Test(dataProvider = "bvtdata", dataProviderClass = ReadXLSdata.class)
	public void LoginTest(String username,String password, String expectedName) {
		LoginPage loginpage=new LoginPage(keyword.driver);
		loginpage.enterUserName(username);
		loginpage.enterPassword(password);
		loginpage.clickOnLogin();
		
		
		if (expectedName.equalsIgnoreCase("Invalid credentials")) {
			String actualError = loginpage.getErrorMsgText();
            Assert.assertEquals(actualError.trim(), expectedName, "Error message mismatch");
        } else {
            String actualName = loginpage.getEmpNameTxtAfterlgn();
            Assert.assertEquals(actualName.trim(), expectedName, "Logged in user mismatch");
        }
    
//		String actualEmpName=loginpage.getEmpNameTxtAfterlgn();
//		
//		String expUserName=expectedName.trim();
//		Assert.assertEquals(actualEmpName, expUserName);
//		System.out.println("Logged in with: " + username + 
//                " | Expected Name: " + expUserName + 
//                " | Actual: " + actualEmpName);
		
	}
	 @AfterMethod
	    public void tearDown(ITestResult result) throws IOException {
	        if (ITestResult.FAILURE == result.getStatus()) {
	            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            Files.copy(src.toPath(), new File("ScreenShots/" + result.getName() + ".png").toPath());
	        }
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	

}
