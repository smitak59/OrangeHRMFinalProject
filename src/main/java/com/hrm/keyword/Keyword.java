package com.hrm.keyword;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Keyword {
	
		public static WebDriver driver;
		private static final Logger log = Logger.getLogger(Keyword.class);
		public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

		
		public WebDriver lunchBrowser(String browserName) {
			
			if(browserName.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
				//System.out.println("Launched Chrome browser");
			    log.info("Launched Chrome browser");
			}else if(browserName.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
				log.info("Launched Edge browser");
			}else if(browserName.equalsIgnoreCase("FireFox")) {
				driver = new FirefoxDriver();
				log.info("Launched FireFox browser");
			}else if(browserName.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
				log.info("Launched Safari browser");
			}else {
				log.error("You Entered invalid browser name");
				driver = new ChromeDriver();
				log.error("Due to invalid browser by default launched Chrome browser");
			}
			 tlDriver.set(driver);
			driver.manage().deleteAllCookies();
			return getDriver();
			
		}
		
		public void lunchURL(String url) {
			driver.get(url);
		}
		

		 public String getTitleText() {
				return driver.getTitle();
		 }
		 public String getCurrentUrlLink() {
			return driver.getCurrentUrl();
			
		}
		
		 
		 
		public void maximizeWindow() {
			driver.manage().window().maximize();
			log.info("Browser maximized");
		}
		
		public void implicittWait() {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			
		}
		
		public void enterText(String xpath,String textValue) {
			driver.findElement(By.xpath(xpath)).sendKeys(textValue);

		}
		
       
		
//		public static boolean isDisplayed(String locatorType,String locatorValue) {
//			boolean flag = false;
//			
//			if (flag) {
//				flag = driver.findElement(By.cssSelector(locatorValue)).isDisplayed();
//				if (flag) {
//					System.out.println("The element is Displayed");
//				} else {
//					System.out.println("The element is not Displayed");
//				}
//			} else {
//				System.out.println("Not displayed ");
//			}
//			return flag;
//		}
//		
		

		//method overloading
		public void enterText(String locatorType,String locatorValue,String textValue) {
			if(locatorType.equalsIgnoreCase("xpath")) {
			  driver.findElement(By.xpath(locatorValue)).sendKeys(textValue);
			}else if(locatorType.equalsIgnoreCase("css")) {
				  driver.findElement(By.cssSelector(locatorValue)).sendKeys(textValue);
			}else if(locatorType.equalsIgnoreCase("name")) {
				   driver.findElement(By.name(locatorValue)).sendKeys(textValue);
			}else if(locatorType.equalsIgnoreCase("id")) {
				  driver.findElement(By.id(locatorValue)).sendKeys(textValue);
			}else if(locatorType.equalsIgnoreCase("className")) {
				  driver.findElement(By.className(locatorValue)).sendKeys(textValue);
			}else if(locatorType.equalsIgnoreCase("tagName")) {
				  driver.findElement(By.tagName(locatorValue)).sendKeys(textValue);
			}else if(locatorType.equalsIgnoreCase("linkText")) {
				  driver.findElement(By.linkText(locatorValue)).sendKeys(textValue);
			}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
				  driver.findElement(By.partialLinkText(locatorValue)).sendKeys(textValue);
			}else {
				log.error("invalid selector: Unable to locate an element with the any locator expression, Please enter valid locator starategy");

			}


		}
		public void enterUsername(String xpath,String textValue) {
			driver.findElement(By.xpath(xpath)).sendKeys(textValue);

		}
		public void enterPassword(String xpath,String textValue) {
			driver.findElement(By.xpath(xpath)).sendKeys(textValue);

		}
		
		public void click(String xapath) {
			driver.findElement(By.xpath(xapath)).click();

		}
		public void click(String locatorType,String locatorValue) {
			if(locatorType.equalsIgnoreCase("xpath")) {
	           driver.findElement(By.xpath(locatorValue)).click();
			}else if(locatorType.equalsIgnoreCase("css")) {
			   driver.findElement(By.cssSelector(locatorValue)).click();
			}else if(locatorType.equalsIgnoreCase("name")) {
				   driver.findElement(By.name(locatorValue)).click();
			}else if(locatorType.equalsIgnoreCase("id")) {
				   driver.findElement(By.id(locatorValue)).click();
			}else if(locatorType.equalsIgnoreCase("className")) {
				   driver.findElement(By.className(locatorValue)).click();
			}else if(locatorType.equalsIgnoreCase("tagName")) {
				   driver.findElement(By.tagName(locatorValue)).click();
			}else if(locatorType.equalsIgnoreCase("linkText")) {
				   driver.findElement(By.linkText(locatorValue)).click();
			}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
				   driver.findElement(By.partialLinkText(locatorValue)).click();
			}else {
				log.error("invalid selector: Unable to locate an element with the any locator expression, Please enter valid locator starategy");

			}
			

		}
		
		public String getAcutalText(String xpath) {
			return driver.findElement(By.xpath(xpath)).getText();
		}
		
		public void assertExpectedWithActual(String actualText, String expectedText) {
			Assert.assertEquals(actualText, expectedText);
			log.info("Assertion is Pass");
		}
		
		public void assertExpectedWithActual(String actualText, String expectedText, String AssertMsg) {
			Assert.assertEquals(actualText, expectedText,AssertMsg);
		}
		
		public String getCurrentTimeDate() {
			// Get the current date and time
			LocalDateTime localTImeDate = LocalDateTime.now();
			// Define the format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");// 2025_03_28_11_34_34
			// Format the current date and time
			String formattedNow = localTImeDate.format(formatter);// 2025_03_28_11_34_34
			// Print the formatted date and time
			return formattedNow;
		}
		
		public void takeScreenShot() {
			Screenshot src = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(400)).takeScreenshot(driver);
			BufferedImage img = src.getImage();
			try {
				ImageIO.write(img, "png", new File("src/ScreenShots/" +getCurrentTimeDate() + "_screenShotUsingAshot.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		 /**
	     * Safe screenshot capture – works even if teardown fails
	     */
	    public static void takeScreenshot(String namePrefix) {
	        if (driver == null) {
	            System.out.println("Driver is null, cannot take screenshot.");
	            return;
	        }
	        try {
	            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            File dest = new File("ScreenShots/" + namePrefix + "_" + timeStamp + ".png");
	            dest.getParentFile().mkdirs();
	            Files.copy(src.toPath(), dest.toPath());
	            System.out.println("Screenshot saved to: " + dest.getAbsolutePath());
	        } catch (IOException e) {
	            System.out.println("Screenshot save failed: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("Screenshot capture failed: " + e.getMessage());
	        }
	    }
		
		
//		public void tearDown(){
//			driver.quit();
//			log.info("TearDown Successfull");
//		}
	    
	    public void tearDown() {
	        try {
	            WebDriver currentDriver = getDriver(); // use ThreadLocal
	            if (currentDriver != null) {
	                currentDriver.quit();
	                log.info("TearDown Successful");
	            } else {
	                log.warn("No driver found, skipping quit");
	            }
	        } catch (Exception e) {
	            log.error("Teardown failed: " + e.getMessage());
	            takeScreenshot("Teardown_Failed");
	        }
	    }

		/**
		 * this is used to get the driver with ThreadLocal
		 * 
		 * @return
		 */
		public static synchronized WebDriver getDriver() {
			return tlDriver.get();
		}
		
		public List<WebElement> elementList(String xpath) {
			List <WebElement> elementlist = driver.findElements(By.xpath(xpath));
			elementlist.get(3).click();
			return elementlist ;
			
		}
		public void ScrolldownMethod() {
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0,500)");

		    }

	}
