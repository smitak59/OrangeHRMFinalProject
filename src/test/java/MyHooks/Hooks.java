package MyHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.hrm.keyword.Keyword;
import com.hrm.util.ConfigReader;

public class Hooks {
	 Keyword keyword = new Keyword();;
	 WebDriver driver;
	 
	 private ConfigReader configReader; 
	 Properties prop;
	 
	
	 public void getProperty() {
		 configReader = new ConfigReader();
		 prop = configReader.init_prop();
     }
	 
	@Before(order=0)
	public void launchBrowser() {
		getProperty();
		String browserName = prop.getProperty("browserName");
		String webUrl = prop.getProperty("webURL");
		keyword.lunchBrowser(browserName);
	    keyword.maximizeWindow();
	    keyword.lunchURL(webUrl);
	    keyword.implicittWait();
	}
	
	
	
	
//	@After(order = 1)
//	public void tearDown(Scenario scenario) {
//		if(scenario.isFailed()) {
////			String screenShotName = scenario.getName().replaceAll(" ", "_");
////		    byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
////		    scenario.attach(sourcePath, "image/png", screenShotName);
//		    keyword.takeScreenShot();
//		}
//	}
	
	@After(order = 1)
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) Keyword.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
                Keyword.takeScreenshot(scenario.getName()); // also save to file
            } catch (Exception e) {
                System.out.println("Screenshot hook failed: " + e.getMessage());
            }
        }
    }
	
	@After(order = 0)
	public void quitBrowser() {
		keyword.tearDown();
		
	}

//	@After(order = 1)
//	public void tearDown(Scenario scenario) {
//	    if (scenario.isFailed()) {
//	        byte[] sourcePath = ((TakesScreenshot) Keyword.getDriver()).getScreenshotAs(OutputType.BYTES);
//	        scenario.attach(sourcePath, "image/png", scenario.getName());
//	    }
//	}

}
