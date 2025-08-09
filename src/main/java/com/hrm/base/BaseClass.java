package com.hrm.base;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.hrm.keyword.Keyword;


public class BaseClass {
	public static String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	Keyword keyword = new Keyword();;
	 static WebDriver driver;
	
	
	@BeforeMethod
	public void setup()
	{
	
		keyword.lunchBrowser("Chrome");
		keyword.maximizeWindow();
		keyword.lunchURL(baseUrl);
		keyword.implicittWait();
		keyword.enterUsername("//input[@placeholder='Username']", "Admin");
		keyword.enterPassword("//input[@placeholder='Password']", "admin123");
		keyword.click("//button[@type='submit']");
		keyword.getTitleText();
		keyword.click("//span[text()='PIM']");

	}
	@AfterMethod
	public void tearDown() {
		
		keyword.tearDown();

	}
}
