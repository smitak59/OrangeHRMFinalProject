package TestRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags="", features= {"src/test/resources/features/"},
glue= {"com.hrm.StepDefinations","MyHooks"},
dryRun = false ,
monochrome = true,//if you want console output from Cucumber in a readable format, you can specify it like this
plugin = {"pretty","html:target/htmlreport.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
   
//	@Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios(); // Parallel execution of scenarios
//    }
}

//plugin = {
//        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
//}

