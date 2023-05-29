package runners;

import base.basePage;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.ITest;
import org.testng.annotations.*;
import utils.driverManager;
import utils.generalUtils;

@CucumberOptions(features = "src/main/java/features/",
        tags = "@test",
        glue = {"stepdefs"},
        plugin = {"pretty", "html:target/cucumber-html-report", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-report.json"},
        monochrome = true)

public class runner extends basePage implements ITest {
    private TestNGCucumberRunner testNGCucumberRunner;
    private final ThreadLocal<String> testName = new ThreadLocal<>();
    @Parameters({"BROWSER_NAME"})
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(String browserName){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        browser = browserName;
    }
    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        driverManager.getDriver();
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        generalUtils.navigateToBaseURL();
    }
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        testNGCucumberRunner.finish();
        driverManager.quitDriver();
    }

    @Test(groups = "cucumber", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeatures) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider(parallel = false)
    public Object[][] scenarios(){ return testNGCucumberRunner.provideScenarios(); }

    @Override
    public String getTestName() {
        return testName.get();
    }
}
