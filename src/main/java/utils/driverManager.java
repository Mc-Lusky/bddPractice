package utils;
import base.basePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class driverManager extends basePage {

    //src/drivers/chromedriver.exe
    //C:\driversBrowser\geckodriver-v0.33.0-win32\geckodriver.exe
    //"C:\\driversBrowser\\edgedriver_win64\\msedgedriver.exe"

    private static final String CHROME_DRIVER_PATH = "src/drivers/chromedriver.exe";
    private static final String FIREFOX_DRIVER_PATH = "C:\\driversBrowser\\geckodriver-v0.33.0-win32\\geckodriver.exe";
    private static final String IE_DRIVER_PATH = "C:\\\\driversBrowser\\\\edgedriver_win64\\\\msedgedriver.exe";

    private static final String BROWSER = basePage.browser;

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            if (BROWSER.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driverThreadLocal.set(new ChromeDriver());
            } else if (BROWSER.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                driverThreadLocal.set(new FirefoxDriver());
            } else if (BROWSER.equalsIgnoreCase("ie")) {
                System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
                driverThreadLocal.set(new InternetExplorerDriver());
            } else {
                throw new IllegalArgumentException("Invalid browser specified: " + BROWSER);
            }
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
